package com.curtcaldwell.flickrbrowser

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso


class FlickerImageViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    var thumbNail : ImageView = view.findViewById(R.id.thumbnail)
    var title: TextView = view.findViewById(R.id.title)
}
class FlickrRecyclerViewAdapter(private var photoList : List<Photo>) : RecyclerView.Adapter<FlickerImageViewHolder>(){

    private val TAG = "FlickrRecyclerViewadapt"

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlickerImageViewHolder {

        Log.d(TAG, ".onCreateViewHolder new view requested")

        val view = LayoutInflater.from(parent.context).inflate(R.layout.browse, parent, false)
        return FlickerImageViewHolder(view)
    }

    override fun getItemCount(): Int {

//        Log.d(TAG, ".getItemCount called")
        return if (photoList.isNotEmpty()) photoList.size else 0
    }

    fun loadNewData(newPhotos: List<Photo>){
        photoList = newPhotos
        notifyDataSetChanged()

    }

    fun getPhoto(position: Int): Photo?{
        return if(photoList.isNotEmpty()) photoList[position] else null
    }

    override fun onBindViewHolder(holder: FlickerImageViewHolder, position: Int) {

        val photoItem = photoList[position]
        Picasso.get().load(photoItem.image)
            .error(R.drawable.placeholder)
            .placeholder(R.drawable.placeholder)
            .into(holder.thumbNail)

        holder.title.text = photoItem.title



    }

}