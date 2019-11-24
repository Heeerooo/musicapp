package com.franck.cedric.musicapp.ui.music

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.franck.cedric.musicapp.R
import com.franck.cedric.musicapp.domain.Playlist
import com.franck.cedric.musicapp.ui.utils.viewModel
import jp.wasabeef.recyclerview.animators.SlideInDownAnimator
import kotlinx.android.synthetic.main.activity_music.*
import com.facebook.drawee.backends.pipeline.Fresco
import com.franck.cedric.musicapp.ui.playlist.PlaylistActivity
import com.franck.cedric.musicapp.ui.playlist.PlaylistActivity.Companion.PLAYLIST_ID
import com.franck.cedric.musicapp.ui.playlist.PlaylistAdapter
import com.franck.cedric.musicapp.ui.utils.launchActivity


class MusicActivity : AppCompatActivity() {

    private lateinit var playlistAdapter: PlaylistAdapter

    private lateinit var viewModel: MusicViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_music)
        setUpRecycler()
        Fresco.initialize(this)
        viewModel = viewModel()
        viewModel.observe(this, Observer { event ->
            when (event) {
                is MusicViewModel.Event.ShowPlaylists -> showPlaylists(event.playlists)
                is MusicViewModel.Event.Error -> errorDialog(event.message)
                is MusicViewModel.Event.ShowPlaylist -> showPlaylist(event.id)
            }
        })
        viewModel.start()
    }

    private fun showPlaylist(id: Int) {
        launchActivity<PlaylistActivity> {
            putExtra(PLAYLIST_ID, id)
        }
    }

    private fun errorDialog(message: String?) {
        AlertDialog.Builder(this)
            .setTitle(getString(R.string.error))
            .setMessage(message ?: getString(R.string.unknown_error))
            .setPositiveButton(
                android.R.string.ok
            ) { dialog, which ->
                // Continue with delete operation
            }
            .show()
    }

    private fun showPlaylists(playlists: List<Playlist>) {
        playlistAdapter.addPlaylists(playlists)
    }

    private fun setUpRecycler() {
        playlistAdapter = PlaylistAdapter(mutableListOf())
        playlistAdapter.setOnItemClick { viewModel.playlistClicked(it) }
        playlists_recycle.layoutManager = GridLayoutManager(this, 3)
        playlists_recycle.adapter = playlistAdapter
        playlists_recycle.itemAnimator = SlideInDownAnimator()
    }
}