package com.example.chapstone.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.chapstone.R
import com.example.core.data.api.Constant.Companion.POSTER_IMAGE_URL
import com.example.core.databinding.ItemListBinding
import com.example.core.domain.model.MovieEntityModel
import com.example.core.utils.DataCallbackMovie

class MovieAdapter (private val dataCallbackMovie: DataCallbackMovie) : RecyclerView.Adapter<MovieAdapter.MyViewHolder>(){

    private val listMovie = ArrayList<MovieEntityModel>()

    fun setMovie(movies : List<MovieEntityModel>){
        listMovie.clear()
        this.listMovie.addAll(movies)
        notifyDataSetChanged()

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemListCatalogueBinding = ItemListBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(itemListCatalogueBinding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val movie = listMovie[position]
        holder.bind(movie)

    }

    override fun getItemCount(): Int = listMovie.size

    inner class MyViewHolder (private val binding: ItemListBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(movie : MovieEntityModel){
            with(binding){
                tvTitle.text = movie.title
                tvReleaseDate.text = movie.releaseDate
                tvDescription.text = movie.overview
                itemView.setOnClickListener { dataCallbackMovie.setDataMovie(movie) }


                Glide.with(itemView.context)
                    .load(POSTER_IMAGE_URL + movie.imgPath)
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_image_broken))
                    .into(imgPoster)
            }
        }
    }

}