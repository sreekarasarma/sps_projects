package com.example.pixabayimageapp

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ImageAdapter(private val context: Context) : ListAdapter<ImageResult, ImageAdapter.ImageViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_card, parent, false)
        return ImageViewHolder(view)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val image = getItem(position)
        holder.bind(image)
    }

    inner class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageView: ImageView = itemView.findViewById(R.id.ivImage)
        private val userName: TextView = itemView.findViewById(R.id.tvUserName)

        fun bind(image: ImageResult) {
            Glide.with(itemView.context).load(image.previewURL).into(imageView)
            userName.text = image.user

            itemView.setOnClickListener {
                val intent = Intent(context, ImageDetailActivity::class.java)
                intent.putExtra("imageUrl", image.largeImageURL)
                intent.putExtra("user", image.user)
                intent.putExtra("likes", image.likes)
                intent.putExtra("views", image.views)
                intent.putExtra("downloads", image.downloads)
                intent.putExtra("comments", image.comments)
                intent.putExtra("tags", image.tags)
                context.startActivity(intent)
            }

        }
    }

    class DiffCallback : DiffUtil.ItemCallback<ImageResult>() {
        override fun areItemsTheSame(oldItem: ImageResult, newItem: ImageResult): Boolean {
            return oldItem.previewURL == newItem.previewURL
        }

        override fun areContentsTheSame(oldItem: ImageResult, newItem: ImageResult): Boolean {
            return oldItem == newItem
        }
    }
}
