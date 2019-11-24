package com.franck.cedric.musicapp.ui

import com.franck.cedric.musicapp.domain.Playlist
import com.franck.cedric.musicapp.domain.io.deezer.DeezerService

class MusicViewModel(
    private val deezerService: DeezerService  = DeezerService(),
    private val playlistsMapper: PlaylistMapper = PlaylistMapper()
) : EventViewModel<MusicViewModel.Event>() {

    sealed class Event : EventViewModel.Event() {
        class ShowPlaylists(val playlists: List<Playlist>) : Event()
        class Error(val message: String?) : Event()
    }

    fun start() {

        deezerService.getUserPlaylists(5, { userPlaylistDeezer ->
            val playlists = userPlaylistDeezer.data.map { playlistsMapper.map(it) }
            dispatch(Event.ShowPlaylists(playlists))
        }, {
            dispatch(Event.Error(it.message))
        })
    }

    fun playlistClicked(it: Playlist) {

    }

}