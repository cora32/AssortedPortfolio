package io.iskopasi.somedemo.managers

import android.app.Application
import androidx.core.net.toUri
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer

class PlayerManager(
    val application: Application
) {
    fun getPlayer(videoUrl: String): Player {
        return ExoPlayer.Builder(application).build().apply {
            // 2. Create a MediaItem from the URL
            val mediaItem = MediaItem.fromUri(videoUrl.toUri())
            setMediaItem(mediaItem)

            // 3. Prepare the player
            prepare()

            // 4. Set to play when ready (optional, start playback immediately)
            playWhenReady = true
        }
    }
}