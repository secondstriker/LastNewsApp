import com.codewithmohsen.gradle.DependenciesPlugin

plugins {
    id("com.android.library")
    id("kotlin-kapt")
    id("kotlin-android")
    id("com.codewithmohsen.dependencies")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdk = DependenciesPlugin.CompileSdk

    defaultConfig {
        minSdk = DependenciesPlugin.MinSdk
        targetSdk = DependenciesPlugin.TargetSdk

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
    buildFeatures {
        dataBinding = true
    }
}

dependencies {
    implementation(project(":domain"))
    implementation(project(":commonAndroid"))
    implementation(project(":presentation"))

    implementation(DependenciesPlugin.AndroidxCore)
    implementation(DependenciesPlugin.Material)
    implementation(DependenciesPlugin.AndroidxAppCompat)
    androidTestImplementation(DependenciesPlugin.AndroidxJUnit)
    androidTestImplementation(DependenciesPlugin.AndroidxEspresso)

    implementation(DependenciesPlugin.KotlinXCoroutines)
    implementation(DependenciesPlugin.KotlinXCoroutinesAndroid)

    implementation(DependenciesPlugin.Timber)

    implementation(DependenciesPlugin.PreferencesKotlin)

    //hilt
    implementation(DependenciesPlugin.HiltAndroid)
    kapt(DependenciesPlugin.HiltCompiler)

    implementation(DependenciesPlugin.MaterialProgressBar)
    implementation(DependenciesPlugin.ElasticView)

    implementation(DependenciesPlugin.Glide)
    kapt(DependenciesPlugin.GlideKtx)
    implementation(DependenciesPlugin.SwipeRefreshLayout)

    implementation(DependenciesPlugin.lifecycleViewModelKtx)
    implementation(DependenciesPlugin.ViewModelRuntimeKtx)
}