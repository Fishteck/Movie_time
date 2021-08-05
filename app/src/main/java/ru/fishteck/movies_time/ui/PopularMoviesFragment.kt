package ru.fishteck.movies_time.ui

import android.os.Bundle
import android.util.Log
import android.view.View
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
import java.util.*


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
    private lateinit var coroutineExceptionHandler: CoroutineExceptionHandler
    private var list: List<MovieModel> = mutableListOf<MovieModel>()
    private var savedState: Bundle? = null
    private val SAVED_STATE_TAG = "savedState"
    private val SAVED_BUNDLE_TAG = "savedBundle"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
            showToast(throwable.javaClass.simpleName)
            refreshLayout.isRefreshing = false
        }

        initMoviesRecycler()
        initGenresRecycler()
        initMoviesDTO()
        initGenresDTO()
        initRefreshLayout(view)
        genresAdapter.setItems(genresDTO.getGenres())

        if (savedInstanceState != null && savedState == null) {
            savedState = savedInstanceState.getBundle(SAVED_BUNDLE_TAG)
        }

        if (savedState != null) {
            list = savedState?.getParcelableArrayList<MovieModel>(SAVED_STATE_TAG) as List<MovieModel>
            moviesAdapter.setItems(list)
        } else {
            downloadData()
        }
        savedState = null

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

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBundle(SAVED_BUNDLE_TAG, savedState ?: saveState())
    }

    private fun downloadData() = lifecycleScope.launch(coroutineExceptionHandler) {

        refreshLayout.isRefreshing = true
        withContext(Dispatchers.IO) {
            delay(2000)
            list = moviesDTO.getMovies()
        }

        withContext(Dispatchers.Main) {
            diffUtilMovies = DiffUtilMovies(moviesAdapter.getData(), list)
            val diffResult: DiffUtil.DiffResult = DiffUtil.calculateDiff(diffUtilMovies)
            moviesAdapter.setItems(list)
            diffResult.dispatchUpdatesTo(moviesAdapter)
        }
        refreshLayout.isRefreshing = false
    }

    override fun onDestroyView() {
        super.onDestroyView()
        savedState = saveState()
    }

    private fun saveState(): Bundle {
        val state = Bundle()
        state.putParcelableArrayList(SAVED_STATE_TAG, list as ArrayList<MovieModel>?)
        return state
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