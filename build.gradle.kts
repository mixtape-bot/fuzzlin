import lol.dimensional.gradle.dsl.Version

buildscript {
    repositories {
        gradlePluginPortal()
        maven("https://maven.dimensional.fun/releases")
    }

    dependencies {
        classpath("fun.dimensional.gradle:gradle-tools:1.0.2")
    }
}

plugins {
    `maven-publish`

    kotlin("jvm") version "1.6.10"
}

kotlin {
    explicitApi()
}


val version = Version(1, 0, 0)
val artifact = "fuzzlin"

project.group = "gg.mixtape"
project.version = version.asString()

repositories {
    mavenCentral()
    maven("https://maven.dimensional.fun/releases")
    maven("https://jitpack.io")
}

dependencies {
    implementation("gg.mixtape:native-loader:1.0")

    testImplementation("ch.qos.logback:logback-classic:1.2.10")
}

val sourcesJar by tasks.registering(Jar::class) {
    archiveClassifier.set("sources")
    from(sourceSets["main"].allSource)
}

tasks.create<Exec>("deployRust") {
    workingDir = file(".")
    commandLine = listOf("cargo", "build", "--release")

    copy {
        // TODO: Make these a per-system value lol
        from("build/rust/release/lib$artifact.so")
        into("src/main/resources/natives/linux-x86-64")
    }
}

publishing {
    repositories {
        maven(version.repository.fullUrl) {
            authentication {
                create<BasicAuthentication>("basic")
            }

            credentials {
                username = System.getenv("REPO_ALIAS")
                password = System.getenv("REPO_TOKEN")
            }
        }
    }

    publications {
        create<MavenPublication>("Fuzzlin") {
            from(components["kotlin"])

            artifactId = artifact
            version = project.version as String

            artifact(sourcesJar)
        }
    }
}
