package ru.fishteck.movies_time.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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

class PopularMoviesFragment
    : Fragment(R.layout.fragment_popular_movies),
        PopularMoviesAdapter.MovieItemListener,
        GenresListAdapter.GenreItemListener
{

    private lateinit var moviesAdapter: PopularMoviesAdapter
    private lateinit var genresAdapter: GenresListAdapter
    private lateinit var moviesDTO: MoviesDTO
    private lateinit var genresDTO: GenresDTO
    private lateinit var diffUtilMovies: DiffUtilMovies

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initMoviesRecycler()
        initGenresRecycler()
        initMoviesDTO()
        initGenresDTO()
        diffUtilMovies = DiffUtilMovies(moviesAdapter.getData(), moviesDTO.getMovies())
        val diffResult : DiffUtil.DiffResult = DiffUtil.calculateDiff(diffUtilMovies)
        moviesAdapter.setItems(moviesDTO.getMovies())
        diffResult.dispatchUpdatesTo(moviesAdapter)
        genresAdapter.setItems(genresDTO.getGenres())
    }

    private fun initGenresDTO() {
        genresDTO = GenresDTO(GenresDataSourceImpl())
    }

    private fun initGenresRecycler() {
        genresAdapter = GenresListAdapter(this)
        val layoutManager : LinearLayoutManager = LinearLayoutManager(context)
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
        val layoutManager : GridLayoutManager = GridLayoutManager(context, 2)
        val recyclerView = view?.findViewById<RecyclerView>(R.id.popular_movies_list)
        recyclerView?.adapter = moviesAdapter
        recyclerView?.layoutManager = layoutManager
        recyclerView?.addItemDecoration(GridItemDecoration(10, 2))
    }

    override fun onClickedMovie(movieModel: MovieModel) {

        activity
                ?.supportFragmentManager
                ?.beginTransaction()
                ?.replace(R.id.main_nav_fragment,
                        MovieDetailsFragment.newInstance(movieModel),
                        MovieDetailsFragment.TAG)
                ?.addToBackStack(null)
                ?.commit()
    }



    override fun onClickedGenre(text: String) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    }

    companion object {
        const val TAG = "POPULAR_MOVIES_FRAGMENT_TAG"
        fun newInstance() = PopularMoviesFragment()
    }
}