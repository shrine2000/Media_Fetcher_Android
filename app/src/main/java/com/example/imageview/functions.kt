package com.example.imageview

import android.content.Context
import android.graphics.Typeface
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import java.io.File
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import androidx.core.content.FileProvider
import android.provider.MediaStore
import android.app.Activity



fun visibility(visible : View?, gone : View?) {
    visible?.visibility = View.VISIBLE
    gone?.visibility = View.GONE
}


fun fetchGalleryImages(context: Activity): ArrayList<String> {
    val galleryImageUrls: ArrayList<String> = ArrayList()
    val columns = arrayOf(
        MediaStore.Images.Media.DATA,
        MediaStore.Images.Media._ID
    )//get all columns of type images
    val orderBy = MediaStore.Images.Media.DATE_TAKEN//order data by date

    val imagecursor = context.managedQuery(
        MediaStore.Images.Media.EXTERNAL_CONTENT_URI, columns,
        null, null, "$orderBy DESC"
    )//get all data in Cursor by sorting in DESC order

    for (i in 0 until imagecursor.count) {
        imagecursor.moveToPosition(i)
        val dataColumnIndex =
            imagecursor.getColumnIndex(MediaStore.Images.Media.DATA)//get column index
        galleryImageUrls.add(imagecursor.getString(dataColumnIndex))//get Image from column index

    }

    return galleryImageUrls
}


fun fetchGalleryVideo(context: Activity): ArrayList<String> {
    val galleryVideoUrls: ArrayList<String> = ArrayList()
    val columns = arrayOf(
        MediaStore.Video.Media.DATA,
        MediaStore.Video.Media._ID
    )//get all columns of type images
    val orderBy = MediaStore.Video.Media.DATE_TAKEN//order data by date

    val imagecursor = context.managedQuery(
        MediaStore.Video.Media.EXTERNAL_CONTENT_URI, columns,
        null, null, "$orderBy DESC"
    )//get all data in Cursor by sorting in DESC order

    for (i in 0 until imagecursor.count) {
        imagecursor.moveToPosition(i)
        val dataColumnIndex =
            imagecursor.getColumnIndex(MediaStore.Images.Media.DATA)//get column index
        galleryVideoUrls.add(imagecursor.getString(dataColumnIndex))//get Image from column index


    }

    return galleryVideoUrls
}

fun isVideo(file: File): Boolean = file.path.endsWith("mp4")
