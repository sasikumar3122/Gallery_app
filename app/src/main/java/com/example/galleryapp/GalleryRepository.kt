package com.example.galleryapp

import android.content.Context
import android.os.Build
import android.provider.MediaStore
import androidx.annotation.RequiresApi
import java.util.HashMap

object GalleryRepository {


     @RequiresApi(Build.VERSION_CODES.R)
     fun getAllImages(context: Context): List<ImageData> {

         val orderBy = "${MediaStore.Images.ImageColumns.DATE_TAKEN} DESC"

         val findAlbums = HashMap<String, ImageData>()

        val images = ArrayList<ImageData>()
        val allImageUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        val projection = arrayOf(MediaStore.Images.ImageColumns.DATA, MediaStore.Images.Media.DISPLAY_NAME,
        MediaStore.Images.Media.BUCKET_DISPLAY_NAME,
        MediaStore.Images.Media.BUCKET_ID,
        MediaStore.Images.Media.DATA,
            "COUNT(" + MediaStore.Images.ImageColumns._ID + ") AS image_count"
        )
         val groupBy = "1) GROUP BY ${MediaStore.Images.ImageColumns.BUCKET_ID}, (${MediaStore.Images.ImageColumns.BUCKET_DISPLAY_NAME}"

         val albums = mutableListOf<ImageData>()
         context.contentResolver.query(allImageUri,projection,groupBy,null,null)?.use { cursor ->
if (cursor.moveToFirst()) {
    val bucketIdIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.ImageColumns.BUCKET_ID)
    val nameIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.ImageColumns.BUCKET_DISPLAY_NAME)
    val countIndex = cursor.getColumnIndexOrThrow("image_count")

    do {

            val bucketId = cursor.getString(bucketIdIndex)
            val bucketName = cursor.getString(nameIndex)
            val imageCount = cursor.getLong(countIndex)

            val album = ImageData(id = bucketId, name = bucketName, count = imageCount)
            albums.add(album)

            findAlbums[bucketId] = album

                album


            album.count++



        val image = ImageData(bucketName, imageCount, bucketId)
        image.imagePath =
            cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA))
        image.imageName =
            cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME))
        image.folderNames =
            cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_DISPLAY_NAME))


        images.add(image)
        }while (cursor.moveToNext())
        cursor.close()
}
        }

        return images

         return findAlbums.values.toList()

    }
}