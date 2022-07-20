package com.example.galleryapp.Fragment



import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.galleryapp.Activity.MainActivity
import com.example.galleryapp.Adapters.ImageFolderAdapter
import com.example.galleryapp.Adapters.ImgAdapter
import com.example.galleryapp.R
import com.example.galleryapp.databinding.ActivityImageFoldersBinding
import com.example.galleryapp.databinding.ImageActivityFoldersBinding
import com.example.galleryapp.models.ImageData

class ImageFragment:Fragment() {


    @SuppressLint("SetTextI18n")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.activity_image_folders, container, false)
        val binding= ActivityImageFoldersBinding.bind(view)


        binding.imageRecyclerFA.setHasFixedSize(true)
        binding.imageRecyclerFA.setItemViewCacheSize(10)
        binding.imageRecyclerFA.layoutManager= LinearLayoutManager(requireContext())
        binding.imageRecyclerFA.adapter= ImgAdapter(requireContext(),MainActivity.imagelist)
        binding.totalimages.text = "total Images : ${MainActivity.imagelist.size}"

        return view
    }

}