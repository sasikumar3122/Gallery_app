package com.example.galleryapp.models




data class ImageData(
    var id: Int,
    var folderName: String,
    var imagePath: String,
    var imageName: String,
    var imgCount: Int,
    var title: String,
    var videoPath: String,
    var videoName: String,
    var videoCount: Int,
    var videotitle: String,
    var isSelected: Boolean=false

)

{

    constructor():this(0,"","","",0,"","","",0,"")
}
data class VideoDatas(
    var title: String="",
    var videoPath: String="",
    var videoName: String="",
    var videotitle: String="",
    var id: Int=0,
    var folderName: String="",
)

data class AlbumsDatas(
    var id :Int=0,
    var folderName: String="",
    var name: String = "",
    var coverUri: String = "",
    var albumPhotos: ArrayList<ImageData> = ArrayList())






