import gradle.kotlin.dsl.accessors._cf23dfd706c1bf9cda1f64fe1da16a33.nexusPublishing

plugins {
    id("io.github.gradle-nexus.publish-plugin")
}

allprojects {
    group = "com.kmpkit"
    version = "0.0.1"
}

nexusPublishing {
    // Configure maven central repository
    // https://github.com/gradle-nexus/publish-plugin#publishing-to-maven-central-via-sonatype-ossrh
    repositories {
        sonatype {  //only for users registered in Sonatype after 24 Feb 2021
            nexusUrl.set(uri("https://s01.oss.sonatype.org/service/local/"))
            snapshotRepositoryUrl.set(uri("https://s01.oss.sonatype.org/content/repositories/snapshots/"))
        }
    }
}