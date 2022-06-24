package com.example.galleryapp

import android.content.Context
import android.provider.MediaStore
import java.lang.Exception

object GalleryRepository {


     fun getAllImages(context: Context): ArrayList<ImageData> {

        val images = ArrayList<ImageData>()
        val allImageUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        val projection = arrayOf(MediaStore.Images.ImageColumns.DATA, MediaStore.Images.Media.DISPLAY_NAME)
        val cursor = context.contentResolver.query(allImageUri,projection,null,null,null)


        try {
            cursor!!.moveToFirst()
            do {
                val image = ImageData()
                image.imagePath=cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA))
                image.imageName=cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME))
//                image.imageDateTime=cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATE_TAKEN))

                images.add(image)
            }while (cursor.moveToNext())
            cursor.close()
        }catch (e: Exception)
        {
            e.printStackTrace()
        }
        return images

    }
}