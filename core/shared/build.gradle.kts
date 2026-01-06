plugins {
    id("local.library")
}

android {
    val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")
    namespace = libs.findVersion("packageName").get().toString() + ".shared"

    defaultConfig {
        consumerProguardFiles("consumer-rules.pro")
    }
}

dependencies {
    implementation(libs.composeMaterial3)
    implementation(libs.serializationJson)
}

