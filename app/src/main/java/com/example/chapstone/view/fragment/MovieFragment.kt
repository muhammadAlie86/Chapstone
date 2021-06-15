package com.example.chapstone.view.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chapstone.MyApplication
import com.example.chapstone.R
import com.example.chapstone.adapter.MovieAdapter
import com.example.chapstone.databinding.FragmentMovieBinding
import com.example.chapstone.viewmodel.MovieViewModel
import com.example.chapstone.viewmodel.ViewModelFactory
import com.example.core.domain.model.MovieEntityModel
import com.example.core.utils.DataCallbackMovie
import com.example.core.vo.Resource
import javax.inject.Inject


class MovieFragment : Fragment(), DataCallbackMovie {


    @Inject
    lateinit var factory : ViewModelFactory

    private var _binding: FragmentMovieBinding? = null
    private val binding  get() = _binding!!
    private lateinit var movieAdapter : MovieAdapter
    private val viewModel : MovieViewModel by viewModels{
        factory
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            viewModel.movie.observe(viewLifecycleOwner, Observer { movie ->
                when (movie) {
                    is Resource.Loading -> showProgress()
                    is Resource.Success -> {
                        hideProgress()
                        movie.data?.let { dataMovie ->
                            movieAdapter.setMovie(dataMovie)
                        }
                    }
                    is Resource.Error -> errorMessage()
                }
            })
            initRecyclerView()
            binding.btnFav.setOnClickListener {
                view.findNavController().navigate(R.id.action_movieFragment_to_favoriteFragment)
            }
        }
    }
    private fun initRecyclerView(){

        movieAdapter = MovieAdapter(this)
        with(binding.rvMovie) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = movieAdapter
        }
    }
    private fun showProgress(){
        binding.progressBar.visibility = View.VISIBLE
    }
    private fun hideProgress(){
        binding.progressBar.visibility = View.GONE
    }
    private fun errorMessage(){
        Toast.makeText(requireActivity(),resources.getString(R.string.error_message), Toast.LENGTH_SHORT).show()

    }

    override fun setDataMovie(data: MovieEntityModel) {
        val extraData = MovieFragmentDirections.actionMovieFragmentToDetailFragment(data)
        view?.findNavController()?.navigate(extraData)
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