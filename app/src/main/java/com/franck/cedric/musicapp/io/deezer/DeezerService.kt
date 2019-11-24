package com.franck.cedric.musicapp.io.deezer

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class DeezerService(private val deezerApi: DeezerApi = deezerRxApi) {

    private val compositeDisposable = CompositeDisposable()

    fun getUserPlaylists(
        userId: Int, success: (UserPlaylists) -> Unit,
        error: (Throwable) -> Unit
    ) = compositeDisposable.add(
        deezerApi.userPlaylists(userId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({success(it)},{error(it)})
    )

    fun getPlaylist(
        playlistId: Int, success: (PlaylistInfo) -> Unit,
        error: (Throwable) -> Unit
    ) = compositeDisposable.add(
        deezerApi.playlist(playlistId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({success(it)},{error(it)})
    )

    fun getPlaylistTracks(
        playlistId: Int, success: (PlaylistTracks) -> Unit,
        error: (Throwable) -> Unit
    ) = compositeDisposable.add(
        deezerApi.playlistTracks(playlistId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({success(it)},{error(it)})
    )
}