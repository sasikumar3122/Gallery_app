package com.example.galleryapp

import android.app.Activity
import android.app.Dialog
import android.app.PendingIntent
import android.content.ContentResolver
import android.content.Context
import android.content.Intent
import android.content.IntentSender.SendIntentException
import android.net.Uri
import android.provider.BaseColumns
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import java.io.File
import java.lang.Exception
import java.util.*

class ImageAdapter(private var context: MainActivity,private val data: List<ImageData>):
RecyclerView.Adapter<ImageAdapter.ImageViewHolder>(){

    private var listData: MutableList<ImageData> = data as MutableList<ImageData>
    var selectedList = mutableListOf<Int>()

    inner class ImageViewHolder(itemView : View):RecyclerView.ViewHolder(itemView) {
        var image : ImageView? = null
        init { image = itemView.findViewById(R.id.row_image) }



    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.row_custom_recycler_item,parent,false)
        return ImageViewHolder(view)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val currentImage =GalleryApplication.INSTANCE.imageList[position]

        Glide.with(context)
            .load(currentImage.imagePath)
            .apply(RequestOptions().centerCrop())
            .into(holder.image!!)

        holder.image?.setOnClickListener{
            val intent = Intent (context,ImageFullActivity::class.java)
            intent.putExtra("index",position)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return GalleryApplication.INSTANCE.imageList.size
    }


    fun deleteSelectedItem() {
        if(selectedList.isNotEmpty()){
            listData.removeAll{item -> item.selected == true}
        }
        notifyDataSetChanged()
    }

    fun deleteItem(index: Int) {
        listData.removeAt(index)
        notifyDataSetChanged()
    }

}