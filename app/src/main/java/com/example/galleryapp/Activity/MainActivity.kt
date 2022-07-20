package com.example.galleryapp.Activity

import android.Manifest
import android.Manifest.permission
import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.ProgressBar
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.example.galleryapp.Adapters.ImageAdapter
import com.example.galleryapp.Adapters.ImgAdapter
import com.example.galleryapp.Adapters.VideoAdapter
import com.example.galleryapp.Fragment.ImageFolderFragment
import com.example.galleryapp.Fragment.ImageFragment
import com.example.galleryapp.Fragment.VideoFolderFragment
import com.example.galleryapp.Fragment.VideoFragment
import com.example.galleryapp.GalleryApplication
import com.example.galleryapp.GalleryViewModel
import com.example.galleryapp.R
import com.example.galleryapp.databinding.ActivityMainBinding
import com.example.galleryapp.models.Folders
import com.example.galleryapp.models.ImageData
import com.example.galleryapp.models.ImageFolders
import com.example.galleryapp.models.VideoData
import java.io.File
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity() {


    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
//    private val viewModel by lazy { ViewModelProviders.of(this)[GalleryViewModel::class.java] }

    companion object {
        lateinit var videolist:ArrayList<VideoData>
        lateinit var imagelist:ArrayList<ImageData>
        lateinit var folderslist:ArrayList<Folders>
        lateinit var imagefolderslist:ArrayList<ImageFolders>
    }

    override fun onCreate(savedInstanceState: Bundle?) {
                super.onCreate(savedInstanceState)
        setContentView(binding.root)

//        setupTabIcons()
        checkPermission()
        folderslist= ArrayList()
        imagefolderslist= ArrayList()
            videolist = getAllVideos(GalleryApplication.INSTANCE.applicationContext, filter = "")
            imagelist = getAllImages(GalleryApplication.INSTANCE.applicationContext, filter = "")
            setFragment(VideoFolderFragment())



        binding.bottomnav.setOnItemSelectedListener {
            when(it.itemId){
                R.id.bottomVideoViewtwo -> setFragment(VideoFolderFragment())
                R.id.bottomImageView -> setFragment(ImageFolderFragment())

            }
            return@setOnItemSelectedListener true
        }



    }





    fun setFragment(fragment:Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.framelayout,fragment)
        transaction.disallowAddToBackStack()
        transaction.commit()
    }


    private fun fillImageData() {
        binding.imageRecycler.layoutManager = GridLayoutManager(this, 3)
        if (GalleryApplication.INSTANCE.imageList.size == 0) {

        }
        else{

        }
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
//                viewModel.loadAlbums()
//                viewModel.loadVideoAlbums()
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



    fun getAllVideos(context: Context, filter:String = ""): ArrayList<VideoData> {

        val videos = ArrayList<VideoData>()
        val tempFolderList = ArrayList<String>()
        val allImageUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI
        val projection = arrayOf(
            MediaStore.Video.Media.DISPLAY_NAME,
            MediaStore.Video.Media.BUCKET_DISPLAY_NAME,
            MediaStore.Video.Media.BUCKET_ID,
            MediaStore.Video.Media.DURATION,
            MediaStore.Video.Media.TITLE,
            MediaStore.Video.Media.SIZE,
            MediaStore.Video.Media.DATE_ADDED,
            MediaStore.MediaColumns.DATA,
        )

        context.contentResolver.query(allImageUri, projection, null, null,null
        )?.use { cursor ->
            if (cursor.moveToFirst()) {

                do {
                    val videoPath =
                        cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATA))
                    val title =
                        cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.TITLE))
                    val folderid =
                        cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.BUCKET_ID))
                    val videoName =
                        cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DISPLAY_NAME))
                    val folderName =
                        cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.BUCKET_DISPLAY_NAME))
                    val size =
                        cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.SIZE))
                    val duration =
                        cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DURATION)).toLong()


//                    cursor.columnNames.forEach {
//                        Log.d(
//                            "cursor",
//                            "${it} ${cursor.getString(cursor.getColumnIndexOrThrow(it))}"
//                        )
//
//                    }
                    try {
                        val file= File(videoPath)
                        val artUri = Uri.fromFile(file)
                        val video = VideoData(
                            title = title, folderName = folderName, duration = duration,
                            size = size, videoPath = videoPath, artUri = artUri, videoName = videoName
                        )

                        video.folderName = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.BUCKET_DISPLAY_NAME))

                        ///for adding folders
                        if(!tempFolderList.contains(folderName)){
                            tempFolderList.add(folderName)
                            folderslist.add(Folders(id = folderid,folderName=folderName))
                        }
                        if (file.exists()) videos.add(video)
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                } while (cursor.moveToNext())
                cursor.close()

            }

        }

        return videos

    }


    fun getAllImages(context: Context,filter:String = ""): ArrayList<ImageData> {
        val images = ArrayList<ImageData>()
        val tempFolderList = ArrayList<String>()
        val allImageUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        val projection = arrayOf(
            MediaStore.Images.Media.DISPLAY_NAME,
            MediaStore.Images.Media.BUCKET_DISPLAY_NAME,
            MediaStore.Images.Media.BUCKET_ID,
            MediaStore.Images.Media.DATA,
            MediaStore.Images.Media.DATE_ADDED,
            MediaStore.Images.Media.TITLE,
            MediaStore.Images.Media.SIZE,
        )

        context.contentResolver.query(allImageUri, projection, null, null, null)?.use { cursor ->
            if (cursor.moveToFirst()) {
                    do {
//                        cursor.columnNames.forEach {
//                            Log.d(
//                                "imgcursor",
//                                "${it} ${cursor.getString(cursor.getColumnIndexOrThrow(it))}"
//                            )
//
//                        }

                        val folderid =
                            cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_ID))

                        val imagePath =
                            cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA))
                        val imageName =
                            cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME))
                        val folderName =
                            cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_DISPLAY_NAME))
                        val title =
                            cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.TITLE))
                        val size =
                            cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.SIZE))

                        try {
                            val file= File(imagePath)
                        val artUri = Uri.fromFile(file)
                        val image = ImageData(
                            folderName = folderName, imagePath = imagePath,imageName=imageName,
                            title = title, size = size,artUri=artUri
                        )

                            ///for adding folders
                            image.folderName = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_DISPLAY_NAME))

                            if(!tempFolderList.contains(folderName)) {
                            tempFolderList.add(folderName)
                            imagefolderslist.add(ImageFolders(id = folderid, folderName = folderName))
                        }

                        if (file.exists()) images.add(image)


                    } catch (e: Exception) {
                        e.printStackTrace()

                    }
                    } while (cursor.moveToNext())
                    cursor.close()
            }

        }

        return images

    }

}


