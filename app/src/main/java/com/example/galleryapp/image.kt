package com.example.galleryapp

import android.net.Uri

class ImageData {

    var imagePath:String?=null
    var imageName:String?=null


    constructor(imagePath: String?, imageName: String?) {
        this.imagePath = imagePath
        this.imageName = imageName


    }
    constructor(){}

}