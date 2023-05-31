package com.example.areeba

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.chrisbanes.photoview.PhotoView
import com.squareup.picasso.Picasso
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class ImageZoomActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_zoom)

        val imageUrl = intent.getStringExtra("imageUrl")
        val photoView: PhotoView = findViewById(R.id.zoomed_image)

        Picasso.get().load(imageUrl).into(photoView)

        Glide.with(this)
            .load(imageUrl)
            .apply(RequestOptions().fitCenter())
            .into(photoView)
    }
}
