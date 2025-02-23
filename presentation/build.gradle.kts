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

}