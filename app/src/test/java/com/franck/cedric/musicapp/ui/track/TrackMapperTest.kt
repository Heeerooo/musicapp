package com.franck.cedric.musicapp.ui.track

import com.franck.cedric.musicapp.io.deezer.Album
import com.franck.cedric.musicapp.io.deezer.Artist
import com.franck.cedric.musicapp.io.deezer.Track as DeezerTrack
import org.junit.Assert.*
import org.junit.Test

/**
 * Created by Cédric Franck on 24/11/2019.
 */
class TrackMapperTest {

    private val trackMapper = TrackMapper()

    @Test
    fun testMapTrack() {
        val deezerTrack = DeezerTrack(5, "title", 200, Artist("name"), Album("cover", "title"))
        val track = trackMapper.map(deezerTrack)
        assertEquals(track.albumName , "title")
        assertEquals(track.coverUrl, "cover")
        assertEquals(track.duration , 200)
        assertEquals(track.name, "title")
    }
}