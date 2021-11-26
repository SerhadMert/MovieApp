package com.example.movieapp.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.databinding.ItemGenreBinding

class GenreAdapter: RecyclerView.Adapter<GenreAdapter.GenreViewHolder>() {

    private var list = arrayListOf<String>()

    inner class GenreViewHolder(private var binding: ItemGenreBinding):
    RecyclerView.ViewHolder(binding.root){

        fun bind(genre: String){
            binding.textGenre.text = genre
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreViewHolder {
        val view = LayoutInflater.from(parent.context)
        return GenreViewHolder(ItemGenreBinding.inflate(view,parent,false))
    }

    override fun onBindViewHolder(holder: GenreViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount() = list.size

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newList: ArrayList<String>){
        list = newList
        notifyDataSetChanged()
    }
}