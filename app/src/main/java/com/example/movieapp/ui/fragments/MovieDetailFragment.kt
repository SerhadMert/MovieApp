package com.example.movieapp.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.movieapp.base.BaseFragment
import com.example.movieapp.databinding.FragmentMovieDetailBinding
import com.example.movieapp.ui.adapter.GenreAdapter
import com.example.movieapp.ui.adapter.RatingAdapter
import com.example.movieapp.ui.viewmodels.MovieDetailViewModel
import com.example.movieapp.utils.Resource
import com.example.movieapp.utils.gone
import com.example.movieapp.utils.show
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
import kotlin.collections.ArrayList

@AndroidEntryPoint
class MovieDetailFragment : BaseFragment<FragmentMovieDetailBinding>
    (FragmentMovieDetailBinding::inflate) {

    private val viewModel by viewModels<MovieDetailViewModel>()
    private val args by navArgs<MovieDetailFragmentArgs>()
    private val genreAdapter by lazy { GenreAdapter() }
    private val ratingAdapter by lazy { RatingAdapter() }
    private val genres = ArrayList<String>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getMovieById()
        initBack()
    }

    private fun getMovieById(){
        viewModel.getMovieById(args.id).observe(viewLifecycleOwner,{response ->
            when(response.status){
                Resource.Status.LOADING -> binding.progressBar.show()
                Resource.Status.SUCCESS ->{
                    binding.progressBar.gone()
                    response.data?.let {
                        //Api returns 300x300 size of an image, with this code I'm changing the url
                        //and getting better image with higher quality
                        val img = it.poster?.replace("SX300.jpg","SX800.jpg")
                        val genre = it.genre?.replace(" ","")
                            ?.uppercase(Locale.getDefault())?.split(",")
                        binding.apply {
                            Glide.with(requireContext())
                                .load(img)
                                .into(imagePoster)
                            textWriter.text = it.writer
                            textDirector.text = it.director
                            textReleased.text = it.released
                            textRated.text = it.rated
                            textPlot.text = it.plot
                            textTitle.text = it.title
                            genre?.let { it1 -> genres.addAll(it1) }
                            genreAdapter.setData(genres)
                            it.ratings?.let { it1 -> ratingAdapter.setData(it1) }
                            rvGenre.adapter = genreAdapter
                            rvRatings.adapter = ratingAdapter
                        }
                    }
                }
                Resource.Status.ERROR -> {
                    Toast.makeText(requireContext(), "${response.status}", Toast.LENGTH_SHORT).show()
                    Log.d("getMovies", "${response.message}")
                }
            }
        })
    }
    private fun initBack(){
        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }

}