package com.franck.cedric.musicapp.ui.playlist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.facebook.drawee.backends.pipeline.Fresco
import com.franck.cedric.musicapp.R
import com.franck.cedric.musicapp.domain.Track
import com.franck.cedric.musicapp.ui.utils.viewModel

class PlaylistActivity : AppCompatActivity() {


    private lateinit var viewModel: PlaylistViewModel

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
            }
        })
        viewModel.start()

    }

    private fun showTracks(tracks: List<Track>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun setUpRecycler() {

    }
}