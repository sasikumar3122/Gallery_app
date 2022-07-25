//package com.example.galleryapp.Fragment
//
//import android.annotation.SuppressLint
//import android.os.Bundle
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.fragment.app.Fragment
//import androidx.recyclerview.widget.LinearLayoutManager
//import com.example.galleryapp.Activity.MainActivity
//import com.example.galleryapp.Adapters.ImageFolderAdapter
//import com.example.galleryapp.R
//import com.example.galleryapp.databinding.ImageActivityFoldersBinding
//
//class ImageFolderFragment:Fragment() {
//
//
//    @SuppressLint("SetTextI18n")
//    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        val view = inflater.inflate(R.layout.image_activity_folders, container, false)
//        val binding= ImageActivityFoldersBinding.bind(view)
//
//
//        binding.imageFolderRecyclerFA.setHasFixedSize(true)
//        binding.imageFolderRecyclerFA.setItemViewCacheSize(10)
//        binding.imageFolderRecyclerFA.layoutManager= LinearLayoutManager(requireContext())
//        binding.imageFolderRecyclerFA.adapter= ImageFolderAdapter(requireContext(),MainActivity.imagefolderslist)
//        binding.imagetotalFolders.text = "total Images : ${MainActivity.imagefolderslist.size}"
//
//        return view
//    }
//
//}