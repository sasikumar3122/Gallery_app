package com.example.galleryapp

import android.content.Context
import android.database.Cursor
import android.provider.MediaStore
import android.util.Log
import com.example.galleryapp.models.ImageData

object GalleryRepository {


    fun getAllImages(context: Context,filter:String = ""): List<ImageData> {

        val images = ArrayList<ImageData>()
        val allImageUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        val projection = arrayOf(
            MediaStore.Images.Media.DISPLAY_NAME,
            MediaStore.Images.Media.BUCKET_DISPLAY_NAME,
            MediaStore.Images.Media.BUCKET_ID,
            MediaStore.Images.Media.DATA,
        )

        context.contentResolver.query(allImageUri, projection, null, null, null)?.use { cursor ->
            if (cursor.moveToFirst()) {

                try {

                    do {
                        cursor.columnNames.forEach {
                            Log.d(
                                "cursor",
                                "${it} ${cursor.getString(cursor.getColumnIndexOrThrow(it))}"
                            )

                        }


                        val image = ImageData()
                        image.imagePath =
                            cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA))
                        image.imageName =
                            cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME))
                        image.folderName =
                            cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_DISPLAY_NAME))

                     if (image.folderName==filter || filter.isEmpty())
                        images.add(image)
                    } while (cursor.moveToNext())
                    cursor.close()
                } catch (e: Exception) {
                    e.printStackTrace()

                }
            }

        }

        return images

    }

    fun getAlbumNames(context: Context): List<ImageData> {
            val albumNames = ArrayList<ImageData>()
        val allImageUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        val projection = arrayOf(
            MediaStore.Images.Media.BUCKET_DISPLAY_NAME,
            MediaStore.Images.Media.BUCKET_ID,
        )
        context.contentResolver.query(allImageUri, projection, null, null, null)?.use { cursor ->
            if (cursor.moveToFirst()) {
                try {
                    do {
                        val imageData = ImageData()
                        imageData.folderName = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_DISPLAY_NAME))
                        if(!albumNames.contains(imageData))
                          albumNames.add(imageData)
                    } while (cursor.moveToNext())
                    cursor.close()
                } catch (e: Exception) {
                    e.printStackTrace()

                }
            }

        }
    return albumNames
    }


    fun getAllVideos(context: Context,filter:String = ""): List<ImageData> {

        val videos = ArrayList<ImageData>()
        val allImageUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI
        val projection = arrayOf(
            MediaStore.Video.Media.DISPLAY_NAME,
            MediaStore.Video.Media.BUCKET_DISPLAY_NAME,
            MediaStore.Video.Media.BUCKET_ID,
            MediaStore.Video.Media._ID,
//            MediaStore.Video.Thumbnails.DATA,
            MediaStore.MediaColumns.DATA,
        )

        context.contentResolver.query(allImageUri, projection, null, null, null)?.use { cursor ->
            if (cursor.moveToFirst()) {

                try {

                    do {
                        cursor.columnNames.forEach {
                            Log.d(
                                "cursor",
                                "${it} ${cursor.getString(cursor.getColumnIndexOrThrow(it))}"
                            )

                        }


                        val video = ImageData()
                        video.videoPath =
                            cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATA))
                        video.videoName =
                            cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DISPLAY_NAME))
                        video.folderName =
                            cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.BUCKET_DISPLAY_NAME))

                        if (video.folderName==filter || filter.isEmpty())
                            videos.add(video)
                    } while (cursor.moveToNext())
                    cursor.close()
                } catch (e: Exception) {
                    e.printStackTrace()

                }
            }

        }

        return videos

    }



    fun getVideolbumNames(context: Context): List<ImageData> {
        val albumNames = ArrayList<ImageData>()
        val allVideoUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        val projection = arrayOf(
            MediaStore.Video.Media.BUCKET_DISPLAY_NAME,
            MediaStore.Video.Media.BUCKET_ID,
        )
        context.contentResolver.query(allVideoUri, projection, null, null, null)?.use { cursor ->
            if (cursor.moveToFirst()) {
                try {
                    do {
                        val videoData = ImageData()
                        videoData.folderName = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.BUCKET_DISPLAY_NAME))
                        if(!albumNames.contains(videoData))
                            albumNames.add(videoData)
                    } while (cursor.moveToNext())
                    cursor.close()
                } catch (e: Exception) {
                    e.printStackTrace()

                }
            }

        }
        return albumNames
    }


    private fun getAlbumCount(context: Context, id: Long): Int {
        var c = 0
        val selection = ("( "
                + MediaStore.Files.FileColumns.MEDIA_TYPE
                + "=? ) and "
                + MediaStore.Files.FileColumns.PARENT
                + "=?")
        val selectionArgs =
            arrayOf(MediaStore.Files.FileColumns.MEDIA_TYPE_IMAGE.toString(), id.toString())
        val cur: Cursor? = context
            .contentResolver
            .query(
                MediaStore.Files.getContentUri("external"),
                arrayOf(MediaStore.Files.FileColumns.PARENT),
                selection,
                selectionArgs,
                null
            )
        if (cur != null) {
            c = cur.getCount()
            cur.close()
        }
        return c
    }



}

