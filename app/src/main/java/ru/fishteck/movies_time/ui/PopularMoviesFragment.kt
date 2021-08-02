package ru.fishteck.movies_time.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import kotlinx.coroutines.*
import ru.fishteck.movies_time.PopularMoviesAdapter
import ru.fishteck.movies_time.R
import ru.fishteck.movies_time.adapters.GenresListAdapter
import ru.fishteck.movies_time.data.local.GenresDTO
import ru.fishteck.movies_time.data.local.GenresDataSourceImpl
import ru.fishteck.movies_time.data.local.MoviesDTO
import ru.fishteck.movies_time.data.local.MoviesDataSourceImpl
import ru.fishteck.movies_time.data.models.MovieModel
import ru.fishteck.movies_time.utils.DiffUtilMovies
import ru.fishteck.movies_time.utils.GridItemDecoration
import ru.fishteck.movies_time.utils.showToast
import java.lang.Exception

class PopularMoviesFragment
    : Fragment(R.layout.fragment_popular_movies),
    PopularMoviesAdapter.MovieItemListener,
    GenresListAdapter.GenreItemListener {

    private lateinit var moviesAdapter: PopularMoviesAdapter
    private lateinit var genresAdapter: GenresListAdapter
    private lateinit var moviesDTO: MoviesDTO
    private lateinit var genresDTO: GenresDTO
    private lateinit var diffUtilMovies: DiffUtilMovies
    private lateinit var refreshLayout: SwipeRefreshLayout

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initMoviesRecycler()
        initGenresRecycler()
        initMoviesDTO()
        initGenresDTO()
        initRefreshLayout(view)

        downloadData()

        genresAdapter.setItems(genresDTO.getGenres())
        clearFragmentBackStack()
    }

    private fun initRefreshLayout(view: View) {
        refreshLayout = view.findViewById(R.id.popular_movies_refresh_layout)
        refreshLayout.setOnRefreshListener {
            downloadData()
        }
    }

    private fun initGenresDTO() {
        genresDTO = GenresDTO(GenresDataSourceImpl())
    }

    private fun downloadData() = lifecycleScope.launch {
        var list: List<MovieModel> = mutableListOf<MovieModel>()
        refreshLayout.isRefreshing = true
        withContext(Dispatchers.IO) {
            delay(4000)
            try {
                list = moviesDTO.getMovies()
            } catch (ex: Exception) {
                showToast(ex.message)
            }
        }

        withContext(Dispatchers.Main) {
            diffUtilMovies = DiffUtilMovies(moviesAdapter.getData(), list)
            val diffResult: DiffUtil.DiffResult = DiffUtil.calculateDiff(diffUtilMovies)
            moviesAdapter.setItems(list)
            diffResult.dispatchUpdatesTo(moviesAdapter)
        }
        refreshLayout.isRefreshing = false
    }


    private fun initGenresRecycler() {
        genresAdapter = GenresListAdapter(this)
        val layoutManager: LinearLayoutManager = LinearLayoutManager(context)
        layoutManager.orientation = RecyclerView.HORIZONTAL
        val recyclerView = view?.findViewById<RecyclerView>(R.id.popular_movies_genres_list)
        recyclerView?.adapter = genresAdapter
        recyclerView?.layoutManager = layoutManager

    }

    private fun initMoviesDTO() {
        moviesDTO = MoviesDTO(MoviesDataSourceImpl())
    }

    private fun initMoviesRecycler() {
        moviesAdapter = PopularMoviesAdapter(this)
        val layoutManager: GridLayoutManager = GridLayoutManager(context, 2)
        val recyclerView = view?.findViewById<RecyclerView>(R.id.popular_movies_list)
        recyclerView?.adapter = moviesAdapter
        recyclerView?.layoutManager = layoutManager
        recyclerView?.addItemDecoration(GridItemDecoration(10, 2))
    }

    private fun clearFragmentBackStack() {
        activity?.supportFragmentManager?.backStackEntryCount?.let {
            repeat(it) {
                activity?.supportFragmentManager?.popBackStack()
            }
        }
    }

    override fun onClickedMovie(movieModel: MovieModel) {

        activity
            ?.supportFragmentManager
            ?.beginTransaction()
            ?.replace(
                R.id.main_nav_fragment,
                MovieDetailsFragment.newInstance(movieModel),
                MovieDetailsFragment.TAG
            )
            ?.addToBackStack(null)
            ?.commit()
    }

    override fun onClickedGenre(text: String) {
        showToast(text)
    }

    companion object {
        const val TAG = "POPULAR_MOVIES_FRAGMENT_TAG"
        fun newInstance() = PopularMoviesFragment()
    }


}