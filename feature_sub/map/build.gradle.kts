plugins {
    id("local.library")
}

android {
    val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")
    namespace = libs.findVersion("packageName").get().toString() + ".feature_sub.map"

    defaultConfig {
        consumerProguardFiles("consumer-rules.pro")
    }
}

dependencies {
    api(libs.composeMaterial3)
    api(libs.mapsCompose)
    api(libs.playServicesMaps)
}

