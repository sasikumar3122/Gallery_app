package com.example.galleryapp.models

import android.icu.text.CaseMap
import android.net.Uri
import android.provider.LiveFolders
import android.util.Log


data class ImageData(
    var folderName: String,
    var imagePath: String,
    var imageName: String,
    var title: String,
    var size: String,
    var artUri: Uri

)

data class ImageFolders(
    val folderName: String,
    val id:String

)






