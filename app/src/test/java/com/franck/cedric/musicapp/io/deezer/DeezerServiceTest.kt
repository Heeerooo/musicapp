package com.franck.cedric.musicapp.io.deezer

import com.franck.cedric.musicapp.domain.io.deezer.PlaylistInfo
import com.franck.cedric.musicapp.domain.io.deezer.PlaylistTracks
import com.franck.cedric.musicapp.domain.io.deezer.UserPlaylists
import com.franck.cedric.musicapp.domain.io.deezer.deezerRxService
import io.reactivex.observers.TestObserver
import junit.framework.Assert.*
import org.junit.Test

class DeezerServiceTest {

    @Test
    fun testGetUserPlaylist() {
        val testObserver = TestObserver<UserPlaylists>()
        deezerRxService.userPlaylists(1)
            .subscribe(testObserver)

        testObserver.assertComplete()
        testObserver.assertNoErrors()
        with(testObserver.values()[0]) {
            assertNotNull(data)
            assertNotNull(data[0].title)
            assertNotNull(data[0].duration)
            assertNotNull(data[0].cover)
            assertNotNull(data[0].id)
        }

    }

    @Test
    fun testGetPlaylist() {
        val testObserver = TestObserver<PlaylistInfo>()
        deezerRxService.playlist(5)
            .subscribe(testObserver)

        testObserver.assertComplete()
        testObserver.assertNoErrors()
        with(testObserver.values()[0]) {
            assertNotNull(id)
            assertNotNull(title)
            assertNotNull(duration)
            assertNotNull(cover)
            assertNotNull(id)
        }

    }

    @Test
    fun testGetPlaylistTracks() {
        val testObserver = TestObserver<PlaylistTracks>()
        deezerRxService.playlistTracks(5)
            .subscribe(testObserver)

        testObserver.assertComplete()
        testObserver.assertNoErrors()
        with(testObserver.values()[0]) {
            assertNotNull(data)
            assertNotNull(data[0].title)
            assertNotNull(data[0].duration)
            assertNotNull(data[0].artist)
            assertNotNull(data[0].artist.name)
            assertNotNull(data[0].album)
            assertNotNull(data[0].album.cover)
        }

    }
}