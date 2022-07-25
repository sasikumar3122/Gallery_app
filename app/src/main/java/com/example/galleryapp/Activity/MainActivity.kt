package com.example.galleryapp.Activity

import android.Manifest
import android.Manifest.permission
import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.view.View
import android.widget.ProgressBar
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.example.galleryapp.Adapters.ImageAdapter
import com.example.galleryapp.Adapters.VideoAdapter
import com.example.galleryapp.GalleryApplication
import com.example.galleryapp.GalleryViewModel
import com.example.galleryapp.databinding.ActivityMainBinding
import com.example.galleryapp.models.AlbumsDatas
import com.example.galleryapp.models.ImageData
import com.google.android.exoplayer2.util.Log.d
import java.io.File
import java.io.File.separator


class MainActivity : AppCompatActivity() {



    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private var adapter = ImageAdapter(this)
    private var videoadapter = VideoAdapter(this)
    private var progressBar: ProgressBar? = null
    var photoids: ArrayList<Int> = ArrayList()
    var albumList:ArrayList<AlbumsDatas> = ArrayList()
    var photoList:ArrayList<ImageData> = ArrayList()
    private val viewModel by lazy { ViewModelProviders.of(this)[GalleryViewModel::class.java] }

//companion object{
//    lateinit var   imgdata :ArrayList<ImageData>
//
//}


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        checkPermission()
        getAllImages(albumList)

    }


    fun getAllImages(albums:ArrayList<AlbumsDatas>){
        albumList= ArrayList()
        albums.sortWith(compareBy { it.name })
        for (album in albums) {
            albumList.add(album)
        }
        albumList.add(0, AlbumsDatas(0, "All Photos", albumPhotos = photoList))
        photoList.sortWith(compareByDescending { File(it.imagePath).lastModified() })

        for (id in photoids) {
            for (image in photoList) {
                if (id == image.id) image.isSelected = true
            }
        }

    }



    private fun reload(){
        binding.recyclerProgress.visibility = View.VISIBLE
        getImgData()
        binding.imageRecycler.adapter = ImageAdapter(this@MainActivity).apply {
            clickedFolder = this@MainActivity.clickedFolder

        }
//        binding.imageRecycler.adapter = VideoAdapter(this@MainActivity).apply {
//            clickFolder = this@MainActivity.clickFolder
//        }
        binding.recyclerProgress.visibility = View.GONE
    }
    private fun fillImageData() {
        binding.imageRecycler.layoutManager = GridLayoutManager(this, 3)

        if (GalleryApplication.INSTANCE.imageList.size == 0) {
            reload()
        }
        else{
            reload()
        }
    }

    val clickedFolder = object : ImageAdapter.OnFolderSelectListener{
        override fun onFolderSelected(folderName: String) {
            viewModel.loadImages(folderName)
            fillImageData()
            getAllImages(albumList)



        }
    }
//    val clickFolder = object : VideoAdapter.OnFolderSelectListeners{
//        override fun onFolderSelected(folderName: String) {
//            viewModel.loadVideos(folderName)
//            fillImageData()
//        }
//    }

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onResume() {
        super.onResume()
        refresh()
        viewModel.loadAlbums()
        viewModel.loadVideoAlbums()
        adapter.clickedFolder = this@MainActivity.clickedFolder
//        videoadapter.clickFolder = this@MainActivity.clickFolder
    }

//permission request

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 100) {
            if (grantResults.isNotEmpty() && grantResults[0]
                == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                viewModel.loadAlbums()
                viewModel.loadVideoAlbums()
                fillImageData()

            } else {
                requestStoragePermission()
            }
        }
    }

    private fun checkPermission() {
        if (ContextCompat.checkSelfPermission(
                this@MainActivity,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
            && ContextCompat.checkSelfPermission(
                this,
                permission.WRITE_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this@MainActivity,
                arrayOf(
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    permission.WRITE_EXTERNAL_STORAGE
                ), 100
            )
        } else {
            fillImageData()

        }
    }

    private fun requestStoragePermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(
                this,
                permission.READ_EXTERNAL_STORAGE
            )
        ) {
            AlertDialog.Builder(this)
                .setTitle("Permission needed")
                .setMessage("This permission is needed ")
                .setPositiveButton("ok",
                    DialogInterface.OnClickListener { _, _ ->
                        ActivityCompat.requestPermissions(
                            this@MainActivity, arrayOf(
                                permission.READ_EXTERNAL_STORAGE, permission.WRITE_EXTERNAL_STORAGE
                            ), 100
                        )
                    })
                .create().show()
        } else {
            checkPermission()
        }
    }


    @SuppressLint("NotifyDataSetChanged")
    fun getImgData() {
        viewModel.getImageLiveDataObserver().observe(this, Observer {
            GalleryApplication.INSTANCE.imageList = it as ArrayList
            GalleryApplication.INSTANCE.imageList.reverse()
            adapter.notifyDataSetChanged()
        })
    }


    private fun refresh() {
        binding.refreshScreen.setOnRefreshListener {
            progressBar?.visibility = View.VISIBLE

            progressBar?.visibility = View.GONE

        }
    }

}



