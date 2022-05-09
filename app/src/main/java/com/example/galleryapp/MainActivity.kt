 package com.example.galleryapp

import android.Manifest
import android.content.pm.PackageManager
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.ProgressBar
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.galleryapp.databinding.ActivityMainBinding
import java.lang.Exception

 class MainActivity : AppCompatActivity() {

    private  val imageRecycler:RecyclerView by lazy {
        binding.imageRecycler
    }
     private val progressBar:ProgressBar by lazy {
         binding.recyclerProgress
     }
     private var allpictures:ArrayList<ImageData>?=null


     private lateinit var binding: ActivityMainBinding
     override fun onCreate(savedInstanceState: Bundle?) {
         super.onCreate(savedInstanceState)
         binding = ActivityMainBinding.inflate(layoutInflater)
         val view = binding.root
         setContentView(view)

        imageRecycler.layoutManager=GridLayoutManager(this,3)
        imageRecycler.setHasFixedSize(true)

        if (ContextCompat.checkSelfPermission(
                this@MainActivity,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) !=PackageManager.PERMISSION_GRANTED
        ){
            ActivityCompat.requestPermissions(
                this@MainActivity,
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),101
            )
        }

        allpictures = ArrayList()

        if (allpictures!!.isEmpty())
        {
            progressBar.visibility = View.VISIBLE

            allpictures=getAllImages()

            imageRecycler.adapter = ImageAdapter(this@MainActivity,allpictures!!)

            progressBar.visibility = View.GONE
        }



    }

     private fun getAllImages(): ArrayList<ImageData>? {

         val images = ArrayList<ImageData>()
         val allImageUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
         val projection = arrayOf(MediaStore.Images.ImageColumns.DATA,MediaStore.Images.Media.DISPLAY_NAME)
         var cursor = this@MainActivity.contentResolver.query(allImageUri,projection,null,null,null)

         try {
             cursor!!.moveToFirst()
             do {
                 val image = ImageData()
                 image.imagePath=cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA))
                 image.imageName=cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME))
                 images.add(image)
             }while (cursor.moveToNext())
             cursor.close()
         }catch (e:Exception)
         {
             e.printStackTrace()
         }
         return images
     }
 }