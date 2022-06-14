 package com.example.galleryapp

import android.Manifest
import android.Manifest.permission
import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.example.galleryapp.databinding.ActivityMainBinding
import java.util.*
import android.content.DialogInterface





 class MainActivity : AppCompatActivity() {

     private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
     private var adapter = ImageAdapter(this)
     private var progressBar: ProgressBar? = null

     override fun onCreate(savedInstanceState: Bundle?) {
         super.onCreate(savedInstanceState)
         setContentView(binding.root)
         checkPermission()
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

///permission request
     override fun onStart(){
         super.onStart()
    checkPermission()
     }

     override fun onRequestPermissionsResult(
         requestCode: Int,
         permissions: Array<out String>,
         grantResults: IntArray
     ) {
         val viewModel = ViewModelProviders.of(this)[GalleryViewModel::class.java]
         super.onRequestPermissionsResult(requestCode, permissions, grantResults)
         if (requestCode == 100) {
             if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                 viewModel.loadImages()
                 fillImageData()

             } else {
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
         else{
             fillImageData()
         }
     }

     private fun requestStoragePermission() {
         if (ActivityCompat.shouldShowRequestPermissionRationale(this, permission.READ_EXTERNAL_STORAGE)) {
             AlertDialog.Builder(this)
                 .setTitle("Permission needed")
                 .setMessage("This permission is needed because of this and that")
                 .setPositiveButton("ok",
                     DialogInterface.OnClickListener { dialog, which ->
                         ActivityCompat.requestPermissions(
                             this@MainActivity, arrayOf(
                                 permission.READ_EXTERNAL_STORAGE
                             ), 100
                         )
                     })
                 .setNegativeButton("cancel",
                     DialogInterface.OnClickListener { dialog, which -> dialog.dismiss() })
                 .create().show()
         } else {
             ActivityCompat.requestPermissions(
                 this,
                 arrayOf(permission.READ_EXTERNAL_STORAGE),
                 100
             )
         }
     }

///viewmodel to mainActivity
@SuppressLint("NotifyDataSetChanged")
fun getImgData() {
         val viewModel = ViewModelProviders.of(this)[GalleryViewModel::class.java]
         viewModel.getImageLiveDataObserver().observe(this,Observer {
             GalleryApplication.INSTANCE.imageList = it as ArrayList
             adapter.notifyDataSetChanged()
         })

     }

     ////latest image and refresh



     override fun onResume() {
         val viewModel = ViewModelProviders.of(this)[GalleryViewModel::class.java]
         super.onResume()
         viewModel.loadImages()

     }

     override fun onCreateOptionsMenu(menu: Menu?): Boolean {
         menuInflater.inflate(R.menu.more_options, menu)
         return true
     }

     override fun onOptionsItemSelected(item: MenuItem): Boolean {
         when (item.itemId) {
             R.id.newer -> {
                 GalleryApplication.INSTANCE.imageList.reverse()
                 return true
             }

             R.id.refresh -> {
                 progressBar?.setVisibility(View.VISIBLE)
                 fillImageData()
                 progressBar?.setVisibility(View.GONE)
             }
         }
         return super.onOptionsItemSelected(item)
     }



 }

