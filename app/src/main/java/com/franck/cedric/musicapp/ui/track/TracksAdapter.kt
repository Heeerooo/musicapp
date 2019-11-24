package com.franck.cedric.musicapp.ui.track

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import appDurationFormatter
import com.franck.cedric.musicapp.R
import com.franck.cedric.musicapp.domain.DurationFormatter
import com.franck.cedric.musicapp.domain.Track
import com.franck.cedric.musicapp.ui.AnimatedAdapter
import kotlinx.android.synthetic.main.item_track.view.*


class TracksAdapter(private val durationFormatter: DurationFormatter = appDurationFormatter,
                    private val tracks: MutableList<Track>) :
    AnimatedAdapter<TracksAdapter.ViewHolder>(){

    private var onItemClick: (Track) -> Unit  = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_track, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = tracks.size

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val track = tracks[position]
        with(holder.itemView) {
            setOnClickListener { onItemClick(track) }
            title.text = track.name
            info.text = "${track.albumName} - ${track.artistName}"
            cover.setImageURI(track.coverUrl)
            duration.text = durationFormatter.formatDuration(track.duration)
        }
    }

    fun setOnItemClick(onClick: (Track) -> Unit) {
        onItemClick = onClick
    }

    fun addTracks(playlists: List<Track>) {
        val previousCount = itemCount
        this.tracks.addAll(playlists)
        notifyItemRangeInserted(previousCount, itemCount)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}