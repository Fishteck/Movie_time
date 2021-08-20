package ru.fishteck.movies_time.utils.diff_utils

import androidx.recyclerview.widget.DiffUtil
import ru.fishteck.movies_time.data.models.CastDto

class DiffUtilCast(
        private val  oldList : List<CastDto>,
        private val  newList : List<CastDto>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldCast : CastDto = oldList[oldItemPosition]
        val newCast : CastDto = newList[newItemPosition]
        return oldCast.id == newCast.id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldCast : CastDto = oldList[oldItemPosition]
        val newCast : CastDto = newList[newItemPosition]
        return oldCast.photo == newCast.photo && oldCast.name == newCast.name
    }
}