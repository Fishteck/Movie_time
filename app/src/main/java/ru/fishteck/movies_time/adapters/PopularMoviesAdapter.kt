package ru.fishteck.movies_time

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import jp.wasabeef.glide.transformations.RoundedCornersTransformation
import ru.fishteck.movies_time.data.models.MovieModel

class PopularMoviesAdapter(private val listener: MovieItemListener) : RecyclerView.Adapter<PopularMoviesHolder>() {

    interface MovieItemListener {
        fun onClickedMovie(title : String)
    }

    private val items = mutableListOf<MovieModel>()

    fun getData() : List<MovieModel> = items

    fun setItems(items : List<MovieModel>) {
        this.items.clear()
        this.items.addAll(items)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularMoviesHolder {
        val view = LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_movie, parent, false)
        return PopularMoviesHolder(view, listener)
    }

    override fun onBindViewHolder(holder: PopularMoviesHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size
}

class PopularMoviesHolder ( private val view : View,
                            private val listener : PopularMoviesAdapter.MovieItemListener)
    : RecyclerView.ViewHolder(view), View.OnClickListener {

    private val image : ImageView = view.findViewById(R.id.item_movie_poster_image)
    private val title : TextView = view.findViewById(R.id.item_movie_poster_title)
    private val description : TextView = view.findViewById(R.id.item_movie_poster_description)
    private val ageLimit : TextView = view.findViewById(R.id.item_movie_age_limit)
    private val ratingBar : RatingBar = view.findViewById(R.id.item_movie_rating_bar)

    private lateinit var movie : MovieModel

    init {
        view.setOnClickListener(this)
    }

    fun bind(item : MovieModel) {
        this.movie = item
        Glide
                .with(view)
                .load(movie.imageUrl)
                .centerCrop()
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(image)
        title.text = movie.title
        description.text = movie.description
        ageLimit.text = movie.ageRestriction.toString() + "+"
        ratingBar.rating = movie.rateScore.toFloat()
    }

    override fun onClick(v: View?) {
        listener.onClickedMovie(title = movie.title)
    }
}