//package com.example.galleryapp.Activity
//
//import android.annotation.SuppressLint
//import android.content.Context
//import android.net.Uri
//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//import android.provider.MediaStore
//import android.util.Log
//import android.view.MenuItem
//import androidx.recyclerview.widget.LinearLayoutManager
//import com.example.galleryapp.Adapters.ImageAdapter
//import com.example.galleryapp.Adapters.ImageFolderAdapter
//import com.example.galleryapp.Adapters.ImgAdapter
//import com.example.galleryapp.GalleryApplication
//import com.example.galleryapp.databinding.ActivityImageFoldersBinding
//import com.example.galleryapp.databinding.ActivityMainBinding
//import com.example.galleryapp.databinding.ImageActivityFoldersBinding
//import com.example.galleryapp.models.Folders
//import com.example.galleryapp.models.ImageData
//import com.example.galleryapp.models.ImageFolders
//import com.example.galleryapp.models.VideoData
//import java.io.File
//
//class ImageFoldersActivity : AppCompatActivity() {
//
//    private val binding: ImageActivityFoldersBinding by lazy { ImageActivityFoldersBinding.inflate(layoutInflater) }
//
//
//    companion object{
//        lateinit var currontFolderImages :ArrayList<ImageData>
//    }
//    @SuppressLint("SetTextI18n")
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(binding.root)
//
//        val position = intent.getIntExtra("positon",0)
//
//        supportActionBar?.setDisplayHomeAsUpEnabled(true)
//        supportActionBar?.title=MainActivity.folderslist[position].folderName
//        currontFolderImages = getAllImages(MainActivity.imagefolderslist[position].id)
//        binding.imageFolderRecyclerFA.setHasFixedSize(true)
//        binding.imageFolderRecyclerFA.setItemViewCacheSize(10)
//        binding.imageFolderRecyclerFA.layoutManager= LinearLayoutManager(this@ImageFoldersActivity)
//        binding.imageFolderRecyclerFA.adapter= ImgAdapter(this@ImageFoldersActivity,
//            currontFolderImages
//            ,isFolder = true
//                )
//        binding.imagetotalFolders.text = "Total Video : ${currontFolderImages.size}"
//
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        finish()
//        return true
//    }
//
//    fun getAllImages(folderid:String,): ArrayList<ImageData> {
//
//        val images = ArrayList<ImageData>()
//        val selection = MediaStore.Images.Media.BUCKET_ID + " like? "
//        val allImageUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
//        val projection = arrayOf(
//            MediaStore.Images.Media.DISPLAY_NAME,
//            MediaStore.Images.Media.BUCKET_DISPLAY_NAME,
//            MediaStore.Images.Media.BUCKET_ID,
//            MediaStore.Images.Media.DATA,
//            MediaStore.Images.Media.TITLE,
//            MediaStore.Images.Media.SIZE,
//            MediaStore.Images.Media.DATE_ADDED
//        )
//
//        this.contentResolver.query(allImageUri, projection, selection,
//            arrayOf(folderid), null)?.use { cursor ->
//
//                if (cursor.moveToFirst()) {
//
//                    try {
//
//                        do {
////                            cursor.columnNames.forEach {
////                                Log.d(
////                                    "imgcursor",
////                                    "${it} ${cursor.getString(cursor.getColumnIndexOrThrow(it))}"
////                                )
////
////                            }
//
//                            val imagePath =
//                                cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA))
//                            val imageName =
//                                cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME))
//                            val folderName =
//                                cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_DISPLAY_NAME))
//                            val title =
//                                cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.TITLE))
//                            val size =
//                                cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.SIZE))
//
//                            ///all images
//
//                            val file= File(imagePath)
//                            val artUri = Uri.fromFile(file)
//                            val image = ImageData(
//                                folderName = folderName, imagePath = imagePath,imageName=imageName,
//                                title = title, size = size,artUri=artUri
//                            )
//                            image.folderName = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_DISPLAY_NAME))
//
//                            if (file.exists()) images.add(image)
//
//
//                        } while (cursor.moveToNext())
//                        cursor.close()
//                    } catch (e: Exception) {
//                        e.printStackTrace()
//
//                    }
//                }
//
//        }
//
//        return images
//
//    }
//}