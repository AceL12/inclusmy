plugins {
    kotlin("jvm") version ("1.8.0")
    `maven-publish`
    id("com.gitlab.candicey.stellar") version "0.1.0"
    id("net.weavemc.gradle") version "1.0.0-PRE"
}

val projectName: String by project
val projectGroup: String by project
val projectVersion: String by project
val gitlabProjectId: String by project

weave {
    configure {
        name = "Inclumsy"
        modId = "inclumsy"
        entryPoints = listOf("$projectGroup.InclumsyMain")
        mixinConfigs = listOf()
        mcpMappings()
    }
    version("1.8.9")
}

stellar {
    relocate {
    }

    dependencies {
        zenithCore version "2.0.1"
        zenithCoreVersionedApi version "v1_8"
        zenithLoader version "0.2.0"
    }
}

group = projectGroup
version = projectVersion

repositories {
    mavenCentral()
    maven("https://jitpack.io")
    maven("https://repo.spongepowered.org/repository/maven-public/")
    maven("https://repo.weavemc.dev/releases")

    maven {
        url = uri("https://gitlab.com/api/v4/projects/$gitlabProjectId/packages/maven")
        name = "GitLab"
        credentials(HttpHeaderCredentials::class) {
            name = "Private-Token"
            value = findProperty("gitLabPrivateToken") as String?
        }
        authentication {
            create("header", HttpHeaderAuthentication::class)
        }
    }
}

dependencies {
    testImplementation(kotlin("test"))

    implementation(libs.weaveLoader)
    implementation(libs.weaveCommon)
    implementation(libs.weaveInternals)
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(8)
}

publishing {
    publications {
        create<MavenPublication>("library") {
            from(components["java"])

            val libsDirectory = File(project.projectDir, "build/libs")
            val file = libsDirectory.resolve("${project.name}-${project.version}-relocated.jar")
            artifact(file) {
                classifier = "relocated"
            }
        }
    }

    repositories {
        maven {
            url = uri("https://gitlab.com/api/v4/projects/$gitlabProjectId/packages/maven")
            credentials(HttpHeaderCredentials::class) {
                name = "Private-Token"
                value = findProperty("gitLabPrivateToken") as String?
            }
            authentication {
                create("header", HttpHeaderAuthentication::class)
            }
        }
    }
}