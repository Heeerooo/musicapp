package com.franck.cedric.musicapp.domain.io.deezer

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path


interface DeezerApi {
    companion object {
        const val BASE_PATH = "https://api.deezer.com"
    }

    @GET("user/{userId}/playlists")
    fun userPlaylists(@Path("userId") user: Int): Single<UserPlaylists>

    @GET("playlist/{playlistId}")
    fun playlist(@Path("playlistId") playlist: Int): Single<PlaylistInfo>

    @GET("playlist/{playlistId}/tracks")
    fun playlistTracks(@Path("playlistId") playlist: Int): Single<PlaylistTracks>
}

