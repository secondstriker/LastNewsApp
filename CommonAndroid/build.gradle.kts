import com.codewithmohsen.gradle.DependenciesPlugin
import org.jetbrains.kotlin.kapt3.base.Kapt.kapt

plugins {
    id("com.android.library")
    id("kotlin-kapt")
    id("kotlin-android")
    id("com.codewithmohsen.dependencies")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdk = 32

    defaultConfig {
        minSdk = 21
        targetSdk = 32

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(project(":common"))

    implementation(DependenciesPlugin.HiltAndroid)
    kapt(DependenciesPlugin.HiltCompiler)
    implementation(DependenciesPlugin.Timber)
    implementation(DependenciesPlugin.KotlinXCoroutines)
    implementation(DependenciesPlugin.KotlinXCoroutinesAndroid)
}