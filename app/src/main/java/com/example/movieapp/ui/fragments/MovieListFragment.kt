package com.example.movieapp.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import com.example.movieapp.base.BaseFragment
import com.example.movieapp.databinding.FragmentMovieListBinding
import com.example.movieapp.ui.adapter.MovieListAdapter
import com.example.movieapp.ui.viewmodels.MovieListViewModel
import com.example.movieapp.utils.Resource
import com.example.movieapp.utils.gone
import com.example.movieapp.utils.show
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieListFragment : BaseFragment<FragmentMovieListBinding>
    (FragmentMovieListBinding::inflate) {

    private val viewModel by viewModels<MovieListViewModel>()
    private val adapter by lazy { MovieListAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initSearch()
    }

    private fun getMovies(title:String){
        viewModel.getMoviesByQuery(title).observe(viewLifecycleOwner,{response->
            when(response.status){
                Resource.Status.LOADING -> binding.progressBar.show()
                Resource.Status.SUCCESS ->{
                    binding.progressBar.gone()
                    if(response.data?.search?.size != 0){
                        response.data.let { adapter.setMovies(it!!.search!!) }
                        binding.rvMovieList.adapter = adapter
                    }
                }
                Resource.Status.ERROR -> {
                    Toast.makeText(requireContext(), "${response.status}", Toast.LENGTH_SHORT).show()
                    Log.d("getMovies", "${response.message}")
                }
            }
        })
    }

    private fun initSearch(){
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if(!newText.isNullOrEmpty() && newText.length > 2) {
                    getMovies(newText)
                }
                return true
            }
        })
    }
}