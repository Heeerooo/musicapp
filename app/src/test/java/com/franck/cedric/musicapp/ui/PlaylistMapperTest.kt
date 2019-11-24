package com.franck.cedric.musicapp.ui

import com.franck.cedric.musicapp.domain.io.deezer.PlaylistInfo
import org.junit.Assert.*
import org.junit.Test
import java.time.Duration


class PlaylistMapperTest {

    private val playlistMapper = PlaylistMapper()

    @Test
    fun testMapPlaylist() {
        val deezerPlaylist = PlaylistInfo(5, "title", Duration.ZERO.toMinutes().toInt(), "cover")
        val playlistMapped = playlistMapper.map(deezerPlaylist)

        assertEquals("title", playlistMapped.name)
        assertEquals("cover", playlistMapped.cover)
        assertEquals("title", playlistMapped.name)
    }
}