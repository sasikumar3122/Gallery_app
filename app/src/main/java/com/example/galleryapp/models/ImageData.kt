package com.example.galleryapp.models

import android.icu.text.CaseMap
import android.net.Uri
import android.provider.LiveFolders


data class ImageData(
    var folderNames: String,
    var imagePath: String,
    var imageName: String,
    var imgCount: Int,
    var title: String
)

{
    constructor():this("","","",0,"")
}


