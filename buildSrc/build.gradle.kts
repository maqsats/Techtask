plugins {
    `kotlin-dsl`
}

dependencies {
    implementation(plugin(libs.plugins.kotlin.android))
    implementation(plugin(libs.plugins.compose.compiler))
    implementation(plugin(libs.plugins.kotlin.serialization))
    implementation(plugin(libs.plugins.kotlin.symbolProcessing))
    implementation(plugin(libs.plugins.android.application))
    implementation(plugin(libs.plugins.android.library))
}

kotlin {
    jvmToolchain(17)
}

fun plugin(plugin: Provider<PluginDependency>): Provider<String> =
    plugin.map {
        when (it.pluginId) {
            "com.android.application", "com.android.library" -> "com.android.tools.build:gradle:${it.version}"
            else -> "${it.pluginId}:${it.pluginId}.gradle.plugin:${it.version}"
        }
    }

