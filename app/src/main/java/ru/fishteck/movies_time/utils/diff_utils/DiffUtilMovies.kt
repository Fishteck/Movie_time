package ru.fishteck.movies_time.utils.diff_utils

import androidx.recyclerview.widget.DiffUtil
import ru.fishteck.movies_time.data.models.MovieDto
import ru.fishteck.movies_time.models.Movie

class DiffUtilMovies(
        private val  oldList : List<Movie>,
        private val  newList : List<Movie>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
         val oldMovie : Movie = oldList[oldItemPosition]
         val newMovie : Movie = newList[newItemPosition]
        return oldMovie.id == newMovie.id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldMovie : Movie = oldList[oldItemPosition]
        val newMovie : Movie = newList[newItemPosition]
        return oldMovie.title == newMovie.title && oldMovie.description == newMovie.description
    }
}