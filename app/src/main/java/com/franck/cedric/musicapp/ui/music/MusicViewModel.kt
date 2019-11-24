package com.franck.cedric.musicapp.ui.music

import com.franck.cedric.musicapp.domain.Playlist
import com.franck.cedric.musicapp.io.deezer.DeezerService
import com.franck.cedric.musicapp.ui.EventViewModel
import com.franck.cedric.musicapp.ui.playlist.PlaylistMapper

class MusicViewModel(
    private val deezerService: DeezerService  = DeezerService(),
    private val playlistsMapper: PlaylistMapper = PlaylistMapper()
) : EventViewModel<MusicViewModel.Event>() {

    sealed class Event : EventViewModel.Event() {
        class ShowPlaylists(val playlists: List<Playlist>) : Event()
        class ShowPlaylist(val id: Int) : Event()
        class Error(val message: String?) : Event()
    }

    fun start() {
        deezerService.lifecycleOwner = lifecycleOwner
        deezerService.getUserPlaylists(5, { userPlaylistDeezer ->
            val playlists = userPlaylistDeezer.data.map { playlistsMapper.map(it) }
            dispatch(
                Event.ShowPlaylists(
                    playlists
                )
            )
        }, {
            dispatch(Event.Error(it.message))
        })
    }

    fun playlistClicked(playlist: Playlist) {
        dispatch(Event.ShowPlaylist(playlist.id))
    }

}