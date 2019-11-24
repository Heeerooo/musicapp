package com.franck.cedric.musicapp.domain

import org.junit.Assert.*
import org.junit.Test


class DurationFormatterTest {

    private val durationFormatter = DurationFormatter()

    @Test
    fun testFormatDuration() {
        assertEquals("0:01:00", durationFormatter.formatDuration(60))
        assertEquals("1:01:00", durationFormatter.formatDuration(60 * 60 + 60))
        assertEquals("0:00:54", durationFormatter.formatDuration(54))
    }
}