package com.example.galleryapp

import android.net.Uri

class ImageData {

    var imagePath:String?=null
    var imageName:String?=null
    var imgUri : Uri? = null

    constructor(imagePath: String?, imageName: String?,imgUri: Uri?) {
        this.imagePath = imagePath
        this.imageName = imageName
        this.imgUri = imgUri

    }
    constructor(){}

}