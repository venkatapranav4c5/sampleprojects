package com.getusersapp.ui.albumslist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.getusersapp.R
import com.getusersapp.data.models.Album

class AlbumsListAdapter() : RecyclerView.Adapter<AlbumsListViewHolder>() {

    private lateinit var albumsList: List<Album>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumsListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return AlbumsListViewHolder(
            DataBindingUtil.inflate(
                inflater
                , R.layout.layout_album_item, parent, false
            )
        )
    }

    override fun getItemCount(): Int {
        return albumsList.size
    }

    override fun onBindViewHolder(holder: AlbumsListViewHolder, position: Int) {
        holder.layoutAlbumItemBinding.album = albumsList[position]
    }

    fun updateList(albumsList: List<Album>) {
        this.albumsList = albumsList
    }

    fun updateAlbum(album:Album) {
        if (albumsList.contains(album)) {
            val index = albumsList.indexOf(album)
            val oldAlbum = albumsList[index]
            oldAlbum.photos = album.photos
            notifyItemChanged(index)
        }
    }
}