import com.codewithmohsen.gradle.DependenciesPlugin

plugins {
    id("java-library")
    id("org.jetbrains.kotlin.jvm")
    id("kotlin-kapt")
    id("com.codewithmohsen.dependencies")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
    api(project(":common"))

    implementation(DependenciesPlugin.Dagger)
    implementation(DependenciesPlugin.Moshi)
    implementation(DependenciesPlugin.KotlinXCoroutines)
}