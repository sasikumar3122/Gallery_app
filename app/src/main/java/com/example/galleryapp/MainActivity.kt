 package com.example.galleryapp

import android.Manifest
import android.Manifest.permission
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.galleryapp.databinding.ActivityMainBinding
import com.example.galleryapp.galleryRepository.getAllImages
import java.lang.Exception
import java.util.*
import kotlin.collections.ArrayList


 class MainActivity : AppCompatActivity() {
     private val PERMISSION_REQUEST_CODE = 100
     private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
     var adapter = ImageAdapter(this)
     var progressBar: ProgressBar? = null


     override fun onCreate(savedInstanceState: Bundle?) {
         super.onCreate(savedInstanceState)
         setContentView(binding.root)
         checkPermission()
         fillImageData()
//         onRequestPermissionsResult()
    }

     private fun fillImageData(){
         binding.imageRecycler.layoutManager=GridLayoutManager(this,3)
         if (GalleryApplication.INSTANCE.imageList.size == 0)
         {
             binding.recyclerProgress.visibility = View.VISIBLE
              getImgData()
             binding.imageRecycler.adapter = ImageAdapter(this@MainActivity)
             binding.recyclerProgress.visibility = View.GONE
         }
     }

///permission requiest
     override fun onStart(){
         checkPermission()
         adapter.notifyDataSetChanged()
         super.onStart()
     }

     override fun onRequestPermissionsResult(
         requestCode: Int,
         permissions: Array<out String>,
         grantResults: IntArray
     ) {
         val viewModel = ViewModelProviders.of(this).get(galleryViewModel::class.java)
         super.onRequestPermissionsResult(requestCode, permissions, grantResults)
         if (requestCode == PERMISSION_REQUEST_CODE) {
             if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                 Toast.makeText(this, "Display Gallery images", Toast.LENGTH_SHORT).show()
                 viewModel.loadImages()
                 fillImageData()
                 adapter.notifyDataSetChanged()

             } else {
                 Toast.makeText(this, "Storage permission required", Toast.LENGTH_SHORT).show()
//                 binding.empty.visibility=View.VISIBLE
             }
         }
     }
     private fun checkPermission(){
         if (ContextCompat.checkSelfPermission(
                 this@MainActivity,
                 Manifest.permission.READ_EXTERNAL_STORAGE
             ) !=PackageManager.PERMISSION_GRANTED
             && ContextCompat.checkSelfPermission(
                 this,
                 permission.WRITE_EXTERNAL_STORAGE
             ) != PackageManager.PERMISSION_GRANTED
         ){
             ActivityCompat.requestPermissions(
                 this@MainActivity,
                 arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE ,
                     permission.WRITE_EXTERNAL_STORAGE),100
             )
         }
     }
///viewmodel to mainActivity
     fun getImgData() {
         val viewModel = ViewModelProviders.of(this).get(galleryViewModel::class.java)
         viewModel.getImageLiveDataObserver().observe(this,Observer (){
             GalleryApplication.INSTANCE.imageList = it as ArrayList
             adapter.notifyDataSetChanged()
         })
//         viewModel.loadImages()
     }

     ////latest image and refresh



     override fun onResume() {
         val viewModel = ViewModelProviders.of(this).get(galleryViewModel::class.java)
         super.onResume()
         viewModel.loadImages()
         adapter.notifyDataSetChanged()

     }

     override fun onCreateOptionsMenu(menu: Menu?): Boolean {
         menuInflater.inflate(R.menu.more_options, menu)
         return true
     }

     override fun onOptionsItemSelected(item: MenuItem): Boolean {
         when (item.itemId) {
             R.id.newer -> {
                 Collections.reverse(GalleryApplication.INSTANCE.imageList)
                 adapter.notifyDataSetChanged()
                 return true
             }

             R.id.refresh -> {
                 progressBar?.setVisibility(View.VISIBLE)
                 fillImageData()
                 adapter.notifyDataSetChanged()
                 progressBar?.setVisibility(View.GONE)
             }
         }
         return super.onOptionsItemSelected(item)
     }

 }