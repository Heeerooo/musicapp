package com.franck.cedric.musicapp.ui.playlist

import com.franck.cedric.musicapp.domain.Playlist
import com.franck.cedric.musicapp.io.deezer.PlaylistInfo

class PlaylistMapper {

    fun map(deezerPlaylist: PlaylistInfo) = with(deezerPlaylist) {
        Playlist(id, title, cover)
    }
}