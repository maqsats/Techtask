plugins {
    id("local.library")
}

android {
    val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")
    namespace = libs.findVersion("packageName").get().toString() + ".pagination"

    defaultConfig {
        consumerProguardFiles("consumer-rules.pro")
    }
}

dependencies {
    implementation(projects.core.shared)
    implementation(projects.core.logger)

    implementation(libs.composeMaterial3)
    api(libs.pagingCompose)
}

