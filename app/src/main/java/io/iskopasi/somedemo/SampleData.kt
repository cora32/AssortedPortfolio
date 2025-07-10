package io.iskopasi.somedemo

data class SampleDataHolder(
    val uid: Int = 0,
    val imageRes: Int,
    val name: String,
    val description: String,
    val link: String,
    val videoLinks: List<String>,
)

val unknownSampleData = SampleDataHolder(
    imageRes = R.drawable.shaders,
    name = "Unknown",
    description = "Unknown",
    link = "Unknown",
    videoLinks = listOf(
        "",
    )
)

private val media3DataHolder = SampleDataHolder(
    imageRes = R.drawable.media3,
    name = "Media3Player",
    description = "Media3 api implementation",
    link = "https://github.com/cora32/Media3Player",
    videoLinks = listOf(
        "https://github.com/cora32/AssortedPortfolio/raw/refs/heads/master/app/src/main/res/raw/playerdemo1.mp4",
        "https://github.com/cora32/AssortedPortfolio/raw/refs/heads/master/app/src/main/res/raw/playerdemo2.mp4",
    )
)

private val shadersDataHolder = SampleDataHolder(
    imageRes = R.drawable.shaders,
    name = "ShaderToy",
    description = "AGSL for static image + GLSL with camera feed (Camera2 api)",
    link = "https://github.com/cora32/Shaders",
    videoLinks = listOf(
        "https://github.com/cora32/AssortedPortfolio/raw/refs/heads/master/app/src/main/res/raw/sh1.mp4",
        "https://github.com/cora32/AssortedPortfolio/raw/refs/heads/master/app/src/main/res/raw/sh2.mp4",
    )
)

private val motionDetectorDataHolder = SampleDataHolder(
    imageRes = R.drawable.motion,
    name = "MotionDetector",
    description = "Motion detector on CameraX + NDK",
    link = "https://github.com/cora32/MotionDetector",
    videoLinks = listOf(
        "https://github.com/cora32/AssortedPortfolio/raw/refs/heads/master/app/src/main/res/raw/m1_opt.mp4",
        "https://github.com/cora32/AssortedPortfolio/raw/refs/heads/master/app/src/main/res/raw/m2_opt.mp4",
    )
)

private val hexDataHolder = SampleDataHolder(
    imageRes = R.drawable.hex,
    name = "Hex",
    description = "Desktop string encoder/decoder on Flutter",
    link = "https://github.com/cora32/hex",
    videoLinks = listOf(
        "https://github.com/cora32/hex/raw/master/hex2.jpg",
    )
)

private val gitObserverClientDataHolder = SampleDataHolder(
    imageRes = R.drawable.git,
    name = "GitObserverClient",
    description = "Github viewer on Jetpack Compose",
    link = "https://github.com/cora32/GitObserverClient",
    videoLinks = listOf(
        "https://github.com/cora32/AssortedPortfolio/raw/refs/heads/master/app/src/main/res/raw/git.mp4",
    )
)

private val cryptoDataHolder = SampleDataHolder(
    imageRes = R.drawable.ccc,
    name = "Crypto",
    description = "Crypto trading game with Binance feed",
    link = "",
    videoLinks = listOf(
        "https://github.com/cora32/AssortedPortfolio/raw/refs/heads/master/app/src/main/res/raw/ccc_opt.mp4",
        "https://github.com/cora32/AssortedPortfolio/raw/refs/heads/master/app/src/main/res/raw/ccc_opt_p2_v2.mp4",
        "https://github.com/cora32/AssortedPortfolio/raw/refs/heads/master/app/src/main/res/raw/ccc_opt_1_v2.mp4",
        "https://github.com/cora32/AssortedPortfolio/raw/refs/heads/master/app/src/main/res/raw/ccc_opt_2_v2.mp4",
    )
)

private val avlixAvDataHolder = SampleDataHolder(
    imageRes = R.drawable.avlix,
    name = "Avlix Antivirus",
    description = "Antivirus for Android with password-leaks check screen",
    link = "",
    videoLinks = listOf(
        "https://github.com/cora32/AssortedPortfolio/raw/refs/heads/master/app/src/main/res/raw/av_con_opt.mp4",
        "https://github.com/cora32/AssortedPortfolio/raw/refs/heads/master/app/src/main/res/raw/av3_opt.mp4",
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