// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
    alias(libs.plugins.android.library) apply false
    id("org.sonarqube") version "5.0.0.4638"
}

subprojects {
    sonar {
        properties {
            property("sonar.projectKey", "XPEHO_xpeho_ui_android_d4f36bbd-6fdc-4467-8e77-9dc16a19a085")
            property("sonar.projectName", "xpeho_ui_android")
        }
    }
}