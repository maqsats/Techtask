plugins {
    id("local.library")
}

android {
    val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")
    namespace = libs.findVersion("packageName").get().toString() + ".network"

    defaultConfig {
        consumerProguardFiles("consumer-rules.pro")
    }
}

dependencies {
    implementation(projects.core.logger)
    implementation(projects.core.shared)

    api(libs.kotlin)
    api(libs.coreKtx)
    api(libs.coroutines)
    api(libs.chucker)
    api(libs.bundles.koin)
    api(libs.bundles.retrofit)
    api(libs.bundles.lifecycle)
}

