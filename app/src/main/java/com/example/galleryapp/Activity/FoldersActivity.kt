package com.example.galleryapp.Activity

import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.galleryapp.Adapters.VideoAdapter
import com.example.galleryapp.GalleryApplication
import com.example.galleryapp.databinding.ActivityFoldersBinding
import com.example.galleryapp.models.VideoData
import java.io.File

class FoldersActivity() : AppCompatActivity() {
    private val binding: ActivityFoldersBinding by lazy { ActivityFoldersBinding.inflate(layoutInflater) }


    companion object{
        lateinit var currontFolderVideos :ArrayList<VideoData>
    }
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)



        val position = intent.getIntExtra("positon",0)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title=MainActivity.folderslist[position].folderName
        currontFolderVideos = getAllVideos(MainActivity.folderslist[position].id)
        binding.videoRecyclerFA.setHasFixedSize(true)
        binding.videoRecyclerFA.setItemViewCacheSize(10)
        binding.videoRecyclerFA.layoutManager= LinearLayoutManager(this@FoldersActivity)
        binding.videoRecyclerFA.adapter= VideoAdapter(this@FoldersActivity,
            currontFolderVideos
            ,isFolder = true
        )
        binding.totalVideos.text = "Total Video : ${currontFolderVideos.size}"

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        finish()
        return true
    }

    fun getAllVideos(folderid:String): ArrayList<VideoData> {
        val videos = ArrayList<VideoData>()
        val selection = MediaStore.Video.Media.BUCKET_ID + " like? "
        val orderby = MediaStore.Video.Media.DATE_ADDED + "DESC"
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

        this.contentResolver.query(allImageUri, projection, selection, arrayOf(folderid),
            null)?.use { cursor ->

            if (cursor.moveToFirst()) {


                do {


                    val videoPath =
                        cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATA))
                    val title =
                        cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.TITLE))
                    val videoName =
                        cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DISPLAY_NAME))
                    val folderName =
                        cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.BUCKET_DISPLAY_NAME))
                    val size =
                        cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.SIZE))
                    val duration =
                        cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DURATION)).toLong()


                    cursor.columnNames.forEach {
                        Log.d(
                            "cursor",
                            "${it} ${cursor.getString(cursor.getColumnIndexOrThrow(it))}"
                        )

                    }
                    try {
                        val file= File(videoPath)
                        val artUri = Uri.fromFile(file)
                        val video = VideoData(
                            title = title, folderName = folderName, duration = duration,
                            size = size, videoPath = videoPath, artUri = artUri, videoName = videoName
                        )
                        video.folderName = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.BUCKET_DISPLAY_NAME))


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
}