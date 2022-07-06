package com.example.galleryapp.Activity

import android.net.Uri
import android.os.Bundle
import android.os.PersistableBundle
import android.provider.MediaStore
import androidx.appcompat.app.AppCompatActivity
import com.example.galleryapp.models.dataClass

class AlbamsFolder : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)

        findAlbums()

    }

    private fun findAlbums(): List<dataClass> {

        val contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI

        val projections = arrayOf(
            MediaStore.Images.ImageColumns._ID,
            MediaStore.Images.ImageColumns.BUCKET_ID,
            MediaStore.Images.ImageColumns.BUCKET_DISPLAY_NAME,
            MediaStore.Images.ImageColumns.DATA
        )

        val orderBy = "${MediaStore.Images.ImageColumns.DATE_TAKEN} DESC"

        val findAlbums = HashMap<String, dataClass>()

        contentResolver.query(contentUri, projections, null, null, orderBy)?.use { cursor ->
            if (cursor.moveToFirst()) {
                val bucketIdIndex =
                    cursor.getColumnIndexOrThrow(MediaStore.Images.ImageColumns.BUCKET_ID)
                val bucketNameIndex =
                    cursor.getColumnIndexOrThrow(MediaStore.Images.ImageColumns.BUCKET_DISPLAY_NAME)
                val imageUriIndex =
                    cursor.getColumnIndexOrThrow(MediaStore.Images.ImageColumns.DATA)

                do {
                    val bucketId = cursor.getString(bucketIdIndex)

                    val album = findAlbums[bucketId] ?: let {
                        val bucketName = cursor.getString(bucketNameIndex)
                        val lastImageUri = Uri.parse(cursor.getString(imageUriIndex))
                        val album = dataClass(
                            id = bucketId,
                            name = bucketName,
                            ImageUri = lastImageUri
                        )
                        findAlbums[bucketId] = album

                        album
                    }

                    album.count++

                } while (cursor.moveToNext())

                // cursor.close()
            }
        }
            return findAlbums.values.toList()
        }

}