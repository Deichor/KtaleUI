plugins {
    alias(libs.plugins.kotlin.jvm)
    `java-library`
    `maven-publish`
}

group = "org.deichor.ktaleui"
version = project.property("version") as String

publishing {
    publications {
        create<MavenPublication>("maven") {
            from(components["java"])
            groupId = "org.deichor"
            artifactId = "ktaleui"
            version = project.version.toString()
        }
    }
    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/deichor/KtaleUI")
            credentials {
                username = System.getenv("GITHUB_ACTOR") ?: project.findProperty("gpr.user") as String? ?: ""
                password = System.getenv("GITHUB_TOKEN") ?: project.findProperty("gpr.key") as String? ?: ""
            }
        }
    }
}

repositories {
    // Use Maven Central for resolving dependencies.
    mavenCentral()
    maven("https://maven.hytale.com/release")
}

dependencies {
    compileOnly(libs.hytale)
}

// Apply a specific Java toolchain to ease working on different environments.
java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(25)
    }
}
