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
//import com.example.galleryapp.Adapters.VideoAdapter
//import com.example.galleryapp.R
//import com.example.galleryapp.databinding.FragmentVideoBinding
//
//
//class VideoFragment : Fragment() {
//
//
//    @SuppressLint("SetTextI18n")
//    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        val view = inflater.inflate(R.layout.fragment_video, container, false)
//        val binding=FragmentVideoBinding.bind(view)
//
//        binding.videoRecycler.setHasFixedSize(true)
//        binding.videoRecycler.setItemViewCacheSize(10)
//        binding.videoRecycler.layoutManager= LinearLayoutManager(requireContext())
//        binding.videoRecycler.adapter= VideoAdapter(requireContext(),MainActivity.videolist)
//        binding.allVideos.text = "total Videos : ${MainActivity.videolist.size}"
//
//        return view
//    }
//
//
//}