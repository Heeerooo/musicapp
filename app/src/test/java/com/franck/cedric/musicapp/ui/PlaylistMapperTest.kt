package com.franck.cedric.musicapp.ui

import com.franck.cedric.musicapp.io.deezer.PlaylistInfo
import org.junit.Assert.*
import org.junit.Test
import java.time.Duration


class PlaylistMapperTest {

    private val playlistMapper = PlaylistMapper()

    @Test
    fun testMapPlaylist() {
        val deezerPlaylist = PlaylistInfo(5, "title", Duration.ZERO.toMinutes().toInt(), "coverUrl")
        val playlistMapped = playlistMapper.map(deezerPlaylist)

        assertEquals("title", playlistMapped.name)
        assertEquals("coverUrl", playlistMapped.coverUrl)
        assertEquals("title", playlistMapped.name)
    }
}