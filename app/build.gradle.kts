plugins {
    id("local.app")
}

android {
    val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

    namespace = libs.findVersion("packageName").get().toString()

    defaultConfig {
        applicationId = libs.findVersion("packageName").get().toString()
        versionCode = 1
        versionName = "1.0"
    }

    buildTypes {
        getByName("debug") {
        }
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    buildFeatures {
        buildConfig = true
        compose = true
    }
}

dependencies {
    // Core modules - only those directly used in app module
    implementation(projects.core.network)
    implementation(projects.core.presentation)
    implementation(projects.core.logger)
    implementation(projects.core.design)

    // Feature modules
    implementation(projects.feature.users)
    implementation(projects.feature.root)

    implementation(libs.composeUI)
    implementation(libs.activityCompose)
    implementation(libs.coreKtx)
}

