package com.example.pixabayimageapp

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class ImageDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_detail)

        val imageView: ImageView = findViewById(R.id.ivFullImage)
        val textViewDescription: TextView = findViewById(R.id.tvDescription)
        val textViewLikes: TextView = findViewById(R.id.tvLikes)
        val textViewViews: TextView = findViewById(R.id.tvViews)
        val textViewDownloads: TextView = findViewById(R.id.tvDownloads)
        val textViewComments: TextView = findViewById(R.id.tvComments)
        val textViewTags: TextView = findViewById(R.id.tvTags)

        // Getting the Intent data
        val imageUrl = intent.getStringExtra("imageUrl")
        val description = intent.getStringExtra("user")
        val likes = intent.getIntExtra("likes", 0)
        val views = intent.getIntExtra("views", 0)
        val downloads = intent.getIntExtra("downloads", 0)
        val comments = intent.getIntExtra("comments", 0)
        val tags = intent.getStringExtra("tags")

        // Loading the image using Glide
        Glide.with(this).load(imageUrl).into(imageView)

        // Setting the text fields
        textViewDescription.text = description
        textViewLikes.text = "Likes: $likes"
        textViewViews.text = "Views: $views"
        textViewDownloads.text = "Downloads: $downloads"
        textViewComments.text = "Comments: $comments"
        textViewTags.text = "Tags: $tags"
    }
}
