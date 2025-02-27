ext["namespace"] = "banquemisr.moviedp"

apply {

    from("$rootDir/android-library-build.gradle")
}
plugins {
    alias(libs.plugins.android.application)

}
android {
    defaultConfig {
        applicationId = "banquemisr.moviedp"
        versionCode = 1
        versionName = "1.0"
    }
}


dependencies {
    "implementation"(project(":presentation"))
    "implementation"(project(":data"))
    "implementation"(project(":domain"))


    //Jetpack Compose UI Dependencie kotlin
    "implementation"(platform(libs.androidx.compose.bom)) // Manage Compose versions
    "implementation"(libs.androidx.ui) // Jetpack Compose UI toolkit
    "implementation"(libs.androidx.ui.graphics) // Compose graphics support
    "implementation"(libs.androidx.ui.tooling.preview) // Preview UI components in Android Studio
    "implementation"(libs.androidx.material3) // Material Design 3 components
    "implementation"(libs.androidx.activity.compose) // Compose support for activities

    // Adds backward compatibility support for Android components on older devices.
    "implementation"(libs.androidx.appcompat)

    //Debug Dependencies for UI Development kotlin
    "debugImplementation"(libs.androidx.ui.tooling) // Compose UI debugging tools
    "debugImplementation"(libs.androidx.ui.test.manifest) // UI testing manifest support

    //UI Testing Dependencies kotlin
    "androidTestImplementation"(platform(libs.androidx.compose.bom))

    // Manage Compose test dependencies
    "androidTestImplementation"(libs.androidx.ui.test.junit4)


}


