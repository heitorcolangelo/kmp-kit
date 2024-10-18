plugins {
    `maven-publish`
    signing
}

publishing {
    // Configure all publications
    publications.withType<MavenPublication> {
        // Stub javadoc.jar artifact
        artifact(tasks.register("${name}JavadocJar", Jar::class) {
            archiveClassifier.set("javadoc")
            archiveAppendix.set(this@withType.name)
        })

        // Provide artifacts information required by Maven Central
        pom {
            name.set("Kotlin Multiplatform Kit library")
            description.set("The KMPKit library contains helpful features for Kotlin Multiplatform, Android and iOS projects.")
            url.set("https://github.com/heitorcolangelo/KMPKit")

            licenses {
                license {
                    name.set("Apache License 2.0")
                    url.set("https://opensource.org/license/apache-2-0")
                }
            }
            developers {
                developer {
                    id.set("HeitorColangelo")
                    name.set("Heitor Colangelo")
                    organization.set("Heitor Colangelo Dev")
                    organizationUrl.set("https://www.heitorcolangelo.dev")
                }
            }
            scm {
                url.set("https://github.com/heitorcolangelo/KMPKit")
            }
        }
    }
}

signing {
    if (project.hasProperty("signing.gnupg.keyName")) {
        useGpgCmd()
        sign(publishing.publications)
    }
}