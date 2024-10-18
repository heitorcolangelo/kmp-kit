plugins {
    //trick: for the same plugin versions in all sub-modules
    alias(libs.plugins.androidLibrary).apply(false)
    alias(libs.plugins.kotlinMultiplatform).apply(false)
}

fun Project.getPropertyOrDefault(
    propertyName: String,
    defaultValue: String
): String = this.property(propertyName) as String? ?: run {
    println("$propertyName gradle property not set. Using default value")
    defaultValue
}

fun Project.getVersionName(): String = this.getPropertyOrDefault(
    propertyName = "version",
    defaultValue = "0.0.0"
)

fun Project.getGroupId(): String = this.getPropertyOrDefault(
    propertyName = "group",
    defaultValue = "com.heitorcolangelo.kmpkit"
)

subprojects {
    apply(plugin = "maven-publish")

    group = this.getGroupId()
    version = this.getVersionName()

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
