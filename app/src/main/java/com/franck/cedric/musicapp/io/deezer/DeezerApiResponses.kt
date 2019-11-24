package com.franck.cedric.musicapp.io.deezer

import com.google.gson.annotations.SerializedName

data class UserPlaylists(val data: List<PlaylistInfo>)

data class PlaylistInfo(
    val id: Int,
    val title: String,
    val duration: Int,
    @SerializedName("picture_medium")
    val cover: String
)

data class PlaylistTracks(val data: List<Track>)

data class Track(
    val id: Int,
    val title: String,
    val duration: Int,
    val artist: Artist,
    val album: Album
)

data class Album(@SerializedName("cover_medium") val cover: String)

data class Artist(val name: String)
