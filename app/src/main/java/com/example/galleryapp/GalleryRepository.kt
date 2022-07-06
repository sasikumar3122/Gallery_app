package com.example.galleryapp

import android.content.Context
import android.provider.MediaStore
import com.example.galleryapp.models.ImageData

object GalleryRepository {



     fun getAllImages(context: Context): List<ImageData> {


    val images = ArrayList<ImageData>()
    val allImageUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
    val projection = arrayOf(
        MediaStore.Images.ImageColumns.DATA, MediaStore.Images.Media.DISPLAY_NAME,
        MediaStore.Images.Media.BUCKET_DISPLAY_NAME,
        MediaStore.Images.Media.BUCKET_ID,
        MediaStore.Images.Media.DATA,
    )

    context.contentResolver.query(allImageUri, projection, null, null, null)?.use { cursor ->
        if (cursor.moveToFirst()) {

            try {

                do {


                    val image = ImageData()
                    image.imagePath =
                        cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA))
                    image.imageName =
                        cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME))
                    image.folderNames =
                        cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_DISPLAY_NAME))


                    images.add(image)
                } while (cursor.moveToNext())
                cursor.close()
            } catch (e: Exception) {
                e.printStackTrace()

            }
        }

        }

        return images

}}
