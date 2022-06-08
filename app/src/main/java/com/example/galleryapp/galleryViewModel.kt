package com.example.galleryapp

import android.content.Context
import android.provider.MediaStore
import androidx.lifecycle.*
import kotlinx.coroutines.launch
import java.lang.Exception
import java.util.ArrayList

class galleryViewModel() : ViewModel() {

    lateinit var images : MutableLiveData<List<ImageData>>

    init {
        images = MutableLiveData()
    }

    fun loadImages(): MutableLiveData<List<ImageData>> {
        viewModelScope.launch {
            images.postValue(galleryRepository.getAllImages(GalleryApplication.INSTANCE.applicationContext))
        }
        return images
    }
        fun getImageLiveDataObserver(): MutableLiveData<List<ImageData>> {
            return images
        }
    }







