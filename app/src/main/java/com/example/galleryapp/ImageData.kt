package com.example.galleryapp


data class ImageData(
    var folderNames: String,
    var imagePath: String,
    var imageName: String,
    var imgCount: Int,
    var isVideo: Boolean,
    val id: String,
    var count: Long = 0,
){
    constructor(name: String, count: Long, id: String) : this("","","",0,true,"1",0)

}



