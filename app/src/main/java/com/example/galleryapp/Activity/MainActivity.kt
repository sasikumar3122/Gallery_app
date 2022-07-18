package com.example.galleryapp.Activity

import android.Manifest
import android.Manifest.permission
import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.example.galleryapp.Adapters.ImageAdapter
import com.example.galleryapp.Adapters.VideoAdapter
import com.example.galleryapp.GalleryApplication
import com.example.galleryapp.GalleryViewModel
import com.example.galleryapp.R
import com.example.galleryapp.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayout
import java.util.*


class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private var adapter = ImageAdapter(this)
    private var videoadapter = VideoAdapter(this)
    private var progressBar: ProgressBar? = null
    private val viewModel by lazy { ViewModelProviders.of(this)[GalleryViewModel::class.java] }
//    var tabs = binding.tabs


    override fun onCreate(savedInstanceState: Bundle?) {

                super.onCreate(savedInstanceState)
        setContentView(binding.root)
        checkPermission()

//        setupTabIcons()





    }


fun reload(){
    binding.recyclerProgress.visibility = View.VISIBLE
    getImgData()
    binding.imageRecycler.adapter = ImageAdapter(this@MainActivity).apply {
        clickedFolder = this@MainActivity.clickedFolder
    }
    binding.imageRecycler.adapter = VideoAdapter(this@MainActivity).apply {
        clickFolder = this@MainActivity.clickFolder
    }
    binding.recyclerProgress.visibility = View.GONE
}
    private fun fillImageData() {
        binding.imageRecycler.layoutManager = GridLayoutManager(this, 3)

        if (GalleryApplication.INSTANCE.imageList.size == 0) {
            reload()
        }
        else{
            reload()
        }
    }

    val clickedFolder = object : ImageAdapter.OnFolderSelectListener{
        override fun onFolderSelected(folderName: String) {
            viewModel.loadImages(folderName)
            fillImageData()
        }
    }
    val clickFolder = object : VideoAdapter.OnFolderSelectListeners{
        override fun onFolderSelected(folderName: String) {
            viewModel.loadVideos(folderName)
            fillImageData()
        }
    }

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onResume() {
        super.onResume()
        refresh()
        viewModel.loadAlbums()
        viewModel.loadVideoAlbums()
        adapter.clickedFolder = this@MainActivity.clickedFolder
        videoadapter.clickFolder = this@MainActivity.clickFolder
    }

//permission request

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 100) {
            if (grantResults.isNotEmpty() && grantResults[0]
                == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                viewModel.loadAlbums()
                viewModel.loadVideoAlbums()
                fillImageData()

            } else {
                requestStoragePermission()
            }
        }
    }

    private fun checkPermission() {
        if (ContextCompat.checkSelfPermission(
                this@MainActivity,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
            && ContextCompat.checkSelfPermission(
                this,
                permission.WRITE_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this@MainActivity,
                arrayOf(
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    permission.WRITE_EXTERNAL_STORAGE
                ), 100
            )
        } else {
            fillImageData()

        }
    }

    private fun requestStoragePermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(
                this,
                permission.READ_EXTERNAL_STORAGE
            )
        ) {
            AlertDialog.Builder(this)
                .setTitle("Permission needed")
                .setMessage("This permission is needed ")
                .setPositiveButton("ok",
                    DialogInterface.OnClickListener { _, _ ->
                        ActivityCompat.requestPermissions(
                            this@MainActivity, arrayOf(
                                permission.READ_EXTERNAL_STORAGE, permission.WRITE_EXTERNAL_STORAGE
                            ), 100
                        )
                    })
                .create().show()
        } else {
            checkPermission()
        }
    }


    @SuppressLint("NotifyDataSetChanged")
    fun getImgData() {
        viewModel.getImageLiveDataObserver().observe(this, Observer {
            GalleryApplication.INSTANCE.imageList = it as ArrayList
            GalleryApplication.INSTANCE.imageList.reverse()
            adapter.notifyDataSetChanged()
        })
    }


    private fun refresh() {
        binding.refreshScreen.setOnRefreshListener {
            progressBar?.visibility = View.VISIBLE

            progressBar?.visibility = View.GONE

        }
    }

    override fun onBackPressed() {
        val intent = Intent(Intent.ACTION_MAIN)
        startActivity(intent)
        super.onBackPressed()

    }



    val tabIconList: ArrayList<Int> = ArrayList()
    private val tabIcons = intArrayOf(R.drawable.ic_picker_photos_unselected,
        R.drawable.ic_video_unselected)

    private val selectedTabIcons = intArrayOf(
        R.drawable.ic_picker_photos_selected,
        R.drawable.ic_video_selected)



}


