package com.example.galleryapp

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import android.widget.SimpleAdapter
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.graphics.BlendModeColorFilterCompat
import androidx.core.graphics.BlendModeCompat
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.galleryapp.databinding.ActivityImageFullBinding
import com.example.galleryapp.databinding.ActivityMainBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ImageFullActivity : AppCompatActivity() {

    var imgFullScreen:ImageView?=null

    private val btnShare :FloatingActionButton by lazy {
        binding.btnShare
    }

    private lateinit var binding: ActivityImageFullBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityImageFullBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        imgFullScreen = binding.imageView

        val imagePath = intent.getStringExtra("path")
        val imageName = intent.getStringExtra("name")

            supportActionBar?.setTitle(imageName)
            Glide.with(this@ImageFullActivity)
                .load(imagePath)
                .into(binding.imageView)
            binding.btnShare.setOnClickListener {v: View? ->
                val bitmapDrawable = imgFullScreen!!.drawable as BitmapDrawable
                val bitmap = bitmapDrawable.bitmap
                val bitmapPath = MediaStore.Images.Media.insertImage(contentResolver,bitmap,"some title",null)
                val bitmapUri = Uri.parse(bitmapPath)
                val intent = Intent(Intent.ACTION_SEND)
                intent.type="image/*"
                intent.putExtra(Intent.EXTRA_STREAM,bitmapUri)
                           startActivity(Intent.createChooser(intent,"Share inmge"))


            }

        binding.next.setOnClickListener{

        }

        binding.previous.setOnClickListener {

        }



        }
    }







