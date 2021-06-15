package com.example.chapstone.view.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.chapstone.MyApplication
import com.example.chapstone.R
import com.example.chapstone.databinding.FragmentDetailBinding
import com.example.chapstone.viewmodel.DetailViewModel
import com.example.chapstone.viewmodel.ViewModelFactory
import com.example.core.data.api.Constant.Companion.BACKDROP_IMAGE_URL
import com.example.core.data.api.Constant.Companion.POSTER_IMAGE_URL
import com.example.core.domain.model.MovieEntityModel
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject

class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding  get() = _binding!!
    private lateinit var snackBar: Snackbar
    private val args : DetailFragmentArgs by navArgs()

    @Inject
    lateinit var factory : ViewModelFactory
    private val viewModel : DetailViewModel by viewModels{
        factory
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showProgress()
        populateMovie(args.detailMovie)

        binding.imgBack.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_detailFragment_to_movieFragment)
        }

    }

    private fun populateMovie(movie : MovieEntityModel?) {

        movie?.let {
            hideProgress()
            with(binding) {
                tvTitle.text = StringBuilder("${movie.title}")
                tvReleaseDate.text = StringBuilder("${movie.releaseDate}")
                tvRate.text = StringBuilder("${movie.voteAverage}")
                tvLanguage.text = StringBuilder("${movie.language}")
                tvDescription.text = StringBuilder("${movie.overview}")


                Glide.with(requireActivity())
                        .load(POSTER_IMAGE_URL + movie.imgPath)
                        .apply(
                                RequestOptions
                                        .placeholderOf(R.drawable.ic_loading)
                                        .error(R.drawable.ic_image_broken)
                        )
                        .into(binding.imgPoster)

                Glide.with(requireActivity())
                        .load(BACKDROP_IMAGE_URL + movie.backdropImg)
                        .apply(
                                RequestOptions
                                        .placeholderOf(R.drawable.ic_loading)
                                        .error(R.drawable.ic_image_broken)
                        )
                        .into(binding.imgBackdrop)
            }
            var statusFavorite = movie.isFavorite
            setState(statusFavorite)
            binding.imgUnFav.setOnClickListener {
                statusFavorite = !statusFavorite
                viewModel.setFavoriteMovie(movie, statusFavorite)
                setState(statusFavorite)
                snackBar = Snackbar.make(view as View,getString(R.string.Success),Snackbar.LENGTH_SHORT)
                snackBar.show()

            }
            binding.imgFav.setOnClickListener {
                statusFavorite = !statusFavorite
                viewModel.setFavoriteMovie(movie, statusFavorite)
                setState(statusFavorite)
                snackBar = Snackbar.make(view as View,getString(R.string.delete),Snackbar.LENGTH_SHORT)
                snackBar.show()

            }
        }
    }

    private fun showProgress(){
        binding.progressBar.visibility = View.VISIBLE
    }
    private fun hideProgress(){
        binding.progressBar.visibility = View.GONE
    }



    private fun setState(statusFavorite : Boolean){

        if (statusFavorite) {
            binding.apply {
                imgUnFav.visibility = View.GONE
                imgFav.visibility = View.VISIBLE
            }

        } else {
            binding.apply {
                imgFav.visibility = View.GONE
                imgUnFav.visibility = View.VISIBLE
            }

        }
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as MyApplication).appComponent.inject(this)
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}

