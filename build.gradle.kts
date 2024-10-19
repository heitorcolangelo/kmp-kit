plugins {
    //trick: for the same plugin versions in all sub-modules
    alias(libs.plugins.androidLibrary).apply(false)
    alias(libs.plugins.kotlinMultiplatform).apply(false)
}

subprojects {
    apply(plugin = "maven-publish")

    group = "com.heitorcolangelo.kmpkit"
    version = this.property("version") as String? ?: "0.0.0"

    configure<PublishingExtension> {
        repositories {
            maven {
                name = "KMPKit"
                url = uri("https://maven.pkg.github.com/heitorcolangelo/KMPKit")
                credentials {
                    username = System.getenv("GITHUB_ACTOR")
                    password = System.getenv("GITHUB_TOKEN")
                }
            }
        }
    }
}
