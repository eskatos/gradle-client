@file:Suppress("UnstableApiUsage")

pluginManagement {
    repositories {
        mavenCentral()
        gradlePluginPortal {
            content {
                includeGroupAndSubgroups("com.gradle")
                includeGroupAndSubgroups("org.gradle")
                includeGroupAndSubgroups("io.github.gradle")
            }
        }
    }
}

plugins {
    id("com.gradle.enterprise") version "3.16.2"
    id("io.github.gradle.gradle-enterprise-conventions-plugin") version "0.9.1"
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
}

dependencyResolutionManagement {
    repositoriesMode = RepositoriesMode.FAIL_ON_PROJECT_REPOS
    repositories {
        google {
            content {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        gradlePluginPortal() {
            content { 
                includeGroup("org.gradle.toolchains")
            }
        }
        maven(url = "https://repo.gradle.org/gradle/libs-releases") {
            content {
                includeGroup("org.gradle")
            }
        }
        maven(url = "https://repo.gradle.org/gradle/libs-snapshots") {
            content {
                includeGroup("org.gradle")
            }
        }
        mavenCentral()
    }
}

includeBuild("declarative-gradle/unified-prototype/unified-plugin") {
    dependencySubstitution { 
        substitute(module("org.gradle.experimental:plugin-android")).using(project(":plugin-android"))
    }
}

// move this to daemon toolchain once Gradle supports it
require(JavaVersion.current() == JavaVersion.VERSION_17) {
    "This build requires Java 17, currently using ${JavaVersion.current()}"
}

rootProject.name = "gradle-client-root"

include(":gradle-client")
include(":build-action")
include(":mutations-demo")
