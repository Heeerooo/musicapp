package com.franck.cedric.musicapp.domain

import java.time.Duration

data class Playlist(val name: String,
                    val cover: String,
                    val list: List<Track>)

data class Track(val name: String,
                 val albumName: String,
                 val duration: Duration,
                 val cover: String)
