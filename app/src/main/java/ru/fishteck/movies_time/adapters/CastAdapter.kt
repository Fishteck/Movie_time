package ru.fishteck.movies_time.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.fishteck.movies_time.R
import ru.fishteck.movies_time.data.models.CastDto
import ru.fishteck.movies_time.utils.downloadAndSetImage

class CastAdapter : RecyclerView.Adapter<CastHolder>() {

    private val items = mutableListOf<CastDto>()

    fun getData(): List<CastDto> = items

    fun setItems(items: List<CastDto>) {
        this.items.clear()
        this.items.addAll(items)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_actor, parent, false)
        return CastHolder(view)
    }

    override fun onBindViewHolder(holder: CastHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int =
        items.size
}

class CastHolder(private val view: View) : RecyclerView.ViewHolder(view) {
    private val image: ImageView = view.findViewById(R.id.item_actor_photo)
    private val title: TextView = view.findViewById(R.id.item_actor_name)

    fun bind(item: CastDto) {

        image.downloadAndSetImage(
            view.resources.getString(R.string.base_image_path_w200) + item.photo,
            view
        )
        title.text = item.name
    }
}