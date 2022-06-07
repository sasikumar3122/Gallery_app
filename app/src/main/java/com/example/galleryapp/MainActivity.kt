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
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.galleryapp.databinding.ActivityMainBinding
import com.example.galleryapp.galleryRepository.getAllImages
import java.lang.Exception


 class MainActivity : AppCompatActivity() {

     private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
     override fun onCreate(savedInstanceState: Bundle?) {
         super.onCreate(savedInstanceState)
         setTheme(R.style.splashScreenTheme)
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
//             GalleryApplication.INSTANCE.imageList = getImgData()
//             binding.imageRecycler.adapter = ImageAdapter(this@MainActivity)
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



//     fun getImgData(): ArrayList<ImageData> {
//         val viewModel = ViewModelProviders.of(this).get(galleryViewModel::class.java)
//         viewModel.getImageLiveDataObserver().observe(this,Observer<ImageData>{
//
//         })
//         viewModel.loadImages()
//         return getImgData()
//     }


     override fun onDestroy() {
         super.onDestroy()
         null
     }
 }