package com.example.galleryapp

import android.content.Context
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import com.example.galleryapp.Activity.MainActivity
import com.example.galleryapp.models.Folders
import com.example.galleryapp.models.ImageData
import com.example.galleryapp.models.ImageFolders
import com.example.galleryapp.models.VideoData
import java.io.File

object GalleryRepository {

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
//                do {
////                        cursor.columnNames.forEach {
////                            Log.d(
////                                "imgcursor",
////                                "${it} ${cursor.getString(cursor.getColumnIndexOrThrow(it))}"
////                            )
////
////                        }
//
//                    val folderid =
//                        cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_ID))
//
//                    val imagePath =
//                        cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA))
//                    val imageName =
//                        cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME))
//                    val folderName =
//                        cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_DISPLAY_NAME))
//                    val title =
//                        cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.TITLE))
//                    val size =
//                        cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.SIZE))
//
//                    try {
//                        val file= File(imagePath)
//                        val artUri = Uri.fromFile(file)
//                        val image = ImageData(
//                            folderName = folderName, imagePath = imagePath,imageName=imageName,
//                            title = title, size = size,artUri=artUri
//                        )
//
//                        ///for adding folders
//                        image.folderName = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_DISPLAY_NAME))
//
//                        if(!tempFolderList.contains(folderName)) {
//                            tempFolderList.add(folderName)
//                            MainActivity.imagefolderslist.add(ImageFolders(id = folderid, folderName = folderName))
//                        }
//
//                        if (file.exists()) images.add(image)
//
//
//                    } catch (e: Exception) {
//                        e.printStackTrace()
//
//                    }
//                } while (cursor.moveToNext())
//                cursor.close()
//            }
//
//        }
//
//        return images
//
//    }



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
//                            MainActivity.folderslist.add(Folders(id = folderid,folderName=folderName))
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


}

