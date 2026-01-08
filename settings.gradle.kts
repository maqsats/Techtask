rootProject.name = "Techtask"

include(
    ":app",
    // Core modules
    ":core:design",
    ":core:network",
    ":core:pagination",
    ":core:presentation",
    ":core:shared",
    ":core:logger",
    // Feature modules
    ":feature:users",
    ":feature:root",
)

pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}

@Suppress("UnstableApiUsage")
dependencyResolutionManagement {
    repositories {
        google()
        mavenLocal()
        mavenCentral()
    }
}

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

