package com.franck.cedric.musicapp.domain

import com.franck.cedric.musicapp.io.deezer.Artist
import java.time.Duration

data class Playlist(val id: Int,
                    val name: String,
                    val coverUrl: String)

data class Track(val name: String,
                 val albumName: String?,
                 val artistName: String,
                 val duration: Long,
                 val coverUrl: String?)
