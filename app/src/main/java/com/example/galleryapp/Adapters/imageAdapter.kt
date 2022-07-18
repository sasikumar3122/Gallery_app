package com.example.galleryapp.Adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
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
        val image : ImageView = itemView.findViewById(R.id.row_albums)
        val title : TextView = itemView.findViewById(R.id.title)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.row_custom_recycler_item,parent,false)
        return ImageViewHolder(view)


    }

//  var clickedFolder : OnFolderSelectListener? = null
    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {

        val currentImage = GalleryApplication.INSTANCE.imageList[position]

        if (currentImage.imageName.isNotEmpty()) {
            holder.title.visibility=View.GONE
            Glide.with(context)
                .load(currentImage.imagePath)
                .apply(RequestOptions().centerCrop())
                .into(holder.image)

            holder.image.setOnClickListener {
                val intent = Intent(context, ImageFullActivity::class.java)
                intent.putExtra("index", position)
                context.startActivity(intent)
            }
        }
        else{
            with(holder.title){
                visibility = View.VISIBLE
                text=currentImage.folderName
            }
//            holder.image.setOnClickListener{
//                clickedFolder?.onFolderSelected(currentImage.folderName)}

        }



    }
    override fun getItemCount(): Int {
        return GalleryApplication.INSTANCE.imageList.size
    }

    interface OnFolderSelectListener{
        fun onFolderSelected(folderName : String)
    }

class DiffCallBack : DiffUtil.ItemCallback<ImageData>(){

    override fun areItemsTheSame(oldItem: ImageData, newItem: ImageData) =
        oldItem.imagePath == newItem.imagePath

    override fun areContentsTheSame(oldItem: ImageData, newItem: ImageData) =
        oldItem == newItem
}

}