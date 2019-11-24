package com.franck.cedric.musicapp.ui.playlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.franck.cedric.musicapp.R
import com.franck.cedric.musicapp.domain.Playlist
import com.franck.cedric.musicapp.ui.AnimatedAdapter
import kotlinx.android.synthetic.main.item_playlist.view.*

class PlaylistAdapter(private val playlists: MutableList<Playlist>) :
    AnimatedAdapter<PlaylistAdapter.ViewHolder>(){

    private var onItemClick: (Playlist) -> Unit  = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_playlist, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = playlists.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val playlist = playlists[position]
        with(holder.itemView) {
            setOnClickListener { onItemClick(playlist) }
            name.text = playlist.name
            cover.setImageURI(playlist.coverUrl)
        }
    }

    fun setOnItemClick(onClick: (Playlist) -> Unit) {
        onItemClick = onClick
    }

    fun setPlaylists(playlists: List<Playlist>) {
        val previousCount = itemCount
        this.playlists.addAll(playlists)
        notifyItemRangeInserted(previousCount, itemCount)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }
}