package com.example.galleryapp

class ImageData {
    var imagePath:String?=null
    var imageName:String?=null
    var selected: Boolean? = false

    constructor(imagePath: String?, imageName: String?,selected:Boolean) {
        this.imagePath = imagePath
        this.imageName = imageName
        this.selected= selected
    }
    constructor(){}

}