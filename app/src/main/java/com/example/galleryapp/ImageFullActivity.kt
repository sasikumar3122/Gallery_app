package com.example.galleryapp


import android.R.attr
import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import android.media.MediaScannerConnection
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import com.bumptech.glide.Glide
import com.example.galleryapp.databinding.ActivityImageFullBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.*
import kotlin.collections.ArrayList
import android.R.attr.data
import android.view.Menu
import android.view.MenuItem
import android.widget.Adapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView


class ImageFullActivity : AppCompatActivity() {

    private val btnShare :FloatingActionButton by lazy {
        binding.btnShare
    }
    var position = 0
    private  val binding: ActivityImageFullBinding by lazy { ActivityImageFullBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        getImageData(intent)
    }

    private fun switchImage(position: Int){
        val intent = Intent (this@ImageFullActivity,ImageFullActivity::class.java)
        intent.putExtra("index",position)
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
        this@ImageFullActivity.startActivity(intent)
    }

    private fun shareImage(){
        val bitmapDrawable = binding.imageView.drawable as BitmapDrawable
        val bitmap = bitmapDrawable.bitmap
        val bitmapPath = MediaStore.Images.Media.insertImage(contentResolver,bitmap,"title",null)
        val bitmapUri = Uri.parse(bitmapPath)
        val intent = Intent(Intent.ACTION_SEND)
        intent.type="image*/"
        intent.putExtra(Intent.EXTRA_STREAM,bitmapUri)
        startActivity(Intent.createChooser(intent,"Share image"))
    }

    private fun deleteImage(){
   GalleryApplication.INSTANCE.imageList.removeAt(position)
        moveToNext()
        ImageFullActivity()
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        getImageData(intent)
    }
    fun getImageData(intent: Intent?) {

      intent?.let {

     position = intent.getIntExtra("index",0)
    val currentImage =GalleryApplication.INSTANCE.imageList[position]

    supportActionBar?.setTitle(currentImage.imageName)
    Glide.with(this@ImageFullActivity)
        .load(currentImage.imagePath)
        .into(binding.imageView)

    moveToNext()
    binding.btnPrevious.setOnClickListener {
        if (position <= 0)
            return@setOnClickListener
        switchImage(position-1)
    }
    binding.btnShare.setOnClickListener {
        shareImage()
    }
    binding.btnDelete.setOnClickListener {
        deleteImage()
        finish()
    }
}
    }
    fun moveToNext(){
        binding.btnNext.setOnClickListener{
            if (position >= GalleryApplication.INSTANCE.imageList.size-1)
                return@setOnClickListener
            switchImage(position+1)
        }
    }




}
