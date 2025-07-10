package io.iskopasi.somedemo.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.iskopasi.somedemo.ui.theme.ChevronLeft
import io.iskopasi.somedemo.ui.theme.colorText1

@Composable
fun TopBar(
    modifier: Modifier = Modifier,
    title: String? = null,
    onBack: (() -> Unit)? = null,
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .height(90.dp)
            .fillMaxWidth()
            .clickable {
                // Intercepting clicks so that use wont click on buttons that are under TopBar
            }
            .background(Color.White)
            .statusBarsPadding()
    ) {
        Row(
            modifier = Modifier
                .padding(vertical = 12.dp, horizontal = 24.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (onBack != null)
                IconButton(onClick = onBack, modifier = Modifier.size(30.dp)) {
                    Icon(
                        ChevronLeft,
                        contentDescription = "",
                        tint = Color.Black
                    )
                }
            Spacer(modifier = Modifier.weight(1f))
        }

        if (title != null)
            Text(
                text = title,
                fontWeight = FontWeight.W600,
                fontSize = 16.sp,
                color = colorText1
            )
    }
}