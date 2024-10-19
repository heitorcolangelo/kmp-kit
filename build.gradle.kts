plugins {
    //trick: for the same plugin versions in all sub-modules
    alias(libs.plugins.androidLibrary).apply(false)
    alias(libs.plugins.kotlinMultiplatform).apply(false)
    `maven-publish`
}

subprojects {
    apply(plugin = "maven-publish")

    version = this.property("version") as String? ?: "0.0.0"

    configure<PublishingExtension> {
        repositories {
            maven {
                name = "GitHubPackages"
                url = uri("https://maven.pkg.github.com/heitorcolangelo/kmp-kit")
                credentials {
                    username = System.getenv("GITHUB_ACTOR")
                    password = System.getenv("PUBLISHING_TOKEN")
                }
            }
        }
    }
}
