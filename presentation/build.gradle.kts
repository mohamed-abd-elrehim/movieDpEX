ext["namespace"] = "banquemisr.presentation"

apply {

    from("$rootDir/android-library-build.gradle")
}
plugins {

}


dependencies {
    "implementation"(project(":domain"))
    "implementation"(project(":components"))
    "implementation"(project(":core"))

}