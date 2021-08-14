package ru.fishteck.movies_time.ui.movies

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import ru.fishteck.appComponent
import ru.fishteck.movies_time.PopularMoviesAdapter
import ru.fishteck.movies_time.R
import ru.fishteck.movies_time.adapters.GenresListAdapter
import ru.fishteck.movies_time.data.models.MovieModel
import ru.fishteck.movies_time.di.ViewModelFactory
import ru.fishteck.movies_time.ui.moviedetails.MovieDetailsFragment
import ru.fishteck.movies_time.utils.*
import javax.inject.Inject

class PopularMoviesFragment
    : Fragment(R.layout.fragment_popular_movies),
        PopularMoviesAdapter.MovieItemListener,
        GenresListAdapter.GenreItemListener {

    private val moviesAdapter: PopularMoviesAdapter = PopularMoviesAdapter(this)
    private lateinit var genresAdapter: GenresListAdapter
    private lateinit var diffUtilMovies: DiffUtilMovies
    private lateinit var refreshLayout: SwipeRefreshLayout

    @Inject
    lateinit var factory: ViewModelFactory
    private val popularMoviesViewModel:
            PopularMoviesViewModel by navGraphViewModels<PopularMoviesViewModel>(R.id.menu_home)
    { factory }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initMoviesRecycler()
        initGenresRecycler()
        initRefreshLayout(view)
        genresAdapter.setItems(popularMoviesViewModel.getGenres())
        initObserver()
    }

    private fun setRecyclerData(value: List<MovieModel>) {
        diffUtilMovies = DiffUtilMovies(moviesAdapter.getData(), value)
        val diffResult: DiffUtil.DiffResult = DiffUtil.calculateDiff(diffUtilMovies)
        moviesAdapter.setItems(value)
        diffResult.dispatchUpdatesTo(moviesAdapter)
    }

    private fun initObserver() {
        popularMoviesViewModel.popularMoviesState.observe(viewLifecycleOwner, { state ->
            when (state) {
                is DataState.Success -> {
                    setRecyclerData(state.data)
                    refreshLayout.isRefreshing = false
                }
                is DataState.Error -> {
                    showToast(state.message)
                    refreshLayout.isRefreshing = false
                }
                is DataState.Loading -> {
                    refreshLayout.isRefreshing = true
                }
            }
        })
    }

    override fun onAttach(context: Context) {
        context.appComponent.inject(this)
        super.onAttach(context)
    }

    private fun initRefreshLayout(view: View) {
        refreshLayout = view.findViewById(R.id.popular_movies_refresh_layout)
        refreshLayout.setOnRefreshListener {
            popularMoviesViewModel.updateMovies()
        }
    }

    private fun initGenresRecycler() {
        genresAdapter = GenresListAdapter(this)
        val layoutManager: LinearLayoutManager = LinearLayoutManager(context)
        layoutManager.orientation = RecyclerView.HORIZONTAL
        val recyclerView = view?.findViewById<RecyclerView>(R.id.popular_movies_genres_list)
        recyclerView?.adapter = genresAdapter
        recyclerView?.layoutManager = layoutManager

    }

    private fun initMoviesRecycler() {
        val layoutManager: GridLayoutManager = GridLayoutManager(context, 2)
        val recyclerView = view?.findViewById<RecyclerView>(R.id.popular_movies_list)
        recyclerView?.adapter = moviesAdapter
        recyclerView?.layoutManager = layoutManager
        recyclerView?.addItemDecoration(GridItemDecoration(10, 2))
    }

    override fun onClickedMovie(movieId: Int) {
        findNavController().navigate(
                R.id.action_popularMoviesFragment_to_movieDetailsFragment,
                bundleOf(MovieDetailsFragment.ARG_MOVIE_ID to movieId)
        )
    }

    override fun onClickedGenre(text: String) {
        showToast(text)
    }


}