package com.example.galleryapp.Activity

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.ProgressBar
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.galleryapp.Adapters.AlbamAdapter
import com.example.galleryapp.Adapters.ImageAdapter
import com.example.galleryapp.GalleryApplication
import com.example.galleryapp.GalleryViewModel
import com.example.galleryapp.databinding.ActivityMainBinding
import com.example.galleryapp.databinding.AlbumLayoutBinding
import com.example.galleryapp.databinding.FolderImgBinding
import com.example.galleryapp.models.dataClass
import com.example.galleryapp.models.ImageData
import java.util.ArrayList

class AlbumImageActivity : AppCompatActivity() {

    var datalist = mutableListOf<ImageData>()
    private val binding: AlbumLayoutBinding by lazy { AlbumLayoutBinding.inflate(layoutInflater) }
    private var progressBar: ProgressBar? = null
    private lateinit var albamAdapter: AlbamAdapter
    private val bin: FolderImgBinding by lazy { FolderImgBinding.inflate(layoutInflater) }
    private var adapter = AlbamAdapter()
    private val viewModel by lazy { ViewModelProviders.of(this)[GalleryViewModel::class.java] }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState, )
        setContentView(binding.root)
//        initRecyclerview()


        bin.albamPhoto.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }


    }



    private fun initRecyclerview(){
        binding.albamRecycler.layoutManager = GridLayoutManager(this, 3)
        binding.albamRecycler.apply {
            layoutManager = LinearLayoutManager(this@AlbumImageActivity)
            albamAdapter = AlbamAdapter()
            adapter = albamAdapter
        }
        datalist.add(ImageData())

    }


    private fun fillImageData() {
        binding.albamRecycler.layoutManager = GridLayoutManager(this, 3)
        if (GalleryApplication.INSTANCE.imageList.size == 0) {
            binding.recyclerViewProgress.visibility = View.VISIBLE
            getImgData()
            binding.albamRecycler.adapter = AlbamAdapter()
            binding.recyclerViewProgress.visibility = View.GONE
        }
    }
    private fun getImgData() {
        viewModel.getImageLiveDataObserver().observe(this, Observer {
            GalleryApplication.INSTANCE.imageList = it as ArrayList
            GalleryApplication.INSTANCE.imageList.reverse()
            adapter.notifyDataSetChanged()
        })
    }
    private fun refresh() {
        binding.refreshScreenAlbam.setOnRefreshListener {
            progressBar?.visibility = View.VISIBLE
            fillImageData()
            progressBar?.visibility = View.GONE

        }
}}