package com.franck.cedric.musicapp.ui

import com.franck.cedric.musicapp.domain.Playlist
import com.franck.cedric.musicapp.domain.io.deezer.PlaylistInfo

class PlaylistMapper {

    fun map(deezerPlaylist: PlaylistInfo) = with(deezerPlaylist) {
        Playlist(id, title, cover)
    }
}