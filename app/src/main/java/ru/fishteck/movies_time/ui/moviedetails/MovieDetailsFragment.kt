package ru.fishteck.movies_time.ui.moviedetails

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DiffUtil
import com.bumptech.glide.Glide
import ru.fishteck.appComponent
import ru.fishteck.movies_time.R
import ru.fishteck.movies_time.data.models.MovieModel
import ru.fishteck.movies_time.utils.DataState
import ru.fishteck.movies_time.utils.DiffUtilMovies
import ru.fishteck.movies_time.utils.showToast
import javax.inject.Inject

class MovieDetailsFragment : Fragment(R.layout.fragment_movie_details) {

    private lateinit var poster: ImageView
    private lateinit var title: TextView
    private lateinit var description: TextView
    private lateinit var ageLimit: TextView
    private lateinit var ratingBar: RatingBar
    private var movieId: Int = 0
    @Inject
    lateinit var factory: MovieDetailsViewModelFactory.Factory
    private val movieDetailsViewModel: MovieDetailsViewModel by viewModels {
        factory.create(movieId)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setFields(view)
        initObserver()
    }

    private fun initObserver() {
        movieDetailsViewModel.movieDetailState.observe(viewLifecycleOwner, { state ->
            when(state) {
                is DataState.Success -> {
                    setData(state.data)
                }
                is DataState.Error -> {
                    showToast(state.message)
                }
                is DataState.Loading -> {

                }
            }
        })
    }

    private fun setData(data: MovieModel) {
        view?.let {
            Glide
                .with(it)
                .load(data.imageUrl)
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(poster)
        }
        title.text = data.title
        description.text = data.description
        ageLimit.text = data.ageRestriction.toString() + "+"
        ratingBar.rating = data.rateScore.toFloat()
    }

    override fun onAttach(context: Context) {
        context.appComponent.inject(this)
        super.onAttach(context)
        movieId = requireArguments().getInt(ARG_MOVIE_ID)
    }

    private fun setFields(view: View) {
        poster = view.findViewById(R.id.movie_details_poster)
        title = view.findViewById(R.id.movie_details_title)
        description = view.findViewById(R.id.movie_details_description)
        ageLimit = view.findViewById(R.id.movie_details_age_limit)
        ratingBar = view.findViewById(R.id.movie_details_rating_bar)
    }

    companion object {
        const val ARG_MOVIE_ID = "movie_id"
    }
}