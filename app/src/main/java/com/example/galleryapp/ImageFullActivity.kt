package com.example.galleryapp


import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.galleryapp.databinding.ActivityImageFullBinding
import java.util.*


class ImageFullActivity : AppCompatActivity() {


    private var position = 0
    private val binding: ActivityImageFullBinding by lazy {
        ActivityImageFullBinding.inflate(
            layoutInflater
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        getImageData(intent)

    }

    private fun switchImage(position: Int) {
        val intent = Intent(this@ImageFullActivity, ImageFullActivity::class.java)
        intent.putExtra("index", position)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
        this@ImageFullActivity.startActivity(intent)
    }

    private fun shareImage() {
        val bitmapDrawable = binding.imageView.drawable as BitmapDrawable
        val bitmap = bitmapDrawable.bitmap
        val bitmapPath = MediaStore.Images.Media.insertImage(contentResolver, bitmap, "title", null)
        val bitmapUri = Uri.parse(bitmapPath)
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "image*/"
        intent.putExtra(Intent.EXTRA_STREAM, bitmapUri)
        startActivity(Intent.createChooser(intent, "Share image"))
    }

    private fun deleteImage() {
        GalleryApplication.INSTANCE.imageList.removeAt(position)
        moveToNext()
        ImageFullActivity()
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        getImageData(intent)
    }

    private fun getImageData(intent: Intent?) {

        intent?.let {

            position = intent.getIntExtra("index", 0)
            val currentImage = GalleryApplication.INSTANCE.imageList[position]

            supportActionBar?.title = currentImage.imageName
            Glide.with(this@ImageFullActivity)
                .load(currentImage.imagePath)
                .into(binding.imageView)
            moveToNext()
            binding.btnPrevious.setOnClickListener {
                if (position <= 0)
                    return@setOnClickListener
                switchImage(position - 1)
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

    private fun moveToNext() {
        binding.btnNext.setOnClickListener {
            if (position >= GalleryApplication.INSTANCE.imageList.size - 1)
                return@setOnClickListener
            switchImage(position + 1)
        }
    }
}
