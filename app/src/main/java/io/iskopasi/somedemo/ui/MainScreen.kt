package io.iskopasi.somedemo.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.LinearWavyProgressIndicator
import androidx.compose.material3.LocalRippleConfiguration
import androidx.compose.material3.RippleConfiguration
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import io.iskopasi.somedemo.R
import io.iskopasi.somedemo.SampleDataHolder
import io.iskopasi.somedemo.ui.theme.Typography
import io.iskopasi.somedemo.ui.theme.colorBg
import io.iskopasi.somedemo.ui.theme.colorGray2
import io.iskopasi.somedemo.ui.theme.grad1
import io.iskopasi.somedemo.viewmodel.LoadingState
import io.iskopasi.somedemo.viewmodel.MainModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    model: MainModel = koinViewModel()
) {
    val data by model.data.collectAsStateWithLifecycle()
    val state by model.state.collectAsStateWithLifecycle()

    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = colorBg,
        contentColor = Color.White,
        topBar = {
            TopBar(
                title = stringResource(R.string.main),
            )
        },
    ) { innerPadding ->
        Box(modifier = Modifier.padding(top = innerPadding.calculateTopPadding())) {
            when (state) {
                LoadingState.Idle -> PressButtonBox(onFetchData = model::getData)
                LoadingState.Loading -> LoadingBox(modifier = Modifier.fillMaxSize())
                LoadingState.Success -> Content(
                    data = data,
                    onClick = model::onItemSelected
                )

                LoadingState.Error -> SomeErrorBox()
            }
        }
    }
}

@Composable
fun SomeErrorBox(modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            stringResource(R.string.error_occurred),
            style = Typography.bodySmall,
            color = Color.Red
        )
    }
}

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun LoadingBox(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        LinearWavyProgressIndicator(
            color = grad1,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp),
            trackColor = colorGray2,
        )
    }
}

@Composable
private fun PressButtonBox(modifier: Modifier = Modifier, onFetchData: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 24.dp, end = 24.dp, bottom = 64.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = stringResource(R.string.no_data),
                style = Typography.bodySmall,
                textAlign = TextAlign.Center
            )
        }
        SDButton(
            text = stringResource(R.string.get_data),
            onClick = onFetchData,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun Content(modifier: Modifier = Modifier, data: List<SampleDataHolder>, onClick: (Int) -> Unit) {
    val state = rememberLazyListState()

    LazyColumn(
        state = state,
        contentPadding = PaddingValues(start = 24.dp, end = 24.dp, bottom = 64.dp),
        modifier = Modifier
            .fillMaxSize()
                then modifier
    ) {
        items(items = data) { item ->
            ListItem(item = item, onClick = onClick)
        }
    }
}

@Composable
private fun ListItem(
    modifier: Modifier = Modifier,
    item: SampleDataHolder,
    onClick: (Int) -> Unit
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
        Card(
            elevation = CardDefaults.cardElevation(
                defaultElevation = 4.dp
            ),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            modifier = Modifier
                .clickable {
                    onClick(item.uid)
                }
                .padding(vertical = 16.dp)) {
            Column {
                Image(
                    painterResource(id = item.imageRes),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .height(100.dp)
                        .fillMaxWidth()
                )
                HorizontalDivider(thickness = 1.dp, color = Color.Black)
                DataBlock(title = item.name, description = item.description, link = item.link)
            }
        }
    }
}

@Composable
private fun DataBlock(title: String, description: String, link: String) {
    val uriHandler = LocalUriHandler.current

    Column(modifier = Modifier.padding(vertical = 16.dp, horizontal = 12.dp)) {
        Text(
            text = title,
            style = Typography.bodyLarge
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = description,
            style = Typography.bodyMedium
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = link,
            style = Typography.bodySmall,
            textDecoration = TextDecoration.Underline,
            modifier = Modifier.clickable {
                uriHandler.openUri(link)
            }
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xffffffff)
@Composable
fun ListItemPreview(modifier: Modifier = Modifier) {
    val item = SampleDataHolder(
        imageRes = R.drawable.shaders,
        name = "Some name",
        description = "Some description",
        link = "https://google.com",
        videoLinks = emptyList()
    )

    ListItem(item = item, onClick = {})
}

@Preview(showBackground = true, backgroundColor = 0xffffffff)
@Composable
fun ContentPreview(modifier: Modifier = Modifier) {
    val list = listOf<SampleDataHolder>(
        SampleDataHolder(
            imageRes = R.drawable.shaders,
            name = "Some name",
            description = "Some description",
            link = "https://google.com",
            videoLinks = emptyList()
        ),

        SampleDataHolder(
            imageRes = R.drawable.shaders,
            name = "Some name",
            description = "Some description",
            link = "https://google.com",
            videoLinks = emptyList()
        ),

        SampleDataHolder(
            imageRes = R.drawable.shaders,
            name = "Some name",
            description = "Some description",
            link = "https://google.com",
            videoLinks = emptyList()
        ),

        SampleDataHolder(
            imageRes = R.drawable.shaders,
            name = "Some name",
            description = "Some description",
            link = "https://google.com",
            videoLinks = emptyList()
        )
    )

    Content(data = list, onClick = {})
}