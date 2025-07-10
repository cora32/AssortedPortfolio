@file:kotlin.OptIn(ExperimentalMaterial3ExpressiveApi::class)

package io.iskopasi.somedemo.ui

import androidx.annotation.OptIn
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.LinearWavyProgressIndicator
import androidx.compose.material3.LocalRippleConfiguration
import androidx.compose.material3.RippleConfiguration
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.ui.compose.PlayerSurface
import androidx.media3.ui.compose.SURFACE_TYPE_SURFACE_VIEW
import androidx.media3.ui.compose.state.rememberPresentationState
import io.iskopasi.somedemo.ui.theme.grad1


@OptIn(UnstableApi::class)
@Composable
fun VideoBox(
    modifier: Modifier = Modifier,
    player: Player,
    onClick: (() -> Unit)? = null
) {
    DisposableEffect(Unit) {
        onDispose {
            player.release()
        }
    }

    val presentationState = rememberPresentationState(player)

    CompositionLocalProvider(
        LocalRippleConfiguration provides
                RippleConfiguration(
                    rippleAlpha = RippleAlpha(
                        pressedAlpha = 0.2f,
                        focusedAlpha = 0.4f,
                        draggedAlpha = 0.4f,
                        hoveredAlpha = 0.4f
                    ),
                    color = Color.White
                )
    ) {
        Box(
            modifier = modifier then Modifier.clickable(enabled = onClick != null) {
                onClick?.invoke()
            }, contentAlignment = Alignment.Center
        ) {
            PlayerSurface(
                player = player,
                surfaceType = SURFACE_TYPE_SURFACE_VIEW,
                modifier = Modifier
            )

            if (presentationState.coverSurface) {
                // Cover the surface that is being prepared with a shutter
                Box(Modifier.background(Color.Black), contentAlignment = Alignment.Center) {
                    LinearWavyProgressIndicator(
                        color = grad1,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(32.dp)
                            .padding(horizontal = 32.dp),
                        trackColor = Color.Transparent,
                    )
                }
            }
        }
    }
}