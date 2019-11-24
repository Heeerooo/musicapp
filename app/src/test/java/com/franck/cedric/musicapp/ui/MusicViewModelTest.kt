package com.franck.cedric.musicapp.ui

import androidx.arch.core.executor.ArchTaskExecutor
import androidx.arch.core.executor.TaskExecutor
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.lifecycle.Observer
import com.franck.cedric.musicapp.domain.io.deezer.DeezerService
import com.franck.cedric.musicapp.domain.io.deezer.UserPlaylists
import io.mockk.every
import io.mockk.mockk
import junit.framework.Assert.assertTrue
import org.junit.Before
import org.junit.Test

@Suppress("UNCHECKED_CAST")
class MusicViewModelTest {

    protected val events = mutableListOf<MusicViewModel.Event>()

    protected val lastEvent
        get() = events.last()

    private lateinit var musicViewModel: MusicViewModel

    private lateinit var deezerService: DeezerService

    private lateinit var playlistMapper: PlaylistMapper

    @Before
    fun init() {
        deezerService = mockk()
        playlistMapper = mockk()
        musicViewModel = MusicViewModel(deezerService, playlistMapper)

        val owner = mockk<LifecycleOwner>(relaxed = true) {
            every { lifecycle } returns LifecycleRegistry(this).also {
                it.handleLifecycleEvent(Lifecycle.Event.ON_RESUME)
            }
        }

        ArchTaskExecutor.getInstance().setDelegate(object : TaskExecutor() {
            override fun executeOnDiskIO(runnable: Runnable) {
                runnable.run()
            }

            override fun postToMainThread(runnable: Runnable) {
                runnable.run()
            }

            override fun isMainThread(): Boolean = true
        })

        musicViewModel.observe(owner, Observer {
            events += it
        })
        events.clear()
    }

    @Test
    fun testMusicViewModelStart() {

        every { deezerService.getUserPlaylists(any(),any(), any()) } answers {
            (args[1] as (UserPlaylists) -> Unit)(mockk {
                every { data } returns listOf(mockk(), mockk())
            })
            true
        }
        every { playlistMapper.map(any()) } returns mockk()
        musicViewModel.start()
        assertTrue(lastEvent is MusicViewModel.Event.ShowPlaylists)
    }

    @Test
    fun testMusicViewModelStartOnError() {

        every { deezerService.getUserPlaylists(any(),any(), any()) } answers {
            (args[2] as (Throwable) -> Unit)(mockk {
                every { message } returns "error"
            })
            true
        }
        musicViewModel.start()
        assertTrue(lastEvent is MusicViewModel.Event.Error)
    }
}