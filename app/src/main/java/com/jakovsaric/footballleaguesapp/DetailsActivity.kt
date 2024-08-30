package com.jakovsaric.footballleaguesapp

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import coil.ImageLoader
import coil.decode.SvgDecoder
import coil.request.ImageRequest

class DetailsActivity : AppCompatActivity() {

    private lateinit var imageLoader: ImageLoader

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val name = intent.getStringExtra("name")
        val flagUrl = intent.getStringExtra("flag")
        val leagueDesc = "First tier (or most popular league):"
        val leagueName = intent.getStringExtra("leagueName")
        val emblemUrl = intent.getStringExtra("emblem")
        val btBack: Button = findViewById(R.id.btBack)

        Log.e("EMBLEM ERROR", emblemUrl ?: "No emblem URL")

        val tvName: TextView = findViewById(R.id.tvName)
        val ivFlag: ImageView = findViewById(R.id.ivFlag)
        val tvLeagueDesc: TextView = findViewById(R.id.tvLeagueDesc)
        val tvLeagueName: TextView = findViewById(R.id.tvLeagueName)
        val ivLeagueLogo: ImageView = findViewById(R.id.ivLeagueLogo)

        tvName.text = name
        tvLeagueDesc.text = leagueDesc
        tvLeagueName.text = leagueName

        // Initialize ImageLoader with SVG support
        imageLoader = ImageLoader.Builder(this)
            .components {
                add(SvgDecoder.Factory())
            }
            .build()

        flagUrl?.let {
            loadImage(it, ivFlag)
        }

        emblemUrl?.let {
            loadImage(it, ivLeagueLogo)
        } ?: Log.e("Error", "Nije učitano!!!")

        btBack.setOnClickListener{
            finish()
        }
    }

    private fun loadImage(url: String, imageView: ImageView) {
        val request = ImageRequest.Builder(this)
            .data(url)
            .target(imageView)
            .placeholder(R.drawable.placeholder)
            .error(R.drawable.error_placeholder)
            .build()

        imageLoader.enqueue(request)
    }
}
