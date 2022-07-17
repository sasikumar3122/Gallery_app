package com.example.galleryapp.Activity

import android.Manifest
import android.Manifest.permission
import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.DialogInterface
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
import com.example.galleryapp.GalleryApplication
import com.example.galleryapp.GalleryViewModel
import com.example.galleryapp.databinding.ActivityMainBinding
import java.util.*


class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private var adapter = ImageAdapter(this)
    private var progressBar: ProgressBar? = null
    private val viewModel by lazy { ViewModelProviders.of(this)[GalleryViewModel::class.java] }





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        checkPermission()
//        saveImage()
    }



    private fun fillImageData() {
        binding.imageRecycler.layoutManager = GridLayoutManager(this, 3)

        if (GalleryApplication.INSTANCE.imageList.size == 0) {
            binding.recyclerProgress.visibility = View.VISIBLE
            getImgData()
            binding.imageRecycler.adapter = ImageAdapter(this@MainActivity).apply {
                clickedFolder = this@MainActivity.clickedFolder
            }

            binding.recyclerProgress.visibility = View.GONE
        }
    }

    val clickedFolder = object : ImageAdapter.OnFolderSelectListener{
        override fun onFolderSelected(folderName: String) {
            viewModel.loadImages(folderName)
            fillImageData()
        }
    }

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onResume() {
        super.onResume()
        viewModel.loadAlbums()
        refresh()
        adapter.clickedFolder = this@MainActivity.clickedFolder
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
            fillImageData()
            progressBar?.visibility = View.GONE

        }
    }
}


