package io.iskopasi.somedemo.ui

import androidx.annotation.OptIn
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.ui.compose.PlayerSurface
import androidx.media3.ui.compose.SURFACE_TYPE_SURFACE_VIEW
import androidx.media3.ui.compose.state.rememberPresentationState
import io.iskopasi.somedemo.SampleDataHolder
import io.iskopasi.somedemo.ui.theme.Typography
import io.iskopasi.somedemo.ui.theme.colorBg
import io.iskopasi.somedemo.viewmodel.DetailsModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun DetailsScreen(
    modifier: Modifier = Modifier,
    model: DetailsModel = koinViewModel(),
    id: Int
) {
    val players by model.players.collectAsStateWithLifecycle()
    val item by model.item.collectAsStateWithLifecycle()

    LaunchedEffect(id) {
        model.prepareData(id = id)
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
        DetailsContent(
            modifier = Modifier.padding(top = innerPadding.calculateTopPadding()),
            item = item,
            players = players
        )
    }
}

@Composable
private fun DetailsContent(
    modifier: Modifier = Modifier,
    item: SampleDataHolder,
    players: List<Player>
) {
    val imageRes = item.imageRes
    val title = item.name
    val description = item.description
    val link = item.link
    val state = rememberScrollState()

    Column(
        modifier = Modifier
            .background(colorBg)
            .fillMaxSize()
            .verticalScroll(state = state) then modifier
    ) {
        Header(imageRes = imageRes, title = title, description = description, link = link)
        HorizontalDivider(thickness = 1.dp, color = Color.Black)
        FlowRow(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            maxItemsInEachRow = 3
        ) {
            players.forEach { player ->
                VideoItem(player = player)
            }
        }
    }
}

@Composable
fun Header(
    modifier: Modifier = Modifier,
    imageRes: Int,
    title: String,
    description: String,
    link: String,
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
    ) {
        Image(
            painterResource(id = imageRes),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth(),
        )

        val uriHandler = LocalUriHandler.current

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.7f))
                .padding(vertical = 16.dp, horizontal = 24.dp),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(
                text = title,
                style = Typography.bodyLarge.copy(color = Color.White)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = description,
                style = Typography.bodyMedium.copy(color = Color.White)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = link,
                style = Typography.bodySmall.copy(color = Color.White),
                textDecoration = TextDecoration.Underline,
                modifier = Modifier.clickable {
                    uriHandler.openUri(link)
                }
            )
        }
    }
}

@OptIn(UnstableApi::class)
@Composable
private fun VideoItem(modifier: Modifier = Modifier, player: Player) {
    DisposableEffect(Unit) {
        onDispose {
            player.release()
        }
    }

    val presentationState = rememberPresentationState(player)

    Box(modifier) {
        PlayerSurface(
            player = player,
            surfaceType = SURFACE_TYPE_SURFACE_VIEW,
            modifier = Modifier
                .height(200.dp)
                .width(150.dp),
        )

        if (presentationState.coverSurface) {
            // Cover the surface that is being prepared with a shutter
            Box(Modifier.background(Color.Black))
        }
    }
}