package io.iskopasi.somedemo.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.LocalRippleConfiguration
import androidx.compose.material3.RippleConfiguration
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import io.iskopasi.somedemo.suppressed
import io.iskopasi.somedemo.ui.theme.Typography
import io.iskopasi.somedemo.ui.theme.colorGray2
import io.iskopasi.somedemo.ui.theme.colorText2
import io.iskopasi.somedemo.ui.theme.grad1
import io.iskopasi.somedemo.ui.theme.grad2

@Composable
fun SDButton(
    modifier: Modifier = Modifier,
    text: String,
    enabled: Boolean = true,
    suppressionDelay: Long = 500L,
    onClick: () -> Unit
) {
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
        TextButton(
            enabled = enabled,
            onClick = suppressed(
                key = text,
                delay = suppressionDelay
            ) { onClick() },
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent,
                contentColor = Color.White,
                disabledContainerColor = colorGray2,
                disabledContentColor = colorText2
            ),
            modifier = modifier then Modifier
                .fillMaxWidth()
                .height(54.dp)
                .clip(shape = RoundedCornerShape(16.dp))
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(grad1, grad2)
                    )
                )
        ) {
            Text(
                text,
                style = Typography.bodyLarge.copy(
                    color = Color.White
                ),
            )
        }
    }
}