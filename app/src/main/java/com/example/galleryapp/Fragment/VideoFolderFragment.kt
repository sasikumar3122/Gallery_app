//package com.example.galleryapp.Fragment
//
//import android.annotation.SuppressLint
//import android.os.Bundle
//import androidx.fragment.app.Fragment
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.recyclerview.widget.LinearLayoutManager
//import com.example.galleryapp.Activity.MainActivity
//import com.example.galleryapp.Adapters.VideoFoldersAdapter
//import com.example.galleryapp.R
//import com.example.galleryapp.databinding.FragmentImageBinding
//
//
//class VideoFolderFragment : Fragment() {
//
//
//    @SuppressLint("SetTextI18n")
//    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//       val view =inflater.inflate(R.layout.fragment_image, container, false)
//        val binding= FragmentImageBinding.bind(view)
//        binding.folderRecycler.setHasFixedSize(true)
//        binding.folderRecycler.setItemViewCacheSize(10)
//        binding.folderRecycler.layoutManager= LinearLayoutManager(requireContext())
//        binding.folderRecycler.adapter= VideoFoldersAdapter(requireContext(), MainActivity.folderslist)
//        binding.allFolder.text = "total Folder : ${MainActivity.folderslist.size}"
//
//        return view
//    }
//
//}