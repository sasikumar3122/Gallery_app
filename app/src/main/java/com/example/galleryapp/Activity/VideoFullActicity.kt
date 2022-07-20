package com.example.galleryapp.Activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.MediaController
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import com.example.galleryapp.R
import com.example.galleryapp.databinding.ActivityVideoFullBinding




class VideoFullActicity:AppCompatActivity() {

//    private var position = 0
    private val binding: ActivityVideoFullBinding by lazy {
        ActivityVideoFullBinding.inflate(
            layoutInflater
        )
    }
//    var videoView : VideoView? = null
//    var mediaController : MediaController? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

//        videoView = findViewById<View>(R.id.videoView)as VideoView
//        if (mediaController == null){
//            mediaController = MediaController(this)
//            mediaController!!.setAnchorView(this.videoView)
//
//        }
//
//        videoView!!.setMediaController(mediaController)
//        videoView!!.setVideoURI(Uri.parse("android.resource://"+packageManager+"/"))
//        videoView!!.requestFocus()
//        videoView!!.start()
    }

    override fun onBackPressed() {
        val intent = Intent(Intent.ACTION_MAIN)
        startActivity(intent)
        super.onBackPressed()

    }



}