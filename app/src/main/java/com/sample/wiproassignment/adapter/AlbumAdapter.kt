package com.sample.wiproassignment.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sample.wiproassignment.databinding.AlbumLayoutItemBinding
import com.sample.wiproassignment.model.AlbumResponseModelItem

class AlbumAdapter(private var albumList:List<AlbumResponseModelItem>):RecyclerView.Adapter<AlbumAdapter.AlbumViewHolder>() {
    class AlbumViewHolder(private val binding: AlbumLayoutItemBinding) :RecyclerView.ViewHolder(binding.root) {
        fun bind(albumResponseModelItem: AlbumResponseModelItem) {
            binding.dataModel = albumResponseModelItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return AlbumViewHolder(AlbumLayoutItemBinding.inflate(layoutInflater, parent, false))
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        holder.bind(albumList[position])
    }

    override fun getItemCount(): Int {
        return albumList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(it: List<AlbumResponseModelItem>?) {
        this.albumList = it?.toList() ?: emptyList()
        notifyDataSetChanged()
    }
}