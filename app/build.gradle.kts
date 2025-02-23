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
}


