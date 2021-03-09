package com.st18apps.giphytest.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.st18apps.giphytest.R
import com.st18apps.giphytest.data.model.Data
import kotlinx.android.synthetic.main.item_layout.view.*

class DataAdapter(private val data: ArrayList<Data>) :
    RecyclerView.Adapter<DataAdapter.DataViewHolder>() {

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(data: Data) {
            val imageViewGif = itemView.imageViewGif

            Glide.with(imageViewGif.context)
                .load(data.images.downsized.url)
                .placeholder(R.drawable.progress_anim)
                .into(imageViewGif)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder =
        DataViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        )

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(data[position])
    }

    fun addData(data: List<Data>) {
        this.data.apply {
            clear()
            addAll(data)
        }

    }
}