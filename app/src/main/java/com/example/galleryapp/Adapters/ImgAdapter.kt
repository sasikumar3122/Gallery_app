//package com.example.galleryapp.Adapters
//
//
//
//import android.content.Context
//import android.content.Intent
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.core.content.ContextCompat
//import androidx.recyclerview.widget.RecyclerView
//import com.bumptech.glide.Glide
//import com.bumptech.glide.request.RequestOptions
//import com.example.galleryapp.Activity.FoldersActivity
//
//import com.example.galleryapp.Activity.ImageFoldersActivity
//import com.example.galleryapp.Activity.ImageFullActivity
//
//import com.example.galleryapp.Fragment.ImageFolderFragment
//import com.example.galleryapp.Fragment.VideoFragment
//import com.example.galleryapp.R
//import com.example.galleryapp.databinding.FolderViewBinding
//import com.example.galleryapp.databinding.RowCustomRecyclerItemBinding
//import com.example.galleryapp.databinding.RowImagesBinding
//import com.example.galleryapp.models.Folders
//import com.example.galleryapp.models.ImageData
//import com.example.galleryapp.models.ImageFolders
//
//
//class ImgAdapter(private val context: Context,
//                 private var imagefolderList: ArrayList<ImageData>,
//                 private val isFolder:Boolean=false)
//    : RecyclerView.Adapter< ImgAdapter.ImgViewFileHolder>() {
//
//    class ImgViewFileHolder (binding: RowImagesBinding): RecyclerView.ViewHolder(binding.root){
//
//        var foldername = binding.imageFolderName
//        var title = binding.imgtitle
//        var img = binding.folderimages
//        val root = binding.root
//
//
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImgViewFileHolder {
//        return ImgViewFileHolder(RowImagesBinding.inflate(LayoutInflater.from(context),parent,false))
//    }
//    interface OnFolderSelectListeners  {
//        fun onFolderSelected(folderName : String)
//    }
//
//    var clickFolder : OnFolderSelectListeners? = null
//
//    override fun onBindViewHolder(holder: ImgViewFileHolder, position: Int) {
//
//        holder.foldername.text = imagefolderList[position].folderName
//        holder.title.text = imagefolderList[position].title
//
//
//
//            val currentImage = imagefolderList[position]
//        if (currentImage.imageName.isNotEmpty()) {
//            holder.title.visibility = View.GONE
//            Glide.with(context)
//                .load(currentImage.artUri)
//                .apply(RequestOptions().placeholder(R.mipmap.ic_gallery_files).centerCrop())
//                .into(holder.img)
//
//            holder.root.setOnClickListener {
//                val intent = Intent(context, ImageFullActivity::class.java)
//                intent.putExtra("index", position)
//                context.startActivity(intent)
//            }
//        }
//            else{
//                with(holder.title){
//                    visibility = android.view.View.VISIBLE
//                    text=currentImage.folderName
//                }
//                holder.root.setOnClickListener{
//                    clickFolder?.onFolderSelected(currentImage.folderName)}
//
//            }
//
//
////        Glide.with(context)
////            .asBitmap()
////            .load(imagefolderList[position].artUri)
////            .apply(RequestOptions().placeholder(R.mipmap.ic_gallery_files).centerCrop())
////            .into(holder.img)
//
//    }
//        override fun getItemCount(): Int {
//            return imagefolderList.size
//        }
//
//
//
//}