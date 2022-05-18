 package com.example.galleryapp

import android.Manifest
import android.content.pm.PackageManager
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

     private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
     override fun onCreate(savedInstanceState: Bundle?) {
         super.onCreate(savedInstanceState)
         setContentView(binding.root)
         checkPermission()
         initUi()
    }
     private fun initUi(){
         binding.imageRecycler.layoutManager=GridLayoutManager(this,3)
         fillImageData()
     }
     private fun fillImageData(){
         if (GalleryApplication.INSTANCE.imageList.isEmpty())
         {
             binding.recyclerProgress.visibility = View.VISIBLE
             GalleryApplication.INSTANCE.imageList = getAllImages()
             binding.imageRecycler.adapter = ImageAdapter(this@MainActivity)
             binding.recyclerProgress.visibility = View.GONE
         }
     }

     private fun checkPermission(){
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
     }
     private fun getAllImages(): ArrayList<ImageData> {
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