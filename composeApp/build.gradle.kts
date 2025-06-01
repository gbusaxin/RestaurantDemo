import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    id("kotlin-parcelize")
    id("org.jetbrains.kotlin.plugin.serialization") version "2.1.20-RC3"
}

kotlin {
    androidTarget {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "composeApp"
            isStatic = true
        }
    }

    sourceSets {
//        iosMain.dependencies {
//            implementation(project(":composeApp"))
//        }
        androidMain.dependencies {
            implementation(compose.preview)
            implementation(libs.androidx.activity.compose)


            implementation("com.google.android.gms:play-services-location:21.3.0")
            implementation("com.google.android.gms:play-services-maps:19.1.0")
        }
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
//            implementation(compose.material)
            implementation(compose.material3)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(libs.androidx.lifecycle.viewmodel)
            implementation(libs.androidx.lifecycle.runtime.compose)

            // Android
            implementation(libs.androidx.core.ktx)

            // Koin
            implementation(libs.koin.core)

            // Multiplatform settings
            implementation(libs.multiplatform.settings)

            // decompose
            val decomposeVersion = "2.1.0"
            api("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.1")
            api("com.arkivanov.decompose:decompose:$decomposeVersion-compose-experimental")
            api("com.arkivanov.decompose:extensions-compose-jetbrains:$decomposeVersion-compose-experimental")

            // InsetX for controlling status bar and navigation bar
            implementation("com.moriatsushi.insetsx:insetsx:0.1.0-alpha10")

            implementation("com.google.maps.android:maps-compose:6.1.0")

            implementation("com.google.accompanist:accompanist-systemuicontroller:0.33.2-alpha")
            implementation("com.google.accompanist:accompanist-permissions:0.32.0")

            implementation("androidx.datastore:datastore-preferences:1.1.3")
            implementation("androidx.datastore:datastore-preferences-core:1.1.3")

            implementation("io.coil-kt.coil3:coil-compose:3.0.0")
//            implementation("io.coil-kt.coil3:coil-network-okhttp:3.1.0")

            implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.6.1")
        }
    }
}

android {
    namespace = "org.gbu.restaurant"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "org.gbu.restaurant"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            signingConfig = signingConfigs.getByName("debug")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    debugImplementation(compose.uiTooling)
}

