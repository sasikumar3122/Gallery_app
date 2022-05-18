package com.example.galleryapp


import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.galleryapp.databinding.ActivityImageFullBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.text.FieldPosition


class ImageFullActivity : AppCompatActivity() {

    private val btnShare :FloatingActionButton by lazy {
        binding.btnShare
    }

    private  val binding: ActivityImageFullBinding by lazy { ActivityImageFullBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val position = intent.getIntExtra("index",0)
        val currentImage =GalleryApplication.INSTANCE.imageList[position]

            supportActionBar?.setTitle(currentImage.imageName)
            Glide.with(this@ImageFullActivity)
                .load(currentImage.imagePath)
                .into(binding.imageView)

            binding.btnShare.setOnClickListener {
                shareImage()
            }

        binding.btnNext.setOnClickListener{
            if (position >= GalleryApplication.INSTANCE.imageList.size-1)
                return@setOnClickListener
            switchImage(position+1)

        }

        binding.btnPrevious.setOnClickListener {
            if (position <=0)
                return@setOnClickListener
            switchImage(position-1)

        }

        binding.btnDelete.setOnClickListener {
            deleteImage()

        }

    }

    private fun switchImage(position: Int){
        val intent = Intent (this@ImageFullActivity,ImageFullActivity::class.java)
        intent.putExtra("index",position)
        this@ImageFullActivity.startActivity(intent)
        this@ImageFullActivity.finish()
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

    }

}
