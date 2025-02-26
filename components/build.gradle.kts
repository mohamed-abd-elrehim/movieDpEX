ext["namespace"] = "banquemisr.components"

apply {

    from("$rootDir/android-library-build.gradle")
}
plugins {
    alias(libs.plugins.android.library)
}

