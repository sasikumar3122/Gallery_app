package com.example.galleryapp.Adapters

import android.content.Context
import android.content.Intent
import android.text.format.DateUtils
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.galleryapp.Activity.PlayerActivity

import com.example.galleryapp.R
import com.example.galleryapp.databinding.VideoViewBinding
import com.example.galleryapp.models.VideoData

class VideoAdapter(private val context: Context, private var videoList: ArrayList<VideoData>, private val isFolder:Boolean=false)
    : RecyclerView.Adapter< VideoAdapter.VideoViewFileHolder>() {

    class VideoViewFileHolder (binding:VideoViewBinding): RecyclerView.ViewHolder(binding.root){

        val title = binding.videoname
        val folder = binding.foldername
        val duration = binding.duration
        val image = binding.videoimg
        val root = binding.root

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewFileHolder {
        return VideoViewFileHolder(VideoViewBinding.inflate(LayoutInflater.from(context),parent,false))
    }



    override fun onBindViewHolder(holder: VideoViewFileHolder, position: Int) {


        holder.title.text = videoList[position].title
        holder.folder.text = videoList[position].folderName
        holder.duration.text = DateUtils.formatElapsedTime(videoList[position].duration/1000)


        Glide.with(context)
            .asBitmap()
            .load(videoList[position].artUri)
            .apply(RequestOptions().placeholder(R.mipmap.ic_gallery_files).centerCrop())
            .into(holder.image)

        holder.root.setOnClickListener{

            when {
                isFolder -> {
                    sendIntent(pos = position, ref = "FolderActivity")
            }
                else -> {
                    sendIntent(pos = position, ref = "AllVideos")
                }
            }
        }

        }

    override fun getItemCount(): Int {
       return videoList.size
    }


    private fun sendIntent(pos:Int,ref:String){
        PlayerActivity.position=pos
        val intent = Intent(context, PlayerActivity::class.java)
        intent.putExtra("class",ref)
        ContextCompat.startActivity(context,intent,null)
    }
}








