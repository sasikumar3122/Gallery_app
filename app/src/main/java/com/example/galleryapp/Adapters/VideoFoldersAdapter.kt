package com.example.galleryapp.Adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.galleryapp.Activity.FoldersActivity
import com.example.galleryapp.databinding.FolderViewBinding
import com.example.galleryapp.models.Folders


class VideoFoldersAdapter(private val context: Context, private var folderList: ArrayList<Folders>)
    : RecyclerView.Adapter< VideoFoldersAdapter.FolderViewFileHolder>() {

    class FolderViewFileHolder (binding: FolderViewBinding): RecyclerView.ViewHolder(binding.root){

        var foldername = binding.folderName
        val root = binding.root

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FolderViewFileHolder {
        return FolderViewFileHolder(FolderViewBinding.inflate(LayoutInflater.from(context),parent,false))
    }


    override fun onBindViewHolder(holder: FolderViewFileHolder, position: Int) {


       holder.foldername.text=folderList[position].folderName
        holder.root.setOnClickListener {
            val intent = Intent(context, FoldersActivity::class.java)
            intent.putExtra("position",position)
            ContextCompat.startActivity(context,intent,null)
        }

    }

    override fun getItemCount(): Int {
        return folderList.size
    }


}