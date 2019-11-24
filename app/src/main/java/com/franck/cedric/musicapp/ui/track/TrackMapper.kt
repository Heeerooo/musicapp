package com.franck.cedric.musicapp.ui.track

import com.franck.cedric.musicapp.domain.Track
import com.franck.cedric.musicapp.io.deezer.Track as DeezerTrack


class TrackMapper() {

    fun map(deezerTrack: DeezerTrack) = with(deezerTrack) {
        Track(title, album.title, duration, album.cover)
    }
}