package ru.fishteck.movies_time

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.fishteck.movies_time.models.Movie
import ru.fishteck.movies_time.utils.downloadAndSetImage


class PopularMoviesAdapter(private val listener: MovieItemListener) :
        RecyclerView.Adapter<PopularMoviesHolder>() {

    interface MovieItemListener {
        fun onClickedMovie(movieId: Int, image: ImageView, imageUrl: String)
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
        setScaleAnimation(holder.itemView)
    }

    override fun getItemCount(): Int = items.size

    private fun setScaleAnimation(view: View) {
        val anim = ScaleAnimation(
            0.0f,
            1.0f,
            0.0f,
            1.0f,
            Animation.RELATIVE_TO_SELF,
            0.5f,
            Animation.RELATIVE_TO_SELF,
            0.5f
        )
        anim.duration = 1000
        view.startAnimation(anim)
    }
}

class PopularMoviesHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    private val image: ImageView = view.findViewById(R.id.item_movie_poster_image)
    private val title: TextView = view.findViewById(R.id.item_movie_poster_title)
    private val description: TextView = view.findViewById(R.id.item_movie_poster_description)
    private val ageLimit: TextView = view.findViewById(R.id.item_movie_age_limit)
    private val ratingBar: RatingBar = view.findViewById(R.id.item_movie_rating_bar)

    fun bind(item: Movie, listener: PopularMoviesAdapter.MovieItemListener) {
        image.apply { transitionName = item.imageUrl }
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
            item.imageUrl?.let { it1 -> listener.onClickedMovie(movieId = item.id, image, it1) }
        }

    }


}