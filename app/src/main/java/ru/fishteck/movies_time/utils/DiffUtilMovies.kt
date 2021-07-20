package ru.fishteck.movies_time.utils

import androidx.recyclerview.widget.DiffUtil
import ru.fishteck.movies_time.data.models.MovieModel

class DiffUtilMovies(
    private val  oldList : List<MovieModel>,
    private val  newList : List<MovieModel>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
         val oldMovie : MovieModel = oldList[oldItemPosition]
         val newMovie : MovieModel = newList[newItemPosition]
        return oldMovie.id == newMovie.id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldMovie : MovieModel = oldList[oldItemPosition]
        val newMovie : MovieModel = newList[newItemPosition]
        return oldMovie.title == newMovie.title && oldMovie.description == newMovie.description
    }
}