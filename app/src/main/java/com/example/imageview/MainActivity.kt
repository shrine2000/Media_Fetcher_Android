package com.example.imageview

import android.Manifest
 import android.content.pm.PackageManager
import android.database.Cursor
import android.graphics.Color
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.View
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.ActionBar
import androidx.core.content.ContextCompat
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.loader.app.LoaderManager
import androidx.loader.content.CursorLoader
import androidx.loader.content.Loader
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide.init
import com.google.android.material.internal.ContextUtils.getActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import java.io.File
import java.io.IOException
import kotlin.coroutines.CoroutineContext

var image = ArrayList<String>()
var video = ArrayList<String>()
 

class MainActivity : AppCompatActivity() {


    companion object{
        var longpress = ArrayList<String>()
    }

    lateinit var imageAdapter: ImageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            askForPermission()
        }


        image = fetchGalleryImages(this)
        video = fetchGalleryVideo(this)

        image.addAll(video)


        val recyclerView =  findViewById<RecyclerView>(R.id.recycler_view)
        val gridLayoutManager = GridLayoutManager(applicationContext,3)
        imageAdapter = ImageAdapter(applicationContext)
        imageAdapter.addAll(image)


        recyclerView.layoutManager = gridLayoutManager
        recyclerView.setHasFixedSize(true)
        recyclerView.hasFixedSize()
        recyclerView.adapter = imageAdapter


        recyclerView.addOnItemTouchListener(RecyclerTouchListener(this,
            recyclerView,
            object : RecyclerTouchListener.ClickListener{
                override fun onClick(view: View?, position: Int) {

                    val imageView = view!!.findViewById<ImageView>(R.id.playIv)
                    visibility(imageView,null)
                    imageView.setImageDrawable(getDrawable(R.drawable.ic_verified))
                    longpress.add(image[position])


                }

                override fun onLongClick(view: View?, position: Int) {



                 }

            }))


        sent.setOnClickListener {

            visibility(recyclerView,receive_tv)

            imageAdapter = ImageAdapter(applicationContext)
            imageAdapter.notifyDataSetChanged()
            imageAdapter.addAll(longpress)

            recyclerView.layoutManager = gridLayoutManager
            recyclerView.setHasFixedSize(true)
            recyclerView.hasFixedSize()
            recyclerView.adapter = imageAdapter


        }

        media.setOnClickListener{

            visibility(recyclerView,receive_tv)

            longpress.clear()

            imageAdapter = ImageAdapter(applicationContext)
            imageAdapter.addAll(image)
            imageAdapter.notifyDataSetChanged()


            recyclerView.layoutManager = gridLayoutManager
            recyclerView.setHasFixedSize(true)
            recyclerView.hasFixedSize()
            recyclerView.adapter = imageAdapter
        }

        receive.setOnClickListener{

            visibility(receive_tv,recyclerView)


        }

    }






    @RequiresApi(Build.VERSION_CODES.M)
    private fun askForPermission() {

        if(ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED){
            //ask for permission
            requestPermissions( arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), 100)
        }
    }



}
