// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
    repositories {
        maven(url = "https://jitpack.io")
        jcenter()
        google()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:3.2.0-rc02")
        classpath("com.github.dcendents:android-maven-gradle-plugin:2.0")
    }
    dependencies {
        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

allprojects {
    repositories {
        maven(url = "https://jitpack.io")
        jcenter()
        google()
    }
}