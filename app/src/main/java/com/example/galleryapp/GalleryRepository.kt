package com.example.galleryapp

import android.content.Context
import android.graphics.Insets.add
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

}

