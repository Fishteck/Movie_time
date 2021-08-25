package ru.fishteck.movies_time.utils

import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import ru.fishteck.movies_time.R

fun ImageView.downloadAndSetImage(photoUrl: String, view: View) {

    Glide
        .with(view)
        .load(photoUrl)
        .placeholder(R.drawable.ic_launcher_foreground)
        .into(this)
}