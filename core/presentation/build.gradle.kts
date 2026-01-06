plugins {
    id("local.library")
}

android {
    val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")
    namespace = libs.findVersion("packageName").get().toString() + ".presentation"
}

dependencies {
    api(libs.kotlin)
    api(libs.composeMaterial3)
    api(libs.bundles.koin)
    api(libs.koinAndroidCompose)
    api(libs.browser)
    api(libs.decompose)
    api(libs.decomposeJetpackCompose)
    api(libs.mvikotlin)
    api(libs.mvikotlinMain)
    api(libs.mvikotlinExtensionsCoroutines)
    api(libs.essenty)

    implementation(projects.core.shared)
    implementation(projects.core.logger)
}

