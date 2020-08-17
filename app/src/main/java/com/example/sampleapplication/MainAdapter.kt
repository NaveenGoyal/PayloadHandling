package com.example.sampleapplication

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.single_item.view.*


const val FAVORITE_CHANGE = "FAVORITE_CHANGE"
class MainAdapter(imgList: MutableList<ImageItem>, var clickCallback: ClickCallback) : RecyclerView.Adapter<MainViewHolder>() {

    var imageList = imgList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.single_item, parent, false)
        return MainViewHolder(view)
    }

    override fun getItemCount(): Int {
        return imageList.size;
    }

    override fun onBindViewHolder(
        holder: MainViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        holder.bind(imageList[position])
        holder.itemView.setOnClickListener {
            Log.d("Naveen", "ItemView clicked")
            this.clickCallback.imageClick(imageList[position])
        }
        if(payloads.isNotEmpty()) {
            when(payloads[0]) {
                FAVORITE_CHANGE -> bindFavoriteIcon(holder, imageList[position].isFavored)
            }
        } else {
            bindFavoriteIcon(holder, imageList[position].isFavored)
            super.onBindViewHolder(holder, position, payloads)
        }
    }


    private fun bindFavoriteIcon(holder: MainViewHolder, isFavored:Boolean) {
        if(isFavored) {
            holder.itemView.favIcon.setImageResource(R.drawable.ic_faved)
        } else {
            holder.itemView.favIcon.setImageResource(R.drawable.ic_wishlist_unfilled)
        }
    }

    interface ClickCallback {
        fun imageClick(item: ImageItem)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
//        holder.itemView.imgView.setBackgroundResource(imageList[position].imageId)
//        holder.itemView.favIcon.setBackgroundResource(R.drawable.ic_fav)
//
//        holder.itemView.setOnClickListener {
//            Log.d("Naveen", "ItemView clicked")
//           clickCallback.imageClick(imageList[position])
//        }
    }


}

open class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(item: ImageItem) {
        itemView.imgView.setBackgroundResource(item.imageId)
        itemView.favIcon.setBackgroundResource(R.drawable.ic_fav)

    }
}
