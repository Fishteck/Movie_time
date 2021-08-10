package ru.fishteck.movies_time.utils

import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import dagger.MapKey
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KClass


fun Fragment.showToast(message: String?) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}

