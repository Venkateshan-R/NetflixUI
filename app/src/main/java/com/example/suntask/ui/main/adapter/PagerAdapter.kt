package com.example.suntask.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.suntask.data.model.MoviesModel
import com.example.suntask.databinding.ItemMoviesBinding
import com.example.suntask.databinding.ItemPagerBinding
import com.example.suntask.ui.utils.Constants

class PagerAdapter()  : RecyclerView.Adapter<PagerAdapter.ViewHolder>() {

    var onClick : ((MoviesModel.Results) -> Unit)? = null

    var list = ArrayList<MoviesModel.Results>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class ViewHolder(val binding: ItemPagerBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindData(model: MoviesModel.Results) {
            Glide.with(getContext()).load(Constants.IMAGE_PATH+model.posterPath)
                .into(binding.ivMovie)

            binding.root.setOnClickListener {
                onClick?.invoke(model)
            }
        }

        fun getContext() = binding.root.context
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(ItemPagerBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(list.get(position))
    }


}