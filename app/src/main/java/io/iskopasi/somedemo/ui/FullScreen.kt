@file:kotlin.OptIn(ExperimentalMaterial3ExpressiveApi::class)

package io.iskopasi.somedemo.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import io.iskopasi.somedemo.viewmodel.FullScreenModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun FullScreen(
    modifier: Modifier = Modifier,
    model: FullScreenModel = koinViewModel(),
    link: String
) {
    val player by model.player.collectAsStateWithLifecycle()
    val item by model.item.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        model.prepareData(link = link)
    }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = Color.White,
        contentColor = Color.White,
        topBar = {
            TopBar(
                onBack = model::onBack,
                title = item.name,
            )
        },
    ) { innerPadding ->
        player?.let {
            VideoBox(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                player = it
            )
        }
    }
}