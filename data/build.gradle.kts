import java.io.FileInputStream
import java.util.Properties

ext["namespace"] = "banquemisr.data"

apply {

    from("$rootDir/android-library-build.gradle")
}
plugins {
    alias(libs.plugins.android.library)

}
android {
    defaultConfig{

        val properties = Properties().apply {
            load(FileInputStream(File("$rootDir/local.properties")))
        }
        buildConfigField("String", "MOVIEDB_BASE_URL", "\"${properties.getProperty("MOVIEDB_BASE_URL")}\"")
        buildConfigField("String", "MOVIEDB_IMAGE_URL", "\"${properties.getProperty("MOVIEDB_IMAGE_URL")}\"")
        buildConfigField("String", "MOVIEDB_API_KEY", "\"${properties.getProperty("MOVIEDB_API_KEY")}\"")


    }

}




dependencies {
    "implementation" (project(":domain"))
    "implementation"(libs.retrofit)
    "implementation"(libs.gson)
    "implementation"(libs.converter.gson)
    "implementation"(libs.logging.interceptor)
}