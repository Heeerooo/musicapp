package com.franck.cedric.musicapp.domain


class DurationFormatter {
    fun formatDuration(durationSeconds: Long): String {
        val absSeconds = Math.abs(durationSeconds)
        val positive = String.format(
            "%d:%02d:%02d",
            absSeconds / 3600,
            (absSeconds % 3600) / 60,
            absSeconds % 60
        )
        return if (absSeconds < 0) "-$positive" else positive
    }
}