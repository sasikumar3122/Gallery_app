package com.example.galleryapp.Adapters

import android.annotation.SuppressLint
import android.content.Context
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
import com.example.galleryapp.GalleryApplication
import com.example.galleryapp.Activity.MainActivity
import com.example.galleryapp.R
import com.example.galleryapp.models.ImageData

class AlbamAdapter():RecyclerView.Adapter<AlbamAdapter.AlbamViewHolder>() {

    private var items: List<ImageData> = ArrayList()

    private var datalist = emptyList<ImageData>()




    inner class AlbamViewHolder constructor(itemView:View):RecyclerView.ViewHolder(itemView){
        @SuppressLint("ResourceType")
        val image : ImageView = itemView.findViewById(R.id.albam_photo)
        val imgCount : TextView = itemView.findViewById(R.id.photoCount)
        val title : TextView = itemView.findViewById(R.id.title)


        fun bind (imageData: ImageData){

            imgCount.setText(imageData.imgCount)
            title.setText(imageData.title)

            val currentImage = GalleryApplication.INSTANCE.imageList[position]

            val requirestOptions = RequestOptions()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)

            Glide.with(itemView.context)
                .load(currentImage.imagePath)
                .apply(requirestOptions)
                .into(image)

            image.setOnClickListener {
                val intents = Intent(itemView.context, MainActivity::class.java)
                intents.putExtra("img",position)
                itemView.context.startActivity(intents)
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbamViewHolder {

        return AlbamViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.folder_img,parent,false)
        )

    }

    override fun onBindViewHolder(holder: AlbamViewHolder, position: Int) {
var data = datalist[position]
        when(holder){
            is AlbamViewHolder -> {
                holder.bind(GalleryApplication.INSTANCE.imageList[position])
            }
        }


    }

    override fun getItemCount(): Int {
        return GalleryApplication.INSTANCE.imageList.size
    }







//    class DiffCallBack : DiffUtil.ItemCallback<ImageData>(){
//
//        override fun areItemsTheSame(oldItem: ImageData, newItem: ImageData) =
//            oldItem.imagePath == newItem.imagePath
//
//        override fun areContentsTheSame(oldItem: ImageData, newItem: ImageData) =
//            oldItem == newItem
//    }
}