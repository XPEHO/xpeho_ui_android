import java.io.FileInputStream
import java.util.Properties

plugins {

    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    `maven-publish`

}

android {
    namespace = "com.xpeho.xpeho_ui_android"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    packaging {

    }
}

val githubProperties = Properties()
if (rootProject.file("github.properties").exists()) {
    githubProperties.load(FileInputStream(rootProject.file("github.properties")))
}

val versionCode = System.getenv("PUBLISH_VERSION") ?: "0.0.0" // version code
val artifactId = "xpeho_ui_android" // library name ID

publishing {
    publications {
        create<MavenPublication>("gpr") {
            run {
                groupId = "com.xpeho.packages"
                artifactId = artifactId
                version = versionCode
                artifact(
                    "${
                        layout.buildDirectory.dir("outputs/aar").get().asFile
                    }/${artifactId}-release.aar"
                )
            }
        }
    }

    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/XPEHO/xpeho_ui_android")
            credentials {
                username = githubProperties["gpr.usr"] as String? ?: System.getenv("USER")
                password =
                    githubProperties["gpr.key"] as String? ?: System.getenv("PAT")

            }
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}

