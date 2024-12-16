package com.example.pixabayimageapp

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var rvImages: RecyclerView
    private lateinit var adapter: ImageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvImages = findViewById(R.id.rvImages)
        setupRecyclerView()
        fetchImages()
    }

    private fun setupRecyclerView() {
        rvImages.layoutManager = LinearLayoutManager(this)
        adapter = ImageAdapter(this)
        rvImages.adapter = adapter
    }

    private fun fetchImages() {
        val apiKey = getString(R.string.pixabay_api_key)
        val api = RetrofitClient.getInstance().create(PixabayApi::class.java)

        api.getImages(apiKey, "nature").enqueue(object : Callback<PixabayImageResponse> {
            override fun onResponse(call: Call<PixabayImageResponse>, response: Response<PixabayImageResponse>) {
                if (response.isSuccessful) {
                    val images = response.body()?.hits ?: emptyList()
                    adapter.submitList(images)
                } else {
                    Toast.makeText(this@MainActivity, "Failed to load images", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<PixabayImageResponse>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
