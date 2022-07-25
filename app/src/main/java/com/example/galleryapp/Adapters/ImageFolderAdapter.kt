//package com.example.galleryapp.Adapters
//
//import android.content.Context
//import android.content.Intent
//import android.view.LayoutInflater
//import android.view.ViewGroup
//import androidx.core.content.ContextCompat
//import androidx.recyclerview.widget.RecyclerView
//import com.example.galleryapp.Activity.ImageFoldersActivity
//import com.example.galleryapp.databinding.RowCustomRecyclerItemBinding
//import com.example.galleryapp.databinding.RowImagesBinding
//import com.example.galleryapp.models.ImageData
//import com.example.galleryapp.models.ImageFolders
//
//
//class ImageFolderAdapter(private val context: Context, private var imagefolderList: ArrayList<ImageFolders>,private val isFolder:Boolean=false)
//    : RecyclerView.Adapter< ImageFolderAdapter.ImageFolderViewFileHolder>() {
//
//    class ImageFolderViewFileHolder (binding: RowCustomRecyclerItemBinding): RecyclerView.ViewHolder(binding.root){
//
//        var foldername = binding.folderName
//        val root = binding.root
//
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageFolderViewFileHolder {
//        return ImageFolderViewFileHolder(RowCustomRecyclerItemBinding.inflate(LayoutInflater.from(context),parent,false))
//    }
//
//
//    override fun onBindViewHolder(holder: ImageFolderViewFileHolder, position: Int) {
//
//        holder.foldername.text=imagefolderList[position].folderName
//
//        holder.root.setOnClickListener {
//            val intent = Intent(context, ImageFoldersActivity::class.java)
//            intent.putExtra("position",position)
//            ContextCompat.startActivity(context,intent,null)
//        }
//
//    }
//
//    override fun getItemCount(): Int {
//        return imagefolderList.size
//    }
//
//
//}