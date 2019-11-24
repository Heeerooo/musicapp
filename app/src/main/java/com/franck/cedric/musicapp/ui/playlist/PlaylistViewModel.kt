package com.franck.cedric.musicapp.ui.playlist

import com.franck.cedric.musicapp.domain.Track
import com.franck.cedric.musicapp.io.deezer.DeezerService
import com.franck.cedric.musicapp.ui.EventViewModel
import com.franck.cedric.musicapp.ui.track.TrackMapper

class PlaylistViewModel(
    private val deezerService: DeezerService = DeezerService(),
    private val trackMapper: TrackMapper = TrackMapper(),
    private val playlistId: Int
) : EventViewModel<PlaylistViewModel.Event>() {

    sealed class Event : EventViewModel.Event() {
        class ShowTracks(val tracks: List<Track>): Event()
        class Error(val message: String?) : Event()
    }


    fun start() {
        deezerService.getPlaylistTracks(playlistId, { playlistTracks ->
            val tracks = playlistTracks.data.map { trackMapper.map(it) }
            dispatch(Event.ShowTracks(tracks))
        }, {
            dispatch(Event.Error(it.message))
        })
    }
}