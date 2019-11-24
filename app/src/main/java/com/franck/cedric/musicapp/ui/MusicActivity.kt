package com.franck.cedric.musicapp.ui

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.franck.cedric.musicapp.R
import com.franck.cedric.musicapp.domain.Playlist
import com.franck.cedric.musicapp.ui.utils.viewModel
import jp.wasabeef.recyclerview.adapters.AlphaInAnimationAdapter
import jp.wasabeef.recyclerview.animators.SlideInDownAnimator
import kotlinx.android.synthetic.main.activity_music.*
import android.content.DialogInterface




class MusicActivity : AppCompatActivity() {

    private lateinit var playlistAdapter: PlaylistAdapter

    private lateinit var viewModel: MusicViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_music)
        setUpRecycler()
        viewModel = viewModel()
        viewModel.observe(this, Observer { event ->
            when (event) {
                is MusicViewModel.Event.ShowPlaylists -> showPlaylists(event.playlists)
                is MusicViewModel.Event.Error -> errorDialog(event.message)
            }
        })
        viewModel.start()
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
        playlistAdapter.setPlaylists(playlists)
    }

    private fun setUpRecycler() {
        playlistAdapter = PlaylistAdapter(mutableListOf())
        playlistAdapter.setOnItemClick { viewModel.playlistClicked(it) }
        playlists_recycle.layoutManager = GridLayoutManager(this, 3)
        playlists_recycle.adapter = playlistAdapter
        playlists_recycle.itemAnimator = SlideInDownAnimator()
    }
}