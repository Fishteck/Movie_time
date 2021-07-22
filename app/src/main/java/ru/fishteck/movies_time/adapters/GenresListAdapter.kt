package ru.fishteck.movies_time.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.fishteck.movies_time.R
import ru.fishteck.movies_time.data.models.GenreModel

class GenresListAdapter(private val listener: GenreItemListener) : RecyclerView.Adapter<GenresListHolder>(){

    interface GenreItemListener {
        fun onClickedGenre(text : String)
    }

    private val items = mutableListOf<GenreModel>()

    fun setItems(items : List<GenreModel>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenresListHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_genre, parent, false)
        return GenresListHolder(view)
    }

    override fun onBindViewHolder(holder: GenresListHolder, position: Int) {
        holder.bind(items[position], listener)
    }

    override fun getItemCount(): Int = items.size
}

class GenresListHolder(private val view : View)
    : RecyclerView.ViewHolder(view){

    private val text : TextView = view.findViewById(R.id.item_genre_text)

    fun bind(item : GenreModel, listener: GenresListAdapter.GenreItemListener) {
        text.text = item.name
        view.setOnClickListener {
            listener.onClickedGenre(item.name)
        }
    }




}