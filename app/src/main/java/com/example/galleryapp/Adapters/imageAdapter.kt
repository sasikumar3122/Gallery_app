package com.example.galleryapp.Adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.galleryapp.GalleryApplication
import com.example.galleryapp.Activity.ImageFullActivity
import com.example.galleryapp.Activity.MainActivity
import com.example.galleryapp.R
import com.example.galleryapp.models.ImageData

class ImageAdapter(private var context: MainActivity):
ListAdapter<ImageData, ImageAdapter.ImageViewHolder>(DiffCallBack()){

    inner class ImageViewHolder(itemView : View):
        RecyclerView.ViewHolder(itemView) {


        val image : ImageView = itemView.findViewById(R.id.row_image)



    }

    override fun onViewRecycled(holder: ImageViewHolder) {
        super.onViewRecycled(holder)
    }

    override fun onViewDetachedFromWindow(holder: ImageViewHolder) {
        super.onViewDetachedFromWindow(holder)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.row_custom_recycler_item,parent,false)
        return ImageViewHolder(view)


    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {

        val currentImage = GalleryApplication.INSTANCE.imageList[position]

        Glide.with(context)
            .load(currentImage.imagePath)
            .apply(RequestOptions().centerCrop())
            .into(holder.image)

        holder.image.setOnClickListener {
            val intent = Intent (context, ImageFullActivity::class.java)
            intent.putExtra("index",position)
            context.startActivity(intent)
        }



    }
    override fun getItemCount(): Int {
        return GalleryApplication.INSTANCE.imageList.size
    }

class DiffCallBack : DiffUtil.ItemCallback<ImageData>(){

    override fun areItemsTheSame(oldItem: ImageData, newItem: ImageData) =
        oldItem.imagePath == newItem.imagePath

    override fun areContentsTheSame(oldItem: ImageData, newItem: ImageData) =
        oldItem == newItem
}

}