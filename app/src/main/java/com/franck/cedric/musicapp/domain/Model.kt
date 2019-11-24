package com.franck.cedric.musicapp.domain

import java.time.Duration

data class Playlist(val id: Int,
                    val name: String,
                    val cover: String)

data class Track(val name: String,
                 val albumName: String,
                 val duration: Duration,
                 val cover: String)
