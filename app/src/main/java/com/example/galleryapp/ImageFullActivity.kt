package com.example.galleryapp


import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageSwitcher
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.galleryapp.databinding.ActivityImageFullBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.io.IOException


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

        val position = intent.getIntExtra("index",0)
        val currentImage =GalleryApplication.INSTANCE.imageList[position]

        val imagePath = currentImage.imagePath
        val imageName = currentImage.imageName

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

        binding.btnNext.setOnClickListener{

            if (position >= GalleryApplication.INSTANCE.imageList.size-1)
                return@setOnClickListener
            val intent = Intent (this@ImageFullActivity,ImageFullActivity::class.java)
            intent.putExtra("index",position+1)
            this@ImageFullActivity.startActivity(intent)
            this@ImageFullActivity.finish()



        }

        binding.btnPrevious.setOnClickListener {

            if (position <=0)
                return@setOnClickListener
            val intent = Intent (this@ImageFullActivity,ImageFullActivity::class.java)
            intent.putExtra("index",position-1)
            this@ImageFullActivity.startActivity(intent)
            this@ImageFullActivity.finish()



        }

    }

}









