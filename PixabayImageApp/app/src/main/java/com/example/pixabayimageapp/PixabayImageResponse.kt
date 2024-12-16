package com.example.pixabayimageapp


data class PixabayImageResponse(
    val hits: List<ImageResult>
)


data class ImageResult(
    val largeImageURL: String,
    val previewURL: String,
    val user: String,
    val likes: Int,
    val views: Int,
    val downloads: Int,
    val comments: Int,
    val tags: String
)
