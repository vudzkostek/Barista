plugins {
    id("com.android.library")
    kotlin("android")
    alias(libs.plugins.composeCompiler)
}

apply(from = "../config/android-quality.gradle")

ext["PUBLISH_ARTIFACT_ID"] = "barista-compose"

apply(from = "${rootProject.projectDir}/scripts/publish-module.gradle")

android {
    compileSdk = 33

    defaultConfig {
        minSdk = 21
        targetSdk = 33

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        testInstrumentationRunnerArguments["clearPackageData"] = "true"
    }

    buildFeatures {
        compose = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }

    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    namespace = "com.adevinta.android.barista.compose"
}

dependencies {
    implementation(platform(libs.androidX.compose.bom))
    debugImplementation(libs.androidX.compose.ui.ui)
    debugImplementation(libs.androidX.compose.material)
    debugImplementation(libs.androidX.compose.ui.tooling.preview)
    debugImplementation(libs.androidX.compose.ui.tooling.core)
    debugImplementation(libs.androidX.compose.test.ui.manifest)

    api(libs.androidX.test.junit)
    api(libs.androidX.compose.test.ui.junit4)

    debugImplementation(libs.androidX.lifecycle)
    debugImplementation(libs.androidX.compose.activity)

}