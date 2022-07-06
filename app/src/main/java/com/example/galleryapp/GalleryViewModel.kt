package com.example.galleryapp


import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.*
import com.example.galleryapp.models.ImageData
import kotlinx.coroutines.launch


class GalleryViewModel : ViewModel() {

     private var images : MutableLiveData<List<ImageData>> = MutableLiveData()



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







