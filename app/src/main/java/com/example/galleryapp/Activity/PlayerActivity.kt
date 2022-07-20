package com.example.galleryapp.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.galleryapp.R
import com.example.galleryapp.databinding.ActivityMainBinding
import com.example.galleryapp.databinding.ActivityPlayerBinding
import com.example.galleryapp.models.VideoData
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer
import java.util.*
import kotlin.collections.ArrayList

class PlayerActivity : AppCompatActivity() {
    private val binding: ActivityPlayerBinding by lazy { ActivityPlayerBinding.inflate(layoutInflater) }

    companion object{
        lateinit var player : SimpleTimeZone
        lateinit var playList:ArrayList<VideoData>
        var position : Int = -1
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initializeLayout()


    }

    private fun initializeLayout(){
        when(intent.getStringExtra("class")){
            "AllVideos" -> {
                playList = ArrayList()
                playList.addAll(FoldersActivity.currontFolderVideos)
            }
        }
        createPlayer()
    }

    private fun createPlayer(){
        val player = SimpleExoPlayer.Builder(this).build()
        binding.playerView.player = player
        val mediaItem = MediaItem.fromUri(MainActivity.videolist[position].artUri)
        player.setMediaItem(mediaItem)
        player.prepare()
        player.play()
    }

    override fun onDestroy() {
        super.onDestroy()
        player
    }
}