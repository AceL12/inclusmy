pluginManagement {
    repositories {
        mavenCentral()
        gradlePluginPortal()
        maven("https://gitlab.com/api/v4/projects/57327439/packages/maven") // Stellar
        maven("https://gitlab.com/api/v4/projects/57325214/packages/maven") // Weave Gradle
        maven("https://jitpack.io")
        maven("https://repo.weavemc.dev/releases")
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.5.0"
}

val projectName: String by settings
rootProject.name = projectName
