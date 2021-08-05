package ru.fishteck.movies_time.ui

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import ru.fishteck.movies_time.R
import ru.fishteck.movies_time.data.models.MovieModel

class MovieDetailsFragment : Fragment(R.layout.fragment_movie_details) {

    private lateinit var poster : ImageView
    private lateinit var title : TextView
    private lateinit var description : TextView
    private lateinit var ageLimit : TextView
    private lateinit var ratingBar: RatingBar

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setFields(view)

        val movie = arguments?.get("Movie") as MovieModel
        Glide
                .with(view)
                .load(movie.imageUrl)
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(poster)
        title.text = movie.title
        description.text = movie.description
        ageLimit.text = movie.ageRestriction.toString() + "+"
        ratingBar.rating = movie.rateScore.toFloat()
    }

    private fun setFields(view: View) {
        poster = view.findViewById(R.id.movie_details_poster)
        title = view.findViewById(R.id.movie_details_title)
        description = view.findViewById(R.id.movie_details_description)
        ageLimit = view.findViewById(R.id.movie_details_age_limit)
        ratingBar = view.findViewById(R.id.movie_details_rating_bar)
    }

    companion object {
        const val TAG = "MOVIE_DETAILS_FRAGMENT_TAG"
        fun newInstance(movie : MovieModel) = MovieDetailsFragment().apply {
            arguments = Bundle().apply {
                putParcelable("Movie", movie)
            }
        }
    }
}