package com.example.galleryapp.models

import android.net.Uri

data class dataClass (val id: String,
                      val name: String,
                      var count: Long = 0,
                      var ImageUri: Uri
){

}