package ru.fishteck.movies_time.ui.moviedetails

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import ru.fishteck.appComponent
import ru.fishteck.movies_time.R
import ru.fishteck.movies_time.adapters.CastAdapter
import ru.fishteck.movies_time.data.models.CastDto
import ru.fishteck.movies_time.models.DetailMovie
import ru.fishteck.movies_time.utils.*
import ru.fishteck.movies_time.utils.diff_utils.DiffUtilCast
import ru.fishteck.movies_time.utils.states.DataState
import javax.inject.Inject

class MovieDetailsFragment : Fragment(R.layout.fragment_movie_details) {

    private lateinit var poster: ImageView
    private lateinit var title: TextView
    private lateinit var description: TextView
    private lateinit var ageLimit: TextView
    private lateinit var ratingBar: RatingBar
    private lateinit var genre: TextView
    private var movieId: Int = 0

    @Inject
    lateinit var factory: MovieDetailsViewModelFactory.Factory
    private val movieDetailsViewModel: MovieDetailsViewModel by viewModels {
        factory.create(movieId)
    }
    private val castAdapter = CastAdapter()
    private lateinit var diffUtilCast: DiffUtilCast

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setBottomSheet(view)
        setFields(view)
        initObserver()
        initRecyclerView(view)
    }

    private fun initRecyclerView(view: View) {
        val recyclerView = view.findViewById<RecyclerView>(R.id.movie_details_actors_recycler_view)
        recyclerView.adapter = castAdapter
    }

    private fun setRecyclerData(value: List<CastDto>) {
        diffUtilCast = DiffUtilCast(castAdapter.getData(), value)
        val diffResult: DiffUtil.DiffResult = DiffUtil.calculateDiff(diffUtilCast)
        castAdapter.setItems(value)
        diffResult.dispatchUpdatesTo(castAdapter)
    }

    private fun setBottomSheet(view: View) {
        val bottomSheetBehavior =
            BottomSheetBehavior.from<NestedScrollView>(view.findViewById(R.id.bottom_sheet_container))
        bottomSheetBehavior.isHideable = false
        bottomSheetBehavior.addBottomSheetCallback(CustomBottomSheetCallback())
    }

    private fun initObserver() {
        movieDetailsViewModel.movieDetailState.observe(viewLifecycleOwner, { state ->
            when (state) {
                is DataState.Success -> {
                    setData(state.data)
                    setRecyclerData(state.data.cast)
                }
                is DataState.Error -> {
                    showToast(state.message)
                }
                is DataState.Loading -> {

                }
            }
        })
    }

    private fun setData(data: DetailMovie) {
        view?.let {
            data.imageUrl?.let { it1 ->
                poster.downloadAndSetImage(
                    it1,
                    it
                )
            }
        }

        title.text = data.title
        description.text = data.description
        ratingBar.rating = data.rateScore
        genre.text = data.genre[0].name
        ageLimit.text = data.ageRestriction
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
        genre = view.findViewById(R.id.movie_details_genre)
    }

    companion object {
        const val ARG_MOVIE_ID = "movie_id"
    }
}