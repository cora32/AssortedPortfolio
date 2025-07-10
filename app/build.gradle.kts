import com.android.build.gradle.internal.tasks.FinalizeBundleTask

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.jetbrains.kotlin.serialization)
    id("com.google.devtools.ksp")
}

android {
    namespace = "io.iskopasi.somedemo"
    compileSdk = 36

    defaultConfig {
        applicationId = "io.iskopasi.somedemo"
        minSdk = 28
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        setProperty("archivesBaseName", "sampledemo_${versionName}_($versionCode)")
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }


    flavorDimensions += "version"

    productFlavors {
        create("dev-sub") {
            dimension = "version"
            buildConfigField("boolean", "isDevVersion", "true")
            buildConfigField("boolean", "isSubscribed", "true")
            versionNameSuffix = "-dev-sub"
        }
        create("dev-not-sub") {
            dimension = "version"
            buildConfigField("boolean", "isDevVersion", "true")
            buildConfigField("boolean", "isSubscribed", "false")
            versionNameSuffix = "-dev-not-sub"
        }
        create("prod") {
            dimension = "version"
            buildConfigField("boolean", "isDevVersion", "false")
            buildConfigField("boolean", "isSubscribed", "false")
            versionNameSuffix = "-prod"
        }
    }

    applicationVariants.all {
        outputs.all {
            // AAB file name that You want. Flavor name also can be accessed here.
            val aabPackageName = "sampledemo_$(versionName)_($versionCode).aab"
            // Get final bundle task name for this variant
            val bundleFinalizeTaskName = StringBuilder("sign").run {
                // Add each flavor dimension for this variant here
                productFlavors.forEach {
                    append(it.name.replaceFirstChar { it.uppercase() })
                }
                // Add build type of this variant
                append(buildType.name.replaceFirstChar { it.uppercase() })
                append("Bundle")
                toString()
            }
            tasks.named(bundleFinalizeTaskName, FinalizeBundleTask::class.java) {
                val file = finalBundleFile.asFile.get()
                val finalFile = File(file.parentFile, aabPackageName)
                finalBundleFile.set(finalFile)
            }
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    // Room
    implementation(libs.androidx.room.runtime)
    ksp(libs.androidx.room.compiler)
    implementation(libs.androidx.room.ktx)

    // Nav3
    implementation(libs.androidx.navigation3.ui.android)
    implementation(libs.kotlinx.serialization.core)
    implementation(libs.kotlinx.serialization.json)

    // WavyIndicator
    implementation(libs.material3)

    // Ktor
    implementation(libs.ktor.client.okhttp)
    implementation(libs.ktor.client.logging)
    implementation(libs.ktor.client.auth)
    implementation(libs.ktor.serialization.kotlinx.json)
    implementation(libs.ktor.client.content.negotiation)

    implementation(libs.slf4j.android)
    implementation(libs.slf4j.api)
    implementation(libs.slf4j.jdk14)

    // Gson
    implementation(libs.gson)

    // Koin
    implementation(project.dependencies.platform(libs.koin.bom))
    implementation(libs.koin.android)
    implementation(libs.koin.core)
    implementation(libs.koin.androidx.workmanager)
    implementation(libs.koin.androidx.compose)

    implementation(libs.androidx.lifecycle.viewmodel.compose)
    // You'll also need the core lifecycle runtime for ViewModels
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)

    // ExoPlayer
    implementation(libs.androidx.media3.ui.compose)
    implementation(libs.media3.exoplayer)
    implementation(libs.media3.ui)
    implementation(libs.media3.common)
}