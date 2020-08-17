package com.example.sampleapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(){

    val imgList = mutableListOf<ImageItem>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var imageItem = ImageItem()
        imageItem.imageId = R.drawable.bear1
        imageItem.isFavored = false

        var imageItem2 = ImageItem()
        imageItem2.imageId = R.drawable.bear2
        imageItem2.isFavored = false

        imgList.add(imageItem)
        imgList.add(imageItem2)

        var adapter = MainAdapter(imgList, object: MainAdapter.ClickCallback {
            override fun imageClick(item: ImageItem) {
                item.isFavored = !item.isFavored
                recyclerView.adapter?.notifyItemChanged(imgList.indexOf(item), FAVORITE_CHANGE)
            }
        })
        var linearLayoutManager: LinearLayoutManager = LinearLayoutManager(this)

        recyclerView.layoutManager = linearLayoutManager
        recyclerView.adapter = adapter


    }
}