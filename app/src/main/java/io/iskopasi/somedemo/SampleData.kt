package io.iskopasi.somedemo

data class SampleDataHolder (
    val imageRes: Int,
    val name: String,
    val description: String,
    val link: String,
    val videoLinks: List<String>, )

val unknownSampleData = SampleDataHolder(
    imageRes = 0,
    name = "Unknown",
    description = "Unknown",
    link = "Unknown",
    videoLinks = listOf(
        "",
    )
)

private val media3DataHolder = SampleDataHolder(
    imageRes = 0,
    name = "Media3Player",
    description = "Media3 api implementation",
    link = "https://github.com/cora32/Media3Player",
    videoLinks = listOf(
        "",
    )
)

private val shadersDataHolder = SampleDataHolder(
    imageRes = 0,
    name = "ShaderToy",
    description = "AGSL for static image + GLSL with camera feed (Camera2 api)",
    link = "https://github.com/cora32/Shaders",
    videoLinks = listOf(
        "",
    )
)

private val motionDetectorDataHolder = SampleDataHolder(
    imageRes = 0,
    name = "MotionDetector",
    description = "Motion detector on CameraX + NDK",
    link = "https://github.com/cora32/MotionDetector",
    videoLinks = listOf(
        "",
    )
)

private val hexDataHolder = SampleDataHolder(
    imageRes = 0,
    name = "Hex",
    description = "Desktop string encoder/decoder on Flutter",
    link = "https://github.com/cora32/hex",
    videoLinks = listOf(
        "",
    )
)

private val gitObserverClientDataHolder = SampleDataHolder(
    imageRes = 0,
    name = "GitObserverClient",
    description = "Github viewer on Jetpack Compose",
    link = "https://github.com/cora32/GitObserverClient",
    videoLinks = listOf(
        "",
    )
)

private val cryptoDataHolder = SampleDataHolder(
    imageRes = 0,
    name = "Crypto",
    description = "Crypto trading game with Binance feed",
    link = "",
    videoLinks = listOf(
        "",
    )
)

private val avlixAvDataHolder = SampleDataHolder(
    imageRes = 0,
    name = "Avlix Antivirus",
    description = "Antivirus for Android with password-leaks check screen",
    link = "",
    videoLinks = listOf(
        "",
    )
)

val sampleDataMap = mapOf(
    "https://github.com/cora32/Media3Player" to media3DataHolder,
    "https://github.com/cora32/Shaders" to shadersDataHolder,
    "https://github.com/cora32/MotionDetector" to motionDetectorDataHolder,
    "https://github.com/cora32/hex" to hexDataHolder,
    "https://github.com/cora32/GitObserverClient" to gitObserverClientDataHolder,
    "https://github.com/cora32/Crypto" to cryptoDataHolder,
    "https://github.com/cora32/AvlixAntivirus" to avlixAvDataHolder,
)