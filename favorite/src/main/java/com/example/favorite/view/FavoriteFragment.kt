package com.example.favorite.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chapstone.viewmodel.ViewModelFactory
import com.example.core.data.di.CoreComponent
import com.example.core.data.di.DaggerCoreComponent
import com.example.core.domain.model.MovieEntityModel
import com.example.core.utils.DataCallbackMovie
import com.example.favorite.databinding.FragmentFavoriteBinding
import com.example.favorite.di.DaggerFavoriteComponent
import com.example.favorite.viewmodel.FavoriteViewModel
import javax.inject.Inject

class FavoriteFragment : Fragment(), DataCallbackMovie {


    private var _binding: FragmentFavoriteBinding? = null
    private val binding  get() = _binding!!
    private val favoriteAdapter by lazy{ MovieFavoriteAdapter (this)}
    @Inject
    lateinit var factory : ViewModelFactory
    private val viewModel : FavoriteViewModel by viewModels{
        factory
    }
    private val coreComponent: CoreComponent by lazy {
        DaggerCoreComponent.factory().create(requireActivity())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvFavorite.adapter = this@FavoriteFragment.favoriteAdapter
        if (activity != null) {
            binding.progressBar.visibility = View.VISIBLE
            viewModel.listFavoriteMovie.observe(viewLifecycleOwner, Observer { favoriteMovie ->
                if (favoriteMovie.isNotEmpty()) {
                    hideProgress()
                    favoriteAdapter.setMovieFavorite(favoriteMovie)

                } else {
                    showProgress()
                }
            })
            initRecyclerView()

            binding.imgBack.setOnClickListener {
                findNavController().navigateUp()

            }
        }
    }

    private fun initRecyclerView(){

        with(binding.rvFavorite) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = favoriteAdapter
        }

    }
    private fun showProgress(){
        binding.apply {
            progressBar.visibility = View.GONE
            imgEmpty.visibility = View.VISIBLE
            tvEmpty.visibility = View.VISIBLE
            rvFavorite.visibility = View.GONE
        }
    }

    private fun hideProgress(){
        binding.apply {
            progressBar.visibility = View.GONE
            imgEmpty.visibility = View.GONE
            tvEmpty.visibility = View.GONE
            rvFavorite.visibility = View.VISIBLE
        }
    }


    override fun setDataMovie(data: MovieEntityModel) {
        val extraData = FavoriteFragmentDirections.actionFavoriteFragmentToDetailFragment(data)
        view?.findNavController()?.navigate(extraData)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        DaggerFavoriteComponent.builder().coreComponent(coreComponent).build().inject(this)

    }
    override fun onDestroyView() {
        binding.rvFavorite.adapter = null
        _binding = null
        super.onDestroyView()
    }
}