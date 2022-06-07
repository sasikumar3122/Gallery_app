package com.example.galleryapp

import android.content.Context
import android.provider.MediaStore
import androidx.lifecycle.*
import kotlinx.coroutines.launch
import java.lang.Exception

class galleryViewModel() : ViewModel() {

    lateinit var images: MutableLiveData<String>

    init {
        images = MutableLiveData()
    }

//    fun loadImages(context: Context): LiveData<String> {
//        viewModelScope.launch {
//            images.postValue(galleryRepository.getAllImages(context).toString())
//            return images
//        }
//    }
//    fun loadImages():LiveData() {
//        viewModelScope.launch {
//            images.postValue(galleryRepository.getAllImages(GalleryApplication.INSTANCE.applicationContext))
//            return images
//        }
//    }

        fun getImageLiveDataObserver(): MutableLiveData<String> {
            return images
        }
    }



