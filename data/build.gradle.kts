import java.util.Properties

ext["namespace"] = "banquemisr.data"

apply {

    from("$rootDir/android-library-build.gradle")
}
plugins {
    alias(libs.plugins.android.library)

}




dependencies {
    "implementation" (project(":domain"))

    "implementation"(libs.retrofit)
    "implementation"(libs.gson)
    "implementation"(libs.converter.gson)
    "implementation"(libs.logging.interceptor)
}