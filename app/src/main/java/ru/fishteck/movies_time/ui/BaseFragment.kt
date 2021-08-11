package ru.fishteck.movies_time.ui

import android.content.Context
import androidx.fragment.app.Fragment

abstract class BaseFragment(layout: Int) : Fragment(layout) {

    override fun onAttach(context: Context) {
        super.onAttach(context)
        numberOfAttachments++
        if ( numberOfAttachments == 1) {
            onFirstAttach()
        } else if (this.isHidden) {
            onFirstAttach()
        }
    }

    abstract fun onFirstAttach()

    companion object {
        private  var numberOfAttachments: Int = 0
    }
}
