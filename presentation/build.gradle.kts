ext["namespace"] = "banquemisr.presentation"

apply {

    from("$rootDir/android-library-build.gradle")
}
plugins {
    alias(libs.plugins.android.library)

}


dependencies {
    "implementation"(project(":domain"))
    "implementation"(project(":components"))
    "implementation"(libs.coil.compose)
    "implementation"(libs.nav.compose)

    //Jetpack Compose UI Dependencie kotlin
    "implementation"(platform(libs.androidx.compose.bom)) // Manage Compose versions
    "implementation"(libs.androidx.ui) // Jetpack Compose UI toolkit
    "implementation"(libs.androidx.ui.graphics) // Compose graphics support
    "implementation"(libs.androidx.ui.tooling.preview) // Preview UI components in Android Studio
    "implementation"(libs.androidx.material3) // Material Design 3 components


    //Debug Dependencies for UI Development kotlin
    "debugImplementation"(libs.androidx.ui.tooling) // Compose UI debugging tools
    "debugImplementation"(libs.androidx.ui.test.manifest) // UI testing manifest support

    //UI Testing Dependencies kotlin
    "androidTestImplementation"(platform(libs.androidx.compose.bom)) // Manage Compose test dependencies
    "androidTestImplementation"(libs.androidx.ui.test.junit4) // JUnit4 for UI testing


}