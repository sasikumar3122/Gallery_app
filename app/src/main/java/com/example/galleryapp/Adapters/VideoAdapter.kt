package com.example.galleryapp.Adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.galleryapp.Activity.MainActivity
import com.example.galleryapp.Activity.VideoFullActicity
import com.example.galleryapp.GalleryApplication
import com.example.galleryapp.R
import com.example.galleryapp.models.ImageData

class VideoAdapter(private var context: MainActivity): ListAdapter<ImageData,VideoAdapter.VideoViewHolder>(DiffCallBack()) {

    class VideoViewHolder (itemView:View):RecyclerView.ViewHolder(itemView){
        val image : ImageView = itemView.findViewById(R.id.row_image)
        val title : TextView = itemView.findViewById(R.id.title)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
       val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.row_custom_recycler_item,parent,false)
        return VideoViewHolder(view)
    }
    interface OnFolderSelectListeners : ImageAdapter.OnFolderSelectListener {
        override fun onFolderSelected(folderName : String)
    }
    var clickFolder : ImageAdapter.OnFolderSelectListener? = null

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {

        val currentImage = GalleryApplication.INSTANCE.imageList[position]

        if (currentImage.videoName.isNotEmpty()) {
            holder.title.visibility=View.GONE
            Glide.with(context)
                .load(currentImage.videoPath)
                .apply(RequestOptions().centerCrop())
                .into(holder.image)

            holder.image.setOnClickListener {
                val intent = Intent(context, VideoFullActicity::class.java)
                intent.putExtra("index", position)
                context.startActivity(intent)
            }
        }else{
            with(holder.title){
                visibility = android.view.View.VISIBLE
                text=currentImage.folderName
            }
            holder.image.setOnClickListener{
                clickFolder?.onFolderSelected(currentImage.folderName)}

        }

    }

    class DiffCallBack : DiffUtil.ItemCallback<ImageData>(){

        override fun areItemsTheSame(oldItem: ImageData, newItem: ImageData) =
            oldItem.videoPath == newItem.videoPath

        override fun areContentsTheSame(oldItem: ImageData, newItem: ImageData) =
            oldItem == newItem
    }

    override fun getItemCount(): Int {
        return GalleryApplication.INSTANCE.imageList.size
    }




}