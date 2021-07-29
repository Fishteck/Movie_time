package ru.fishteck.movies_time.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import ru.fishteck.movies_time.R

class ProfileFragment : Fragment(R.layout.fragment_profile) {

    companion object {
        const val TAG = "PROFILE_FRAGMENT_TAG"
        fun newInstance() = ProfileFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}