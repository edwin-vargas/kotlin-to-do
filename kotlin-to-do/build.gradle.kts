plugins {
    kotlin("jvm") version "1.7.10" // Use the latest stable version
    application // For building a runnable application
}

repositories {
    mavenCentral()
}

dependencies {
     implementation(kotlin("stdlib")) // Standard library for Kotlin
    testImplementation(kotlin("test")) // For unit testing
}

application {
    mainClass.set("MainKt") // Set the main class for the application
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
        jvmTarget = "1.8" // Set the JVM target version
    }
}