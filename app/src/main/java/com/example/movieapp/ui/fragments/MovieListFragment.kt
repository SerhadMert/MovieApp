package com.example.movieapp.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import com.example.movieapp.R
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
    private val searchText by lazy {
        binding.searchView.findViewById<SearchView.SearchAutoComplete>(R.id.search_src_text)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initSearch()
        initRV()
        changeSV()
        setActionBarTitle("Movies")
    }

    private fun getMovies(title:String){
        viewModel.getMoviesByQuery(title).observe(viewLifecycleOwner,{response->
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
                    if(response.data?.search?.isNotEmpty() == true){
                        response.data.let { it.search?.let { it1 -> adapter.setMovies(it1) } }
                        binding.rvMovieList.show()
                    } else{
                        binding.lottieError.show()
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

    private fun initRV(){
        binding.rvMovieList.adapter = adapter
    }

    private fun changeSV(){
        searchText.setHintTextColor(resources.getColor(R.color.white))
        searchText.setTextColor(resources.getColor(R.color.white))
    }
}