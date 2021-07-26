package ru.fishteck.movies_time.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import ru.fishteck.movies_time.R

class MovieDetailsFragment : Fragment(R.layout.fragment_movie_details) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    companion object {
        const val TAG = "MOVIE_DETAILS_FRAGMENT_TAG"
        fun newInstance() = MovieDetailsFragment()
    }
}