plugins {
    id("com.android.application")
}

android {
    compileOptions {
        setSourceCompatibility(JavaVersion.VERSION_1_8)
        setTargetCompatibility(JavaVersion.VERSION_1_8)
    }

    compileSdkVersion(27)

    defaultConfig {
        applicationId = "ru.dimorinny.showcasesample"
        minSdkVersion(15)
        targetSdkVersion(27)
        versionCode = 1
        versionName = "0.0.1"
        testInstrumentationRunner = "android.support.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }
}

repositories {
    google()
}

dependencies {
    implementation(project(":showcasecard"))
    implementation("com.android.support:appcompat-v7:27.1.1")
}