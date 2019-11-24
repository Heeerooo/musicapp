package com.franck.cedric.musicapp.ui.playlist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.facebook.drawee.backends.pipeline.Fresco
import com.franck.cedric.musicapp.R
import com.franck.cedric.musicapp.domain.Playlist
import com.franck.cedric.musicapp.domain.Track
import com.franck.cedric.musicapp.ui.track.TracksAdapter
import com.franck.cedric.musicapp.ui.utils.viewModel
import jp.wasabeef.recyclerview.animators.SlideInLeftAnimator
import kotlinx.android.synthetic.main.playlists_activity.*

class PlaylistActivity : AppCompatActivity() {


    private lateinit var viewModel: PlaylistViewModel

    private lateinit var tracksAdapter: TracksAdapter

     companion object {
         const val PLAYLIST_ID = "playlist_id"
     }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.playlists_activity)
        setUpRecycler()
        Fresco.initialize(this)
        viewModel = viewModel {
            PlaylistViewModel(playlistId = intent.getIntExtra(PLAYLIST_ID, 0))
        }
        viewModel.observe(this, Observer { event ->
            when (event) {
                is PlaylistViewModel.Event.ShowTracks -> showTracks(event.tracks)
                is PlaylistViewModel.Event.ShowPlaylistInfo -> showPlaylistInfo(event.playlist)
            }
        })
        viewModel.start()
    }

    private fun showPlaylistInfo(playlist: Playlist) {
        cover_playlist.setImageURI(playlist.coverUrl)
        toolbar.title = playlist.name
    }

    private fun setUpRecycler() {
        tracksAdapter = TracksAdapter(tracks = mutableListOf())
        tracksAdapter.setOnItemClick { viewModel.onTrackClicked(it) }
        tracks_recycler.layoutManager = LinearLayoutManager(this)
        tracks_recycler.itemAnimator = SlideInLeftAnimator()
        tracks_recycler.adapter = tracksAdapter
    }

    private fun showTracks(tracks: List<Track>) {
        tracksAdapter.addTracks(tracks)
    }

}