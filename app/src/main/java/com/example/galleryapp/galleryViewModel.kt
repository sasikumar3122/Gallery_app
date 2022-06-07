package com.example.galleryapp

import android.content.Context
import android.provider.MediaStore
import androidx.lifecycle.*
import kotlinx.coroutines.launch
import java.lang.Exception

class galleryViewModel() : ViewModel() {

    lateinit var images: MutableLiveData<ImageData>

    init {
        images = MutableLiveData()
    }

//    fun loadImages(context: Context): LiveData<ImageData> {
//        viewModelScope.launch {
//            images.postValue(galleryRepository.getAllImages(context))
//            return images
//        }
//    }


//    fun loadImages():LiveData() {
//        viewModelScope.launch {
//            images.postValue(galleryRepository.getAllImages(GalleryApplication.INSTANCE.applicationContext))
//            return images
//        }
//    }

        fun getImageLiveDataObserver(): MutableLiveData<ImageData> {
            return images
        }
    }



