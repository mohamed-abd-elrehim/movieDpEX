ext["namespace"] = "banquemisr.domain"

apply {

    from("$rootDir/android-library-build.gradle")
}
plugins {
    alias(libs.plugins.android.library)

}

