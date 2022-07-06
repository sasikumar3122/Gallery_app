package com.example.galleryapp

import android.app.Application
import com.example.galleryapp.models.ImageData

class GalleryApplication : Application() {
    var imageList = ArrayList<ImageData>()
    override fun onCreate() {
        super.onCreate()
        instance = this
    }
    companion object{
        private lateinit var instance : GalleryApplication
        val INSTANCE : GalleryApplication by lazy { instance}
    }
}