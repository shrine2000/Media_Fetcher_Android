package com.example.imageview

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

import java.io.File
import java.lang.RuntimeException
import java.util.*
import kotlin.collections.ArrayList


class ImageAdapter(private val context: Context) : RecyclerView.Adapter<ImageAdapter.ViewHolder>(){


    private val listdata = ArrayList<String>()

    fun addAll(arrayList: ArrayList<String>){
        listdata.clear()
        listdata.addAll(arrayList)
        notifyDataSetChanged()
    }



    override fun getItemId(position: Int): Long {
        return position.toLong()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context) as LayoutInflater
        val listItem = layoutInflater.inflate(R.layout.layout_status_item, parent, false) as View
        return ViewHolder(listItem)
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {

        val file = listdata[p1]

        Glide.with(context)
            .load(file)
            .centerCrop()
            .into(p0.imageView)

        if (isVideo(File(file))){
            visibility(p0.video,null)
            p0.video.setImageDrawable(context.getDrawable(R.drawable.ic_play))


        }
        else {
            visibility(null,p0.video)
        }


        if ((MainActivity).longpress.contains(file)){
            visibility(p0.playbutton,null)
            p0.playbutton.setImageDrawable(context.getDrawable(R.drawable.ic_verified))

        }

        else {
            visibility(null, p0.playbutton)
        }



    }



    override fun getItemCount() = listdata.size



    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val imageView: ImageView = itemView.findViewById(R.id.thumbnailIv) as ImageView

        val video: ImageView = itemView.findViewById(R.id.videoplay) as ImageView

        val playbutton: ImageView = itemView.findViewById(R.id.playIv) as ImageView


    }


}