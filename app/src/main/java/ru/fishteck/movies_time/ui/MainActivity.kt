package ru.fishteck.movies_time.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.fishteck.movies_time.*
import ru.fishteck.movies_time.adapters.GenresListAdapter
import ru.fishteck.movies_time.data.local.GenresDTO
import ru.fishteck.movies_time.data.local.GenresDataSourceImpl
import ru.fishteck.movies_time.data.local.MoviesDTO
import ru.fishteck.movies_time.data.local.MoviesDataSourceImpl
import ru.fishteck.movies_time.utils.DiffUtilMovies
import ru.fishteck.movies_time.utils.GridItemDecoration

class MainActivity : AppCompatActivity(), PopularMoviesAdapter.MovieItemListener, GenresListAdapter.GenreItemListener {

    private lateinit var moviesAdapter: PopularMoviesAdapter
    private lateinit var genresAdapter: GenresListAdapter
    private lateinit var moviesDTO: MoviesDTO
    private lateinit var genresDTO: GenresDTO
    private lateinit var diffUtilMovies: DiffUtilMovies

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_popular_movies)
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
        val layoutManager : LinearLayoutManager = LinearLayoutManager(this)
        layoutManager.orientation = RecyclerView.HORIZONTAL
        val recyclerView = findViewById<RecyclerView>(R.id.popular_movies_genres_list)
        recyclerView.adapter = genresAdapter
        recyclerView.layoutManager = layoutManager

    }

    private fun initMoviesDTO() {
        moviesDTO = MoviesDTO(MoviesDataSourceImpl())
    }

    private fun initMoviesRecycler() {
        moviesAdapter = PopularMoviesAdapter(this)
        val layoutManager : GridLayoutManager = GridLayoutManager(this, 2)
        val recyclerView = findViewById<RecyclerView>(R.id.popular_movies_list)
        recyclerView.adapter = moviesAdapter
        recyclerView.layoutManager = layoutManager
        recyclerView.addItemDecoration(GridItemDecoration(10, 2))
    }

    override fun onClickedMovie(title: String) {
        Toast.makeText(this, title, Toast.LENGTH_SHORT).show()
    }

    override fun onClickedGenre(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }
}