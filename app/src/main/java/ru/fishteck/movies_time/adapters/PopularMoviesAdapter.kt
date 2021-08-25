package ru.fishteck.movies_time

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.fishteck.movies_time.models.Movie
import ru.fishteck.movies_time.utils.downloadAndSetImage

class PopularMoviesAdapter(private val listener: MovieItemListener) :
        RecyclerView.Adapter<PopularMoviesHolder>() {

    interface MovieItemListener {
        fun onClickedMovie(movieId: Int)
    }

    private val items = mutableListOf<Movie>()

    fun getData(): List<Movie> = items

    fun setItems(items: List<Movie>) {
        this.items.clear()
        this.items.addAll(items)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularMoviesHolder {
        val view = LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_movie, parent, false)
        return PopularMoviesHolder(view)
    }

    override fun onBindViewHolder(holder: PopularMoviesHolder, position: Int) {

        holder.bind(items[position], listener)
    }

    override fun getItemCount(): Int = items.size
}

class PopularMoviesHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    private val image: ImageView = view.findViewById(R.id.item_movie_poster_image)
    private val title: TextView = view.findViewById(R.id.item_movie_poster_title)
    private val description: TextView = view.findViewById(R.id.item_movie_poster_description)
    private val ageLimit: TextView = view.findViewById(R.id.item_movie_age_limit)
    private val ratingBar: RatingBar = view.findViewById(R.id.item_movie_rating_bar)


    fun bind(item: Movie, listener: PopularMoviesAdapter.MovieItemListener) {

        item.imageUrl?.let {
            image.downloadAndSetImage(
                    it,
                    view
            )
        }
        title.text = item.title
        description.text = item.description
        ageLimit.text = item.ageRestriction
        ratingBar.rating = item.rateScore
        view.setOnClickListener {
            listener.onClickedMovie(movieId = item.id)
        }
    }


}