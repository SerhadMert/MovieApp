package com.example.movieapp.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapp.data.models.movie.MovieResponse
import com.example.movieapp.databinding.ItemMovieListBinding

class MovieListAdapter : RecyclerView.Adapter<MovieListAdapter.MovieListViewHolder>() {

    private var list = emptyList<MovieResponse>()

    inner class MovieListViewHolder(private val binding: ItemMovieListBinding):
        RecyclerView.ViewHolder(binding.root) {

            fun bind(movie: MovieResponse){
                binding.apply {
                    Glide.with(imagePoster.context).load(movie.poster).into(imagePoster)
                    textMovieTitle.text = movie.title
                    textYear.text = movie.year
                    textType.text = movie.type
                }
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListViewHolder {
        val view = LayoutInflater.from(parent.context)
        return MovieListViewHolder(ItemMovieListBinding.inflate(view,parent,false))
    }

    override fun onBindViewHolder(holder: MovieListViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount() = list.size

    @SuppressLint("NotifyDataSetChanged")
    fun setMovies(newList: List<MovieResponse>){
        list = newList
        notifyDataSetChanged()
    }
}