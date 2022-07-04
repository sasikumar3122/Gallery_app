package com.example.galleryapp


import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.*
import kotlinx.coroutines.launch


class GalleryViewModel : ViewModel() {

     private var images : MutableLiveData<List<ImageData>> = MutableLiveData()


    @RequiresApi(Build.VERSION_CODES.R)
    fun loadImages(): MutableLiveData<List<ImageData>> {
        viewModelScope.launch {
            images.postValue(GalleryRepository.getAllImages(GalleryApplication.INSTANCE.applicationContext))
        }
        return images
    }
        fun getImageLiveDataObserver(): MutableLiveData<List<ImageData>> {
            return images
        }
    }







