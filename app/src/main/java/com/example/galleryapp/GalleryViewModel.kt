package com.example.galleryapp



import androidx.lifecycle.*
import com.example.galleryapp.models.ImageData
import kotlinx.coroutines.launch


class GalleryViewModel : ViewModel() {

     private var images : MutableLiveData<List<ImageData>> = MutableLiveData()


    fun loadImages(filter:String = ""): MutableLiveData<List<ImageData>> {
        viewModelScope.launch {
            images.postValue(GalleryRepository.getAllImages(GalleryApplication.INSTANCE.applicationContext,filter))
        }
        return images
    }

    fun loadAlbums(): MutableLiveData<List<ImageData>> {
        viewModelScope.launch {
            images.postValue(GalleryRepository.getAlbumNames(GalleryApplication.INSTANCE.applicationContext))
        }
        return images
    }


//    fun loadVideos(filter:String = ""): MutableLiveData<List<ImageData>> {
//        viewModelScope.launch {
//            images.postValue(GalleryRepository.getAllVideos(GalleryApplication.INSTANCE.applicationContext,filter))
//        }
//        return images
//    }
//
//    fun loadVideoAlbums(): MutableLiveData<List<ImageData>> {
//        viewModelScope.launch {
//            images.postValue(GalleryRepository.getVideolbumNames(GalleryApplication.INSTANCE.applicationContext))
//        }
//        return images
//    }

        fun getImageLiveDataObserver(): MutableLiveData<List<ImageData>> {
            return images
        }
    }