//
//    override fun onCreate(savedInstanceState: Bundle?) {
//                super.onCreate(savedInstanceState)
//        setContentView(binding.root)
//
////        setupTabIcons()
//        checkPermission()
//        folderslist= ArrayList()
//        imagefolderslist= ArrayList()
//            videolist = getAllVideos(GalleryApplication.INSTANCE.applicationContext, filter = "")
//            imagelist = getAllImages(GalleryApplication.INSTANCE.applicationContext, filter = "")
//            setFragment(VideoFolderFragment())
//
//    fun setFragment(fragment:Fragment){
//        val transaction = supportFragmentManager.beginTransaction()
//        transaction.replace(R.id.framelayout,fragment)
//        transaction.disallowAddToBackStack()
//        transaction.commit()
//    }
//
//
//    private fun fillImageData() {
//        binding.imageRecycler.layoutManager = GridLayoutManager(this, 3)
//        if (GalleryApplication.INSTANCE.imageList.size == 0) {
//
//        }
//        else{
//
//        }
//    }
//
//
//
//

//    fun getAllVideos(context: Context, filter:String = ""): ArrayList<VideoData> {
//
//        val videos = ArrayList<VideoData>()
//        val tempFolderList = ArrayList<String>()
//        val allImageUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI
//        val projection = arrayOf(
//            MediaStore.Video.Media.DISPLAY_NAME,
//            MediaStore.Video.Media.BUCKET_DISPLAY_NAME,
//            MediaStore.Video.Media.BUCKET_ID,
//            MediaStore.Video.Media.DURATION,
//            MediaStore.Video.Media.TITLE,
//            MediaStore.Video.Media.SIZE,
//            MediaStore.Video.Media.DATE_ADDED,
//            MediaStore.MediaColumns.DATA,
//        )
//
//        context.contentResolver.query(allImageUri, projection, null, null,null
//        )?.use { cursor ->
//            if (cursor.moveToFirst()) {
//
//                do {
//                    val videoPath =
//                        cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATA))
//                    val title =
//                        cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.TITLE))
//                    val folderid =
//                        cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.BUCKET_ID))
//                    val videoName =
//                        cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DISPLAY_NAME))
//                    val folderName =
//                        cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.BUCKET_DISPLAY_NAME))
//                    val size =
//                        cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.SIZE))
//                    val duration =
//                        cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DURATION)).toLong()
//
//
////                    cursor.columnNames.forEach {
////                        Log.d(
////                            "cursor",
////                            "${it} ${cursor.getString(cursor.getColumnIndexOrThrow(it))}"
////                        )
////
////                    }
//                    try {
//                        val file= File(videoPath)
//                        val artUri = Uri.fromFile(file)
//                        val video = VideoData(
//                            title = title, folderName = folderName, duration = duration,
//                            size = size, videoPath = videoPath, artUri = artUri, videoName = videoName
//                        )
//
//                        video.folderName = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.BUCKET_DISPLAY_NAME))
//
//                        ///for adding folders
//                        if(!tempFolderList.contains(folderName)){
//                            tempFolderList.add(folderName)
//                            folderslist.add(Folders(id = folderid,folderName=folderName))
//                        }
//                        if (file.exists()) videos.add(video)
//                    } catch (e: Exception) {
//                        e.printStackTrace()
//                    }
//                } while (cursor.moveToNext())
//                cursor.close()
//
//            }
//
//        }
//
//        return videos
//
//    }
//
//
//    fun getAllImages(context: Context,filter:String = ""): ArrayList<ImageData> {
//        val images = ArrayList<ImageData>()
//        val tempFolderList = ArrayList<String>()
//        val allImageUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
//        val projection = arrayOf(
//            MediaStore.Images.Media.DISPLAY_NAME,
//            MediaStore.Images.Media.BUCKET_DISPLAY_NAME,
//            MediaStore.Images.Media.BUCKET_ID,
//            MediaStore.Images.Media.DATA,
//            MediaStore.Images.Media.DATE_ADDED,
//            MediaStore.Images.Media.TITLE,
//            MediaStore.Images.Media.SIZE,
//        )
//
//        context.contentResolver.query(allImageUri, projection, null, null, null)?.use { cursor ->
//            if (cursor.moveToFirst()) {
//                    do {
////                        cursor.columnNames.forEach {
////                            Log.d(
////                                "imgcursor",
////                                "${it} ${cursor.getString(cursor.getColumnIndexOrThrow(it))}"
////                            )
////
////                        }
//
//                        val folderid =
//                            cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_ID))
//
//                        val imagePath =
//                            cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA))
//                        val imageName =
//                            cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME))
//                        val folderName =
//                            cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_DISPLAY_NAME))
//                        val title =
//                            cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.TITLE))
//                        val size =
//                            cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.SIZE))
//
//                        try {
//                            val file= File(imagePath)
//                        val artUri = Uri.fromFile(file)
//                        val image = ImageData(
//                            folderName = folderName, imagePath = imagePath,imageName=imageName,
//                            title = title, size = size,artUri=artUri
//                        )
//
//                            ///for adding folders
//                            image.folderName = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_DISPLAY_NAME))
//
//                            if(!tempFolderList.contains(folderName)) {
//                            tempFolderList.add(folderName)
//                            imagefolderslist.add(ImageFolders(id = folderid, folderName = folderName))
//                        }
//
//                        if (file.exists()) images.add(image)
//
//
//                    } catch (e: Exception) {
//                        e.printStackTrace()
//
//                    }
//                    } while (cursor.moveToNext())
//                    cursor.close()
//            }
//
//        }
//
//        return images
//
//    }
//
//}










