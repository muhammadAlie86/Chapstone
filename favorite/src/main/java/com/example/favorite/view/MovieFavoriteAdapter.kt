package com.example.favorite.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.core.data.api.Constant.Companion.POSTER_IMAGE_URL

import com.example.core.databinding.ItemListBinding
import com.example.core.domain.model.MovieEntityModel
import com.example.core.utils.DataCallbackMovie

class MovieFavoriteAdapter (private val dataCallbackMovie: DataCallbackMovie) : RecyclerView.Adapter<MovieFavoriteAdapter.MyHolder>() {


    private val listMovieFavorite = ArrayList<MovieEntityModel>()

    fun setMovieFavorite(favoriteMovies : List<MovieEntityModel>){
        listMovieFavorite.clear()
        this.listMovieFavorite.addAll(favoriteMovies)
        notifyDataSetChanged()

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val itemListCatalogueBinding = ItemListBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyHolder(itemListCatalogueBinding)
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val movie = listMovieFavorite[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int = listMovieFavorite.size

    inner class MyHolder(private val binding: ItemListBinding): RecyclerView.ViewHolder(binding.root)  {

        fun bind(movie : MovieEntityModel){
            with(binding){
                tvTitle.text = movie.title
                tvReleaseDate.text = movie.releaseDate
                tvDescription.text = movie.overview
                itemView.setOnClickListener { dataCallbackMovie.setDataMovie(movie) }


                com.bumptech.glide.Glide.with(itemView.context)
                        .load(POSTER_IMAGE_URL + movie.imgPath)
                        .apply(com.bumptech.glide.request.RequestOptions.placeholderOf(com.example.chapstone.R.drawable.ic_loading).error(com.example.chapstone.R.drawable.ic_image_broken))
                        .into(imgPoster)
            }
        }

    }
}