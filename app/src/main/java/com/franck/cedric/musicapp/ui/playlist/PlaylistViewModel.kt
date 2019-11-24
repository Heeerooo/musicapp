package com.franck.cedric.musicapp.ui.playlist

import com.franck.cedric.musicapp.domain.Playlist
import com.franck.cedric.musicapp.domain.Track
import com.franck.cedric.musicapp.io.deezer.DeezerService
import com.franck.cedric.musicapp.ui.EventViewModel
import com.franck.cedric.musicapp.ui.track.TrackMapper

class PlaylistViewModel(
    private val deezerService: DeezerService = DeezerService(),
    private val trackMapper: TrackMapper = TrackMapper(),
    private val playlistMapper: PlaylistMapper = PlaylistMapper(),
    private val playlistId: Int
) : EventViewModel<PlaylistViewModel.Event>() {

    sealed class Event : EventViewModel.Event() {
        class ShowTracks(val tracks: List<Track>): Event()
        class ShowPlaylistInfo(val playlist:Playlist): Event()

        class Error(val message: String?) : Event()
    }

    fun start() {
        deezerService.lifecycleOwner = lifecycleOwner
        deezerService.getPlaylistTracks(playlistId, { playlistTracks ->
            val tracks = playlistTracks.data.map { trackMapper.map(it) }
            dispatch(Event.ShowTracks(tracks))
        }, {
            dispatch(Event.Error(it.message))
        })
        deezerService.getPlaylist(playlistId, {
            val playlist = playlistMapper.map(it)
            dispatch(Event.ShowPlaylistInfo(playlist))
        }, {
            dispatch(Event.Error(it.message))
        })
    }

    fun onTrackClicked(track: Track) {

    }
}