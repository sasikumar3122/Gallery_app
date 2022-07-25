package com.example.galleryapp



import androidx.lifecycle.*
import com.example.galleryapp.models.ImageData
import com.example.galleryapp.models.VideoDatas
import kotlinx.coroutines.launch


class GalleryViewModel : ViewModel() {

    private var images : MutableLiveData<List<ImageData>> = MutableLiveData()
    private var videos : MutableLiveData<List<VideoDatas>> = MutableLiveData()


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


    fun loadVideos(filter:String = ""): MutableLiveData<List<VideoDatas>> {
        viewModelScope.launch {
            videos.postValue(GalleryRepository.getAllVideos(GalleryApplication.INSTANCE.applicationContext,filter))
        }
        return videos
    }

    fun loadVideoAlbums(): MutableLiveData<List<VideoDatas>> {
        viewModelScope.launch {
            videos.postValue(GalleryRepository.getVideolbumNames(GalleryApplication.INSTANCE.applicationContext))
        }
        return videos
    }

    fun getImageLiveDataObserver(): MutableLiveData<List<ImageData>> {
        return images
    }
}








