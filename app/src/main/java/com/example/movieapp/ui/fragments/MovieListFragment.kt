package com.example.movieapp.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import com.example.movieapp.base.BaseFragment
import com.example.movieapp.databinding.FragmentMovieListBinding
import com.example.movieapp.ui.adapter.MovieListAdapter
import com.example.movieapp.ui.viewmodels.MovieListViewModel
import com.example.movieapp.utils.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieListFragment : BaseFragment<FragmentMovieListBinding>
    (FragmentMovieListBinding::inflate) {

    private val viewModel by viewModels<MovieListViewModel>()
    private val adapter by lazy { MovieListAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initSearch()
        initViews()
    }

    private fun getMovies(title:String){
        viewModel.getMoviesByQuery(title).observe(viewLifecycleOwner,{response->
            Log.d("response",response.toString())
            when(response.status){
                Resource.Status.LOADING -> {
                    binding.apply {
                        progressBar.show()
                        imageEmpty.gone()
                        rvMovieList.gone()
                        lottieError.gone()
                    }
                }
                Resource.Status.SUCCESS ->{
                    binding.progressBar.gone()
                        response.data.let { it?.search?.let { it1 -> adapter.setMovies(it1) } }
                        binding.rvMovieList.show()
                }
                Resource.Status.ERROR -> {
                    binding.lottieError.show()
                    binding.progressBar.gone()
                    Log.d("getMovies", "${response.message}")
                }
            }
        })
    }

    private fun initSearch(){
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                //Api returns movies if search has more than 2 characters, otherwise you get
                //false response
                if(!query.isNullOrEmpty()) {
                    getMovies(query)
                    hideKeyboard()
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if(newText.isNullOrEmpty()){
                    binding.apply{
                        rvMovieList.gone()
                        imageEmpty.show()
                        lottieError.gone()
                    }
                }else{
                    binding.imageEmpty.gone()
                }
                return true
            }
        })
    }

    private fun initViews(){
        binding.rvMovieList.adapter = adapter
        setActionBarTitle("Movies")
    }
}