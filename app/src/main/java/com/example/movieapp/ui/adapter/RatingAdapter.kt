package com.example.movieapp.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.data.models.moviedetail.Rating
import com.example.movieapp.databinding.ItemRatingBinding

class RatingAdapter: RecyclerView.Adapter<RatingAdapter.RatingViewHolder>() {

    private var list = emptyList<Rating>()

    inner class RatingViewHolder(private val binding: ItemRatingBinding):
    RecyclerView.ViewHolder(binding.root){

        fun bind(rating: Rating){
            binding.apply {
                textSource.text = rating.source
                textValue.text = rating.value
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RatingViewHolder {
        val view = LayoutInflater.from(parent.context)
        return RatingViewHolder(ItemRatingBinding.inflate(view,parent,false))
    }

    override fun onBindViewHolder(holder: RatingViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount() = list.size

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newList: List<Rating>){
        list = newList
        notifyDataSetChanged()
    }
}