package com.franck.cedric.musicapp.io.deezer

import androidx.lifecycle.LifecycleOwner
import com.trello.rxlifecycle3.android.lifecycle.kotlin.bindToLifecycle
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class DeezerService(private val deezerApi: DeezerApi) {

    private val compositeDisposable = CompositeDisposable()

    var lifecycleOwner: LifecycleOwner? = null

    fun getUserPlaylists(
        userId: Int, success: (UserPlaylists) -> Unit,
        error: (Throwable) -> Unit
    ) = compositeDisposable.add(
        deezerApi.userPlaylists(userId)
            .apply { lifecycleOwner?.let { bindToLifecycle(it) } }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({success(it)},{error(it)})
    )

    fun getPlaylist(
        playlistId: Int, success: (PlaylistInfo) -> Unit,
        error: (Throwable) -> Unit
    ) = compositeDisposable.add(
        deezerApi.playlist(playlistId)
            .apply { lifecycleOwner?.let { bindToLifecycle(it) } }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({success(it)},{error(it)})
    )

    fun getPlaylistTracks(
        playlistId: Int, success: (PlaylistTracks) -> Unit,
        error: (Throwable) -> Unit
    ) = compositeDisposable.add(
        deezerApi.playlistTracks(playlistId)
            .apply { lifecycleOwner?.let { bindToLifecycle(it) } }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({success(it)},{error(it)})
    )
}