package com.example.galleryapp.models

import android.icu.text.CaseMap
import android.net.Uri
import android.provider.LiveFolders


data class ImageData(
    var folderName: String,
    var imagePath: String,
    var imageName: String,
    var imgCount: Int,
    var title: String,
    var videoPath: String,
    var videoName: String,
    var videoCount: Int,
    var videotitle: String
)

{
    constructor():this("","","",0,"","","",0,"")
}



