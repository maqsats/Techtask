plugins {
    id("local.library")
}

android {
    val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")
    namespace = libs.findVersion("packageName").get().toString() + ".feature.root"

    defaultConfig {
        consumerProguardFiles("consumer-rules.pro")
    }
}

dependencies {
    implementation(projects.core.shared)
    implementation(projects.core.network)
    implementation(projects.core.pagination)
    implementation(projects.core.presentation)
    implementation(projects.feature.users)
}

