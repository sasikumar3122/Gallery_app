package com.example.galleryapp

import android.app.Application

class GalleryApplication : Application() {

    var imageList = ArrayList<ImageData>()
    override fun onCreate() {
        super.onCreate()
        INSTANCE=this

    }

    companion object{
        lateinit var INSTANCE : GalleryApplication
    }

}