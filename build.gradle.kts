import org.cyclonedx.gradle.CyclonedxDirectTask

plugins {
    //trick: for the same plugin versions in all sub-modules
    alias(libs.plugins.androidLibrary).apply(false)
    alias(libs.plugins.kotlinMultiplatform).apply(false)
    `maven-publish`
    alias(libs.plugins.cyclonedx).apply(false)
}

subprojects {
    apply(plugin = "maven-publish")
    apply(plugin = "org.cyclonedx.bom")

    group = "com.kmpkit"
    version = this.property("version") as String? ?: "0.0.0"

    configure<PublishingExtension> {
        publications {
            afterEvaluate {
                publications.withType<MavenPublication> {
                    artifact(file("${layout.buildDirectory.asFile.orNull}/reports/cyclonedx/bom.json")) {
                        classifier = "sbom"
                        extension = "json"
                    }
                }
            }
        }

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

    tasks.withType<CyclonedxDirectTask>().configureEach {
        includeConfigs = listOf("runtimeClasspath", "iosArm64CompileKlibraries")

        skipConfigs.set(listOf("compileClasspath", "testCompileClasspath", "testRuntimeClasspath"))
    }

}
