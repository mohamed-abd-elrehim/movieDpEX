ext["namespace"] = "banquemisr.components"

apply {

    from("$rootDir/android-library-build.gradle")
}
plugins {
    alias(libs.plugins.android.library)
}

dependencies {
    "implementation"(libs.coil.compose)
    //Jetpack Compose UI Dependencie kotlin
    "implementation"(platform(libs.androidx.compose.bom)) // Manage Compose versions
    "implementation"(libs.androidx.ui) // Jetpack Compose UI toolkit
    "implementation"(libs.androidx.ui.graphics) // Compose graphics support
    "implementation"(libs.androidx.ui.tooling.preview) // Preview UI components in Android Studio
    "implementation"(libs.androidx.material3) // Material Design 3 components


}