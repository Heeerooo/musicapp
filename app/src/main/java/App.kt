import com.franck.cedric.musicapp.domain.DurationFormatter
import com.franck.cedric.musicapp.io.deezer.DeezerService
import com.franck.cedric.musicapp.io.deezer.deezerRxApi
import com.franck.cedric.musicapp.ui.playlist.PlaylistMapper
import com.franck.cedric.musicapp.ui.track.TrackMapper


val appDeezerService = DeezerService(deezerRxApi)

val appTrackMapper = TrackMapper()

val appPlaylistMapper = PlaylistMapper()

val appDurationFormatter = DurationFormatter()