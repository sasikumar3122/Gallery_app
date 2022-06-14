package com.example.galleryapp


class ImageData {
    var imagePath:String?=null
    var imageName:String?=null
    var imageDateTime:String?=null
    constructor(imagePath: String?, imageName: String?, imageDateTime: String?) {
        this.imagePath = imagePath
        this.imageName = imageName
        this.imageDateTime = imageDateTime
    }
    constructor(){}

}