//package com.example.galleryapp.Activity
//
//import android.annotation.SuppressLint
//import android.content.Context
//import android.net.Uri
//import android.os.Bundle
//import android.provider.MediaStore
//import android.util.Log
//import androidx.appcompat.app.AppCompatActivity
//import androidx.recyclerview.widget.LinearLayoutManager
//import com.example.galleryapp.Adapters.ImageFolderAdapter
//import com.example.galleryapp.GalleryApplication
//import com.example.galleryapp.databinding.ActivityImageFoldersBinding
//import com.example.galleryapp.databinding.ImageActivityFoldersBinding
//import com.example.galleryapp.models.ImageData
//import com.example.galleryapp.models.ImageFolders
//import java.io.File
//
//class ImageActivity :AppCompatActivity(){
//
//
//    private val binding: ActivityImageFoldersBinding by lazy { ActivityImageFoldersBinding.inflate(layoutInflater) }
//
//
//    companion object{
//        lateinit var currontFolderImages :ArrayList<ImageData>
//        lateinit var imagefolderList: ArrayList<ImageFolders>
//    }
//    @SuppressLint("SetTextI18n")
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(binding.root)
//
//
//        val position = intent.getIntExtra("positon",0)
//        supportActionBar?.setDisplayHomeAsUpEnabled(true)
//        supportActionBar?.title=MainActivity.imagefolderslist[position].folderName
////        currontFolderImages = getAllImages(MainActivity.folderslist[position].id,
////            GalleryApplication.INSTANCE.applicationContext)
//        binding.imageRecyclerFA.setHasFixedSize(true)
//        binding.imageRecyclerFA.setItemViewCacheSize(10)
//        binding.imageRecyclerFA.layoutManager= LinearLayoutManager(this@ImageActivity)
//        binding.imageRecyclerFA.adapter= ImageFolderAdapter(this@ImageActivity, imagefolderList, isFolder = true)
//        binding.totalimages.text = "total images : ${currontFolderImages.size}"
//
//    }
//
//
////    fun getAllImages(folderid:String,context:Context): ArrayList<ImageData> {
////
////        val images = ArrayList<ImageData>()
////
////        val allImageUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
////        val projection = arrayOf(
////            MediaStore.Images.Media.DISPLAY_NAME,
////            MediaStore.Images.Media.BUCKET_DISPLAY_NAME,
////            MediaStore.Images.Media.BUCKET_ID,
////            MediaStore.Images.Media.DATA,
////            MediaStore.Images.Media.TITLE,
////            MediaStore.Images.Media.SIZE,
////            MediaStore.Images.Media.DATE_ADDED
////        )
////
////        context.contentResolver.query(allImageUri, projection, null, null, null)?.use { cursor ->
////            if (cursor!= null)
////            if (cursor.moveToFirst()) {
////
////                try {
////
////                    do {
////                        cursor.columnNames.forEach {
////                            Log.d(
////                                "imgcursor",
////                                "${it} ${cursor.getString(cursor.getColumnIndexOrThrow(it))}"
////                            )
////
////                        }
////
////                        val imagePath =
////                            cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA))
////                        val imageName =
////                            cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME))
////                        val folderName =
////                            cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_DISPLAY_NAME))
////                        val title =
////                            cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.TITLE))
////                        val size =
////                            cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.SIZE))
////
////                        ///all images
////
////                        val file= File(imagePath)
////                        val artUri = Uri.fromFile(file)
////                        val image = ImageData(
////                            folderName = folderName, imagePath = imagePath,imageName=imageName,
////                            title = title, size = size,artUri=artUri
////                        )
////
////                        if (file.exists()) images.add(image)
////
////
////                    } while (cursor.moveToNext())
////                    cursor.close()
////                } catch (e: Exception) {
////                    e.printStackTrace()
////
////                }
////            }
////
////        }
////
////        return images
////
////    }
//
//}