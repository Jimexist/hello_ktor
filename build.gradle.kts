plugins {
  idea
  application
  kotlin("jvm") version "1.5.31"
  kotlin("plugin.serialization") version "1.5.31"
  id("com.diffplug.gradle.spotless") version "4.5.1"
}

group = "me.jiayu"
version = "0.1-SNAPSHOT"

application {
  mainClass.set("me.jiayu.helloktor.ApplicationKt")
}

configure<com.diffplug.gradle.spotless.SpotlessExtension> {
  kotlin {
    ktfmt()
  }
  kotlinGradle {
    ktfmt()
  }
}

repositories {
  mavenCentral()
}

java {
  sourceCompatibility = JavaVersion.VERSION_11
  targetCompatibility = JavaVersion.VERSION_11
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
  kotlinOptions {
    jvmTarget = "11"
  }
}

val exposedVersion: String by project
val ktorVersion: String by project

dependencies {
  implementation(kotlin("stdlib"))
  implementation("io.ktor:ktor-server-core:$ktorVersion")
  implementation("io.ktor:ktor-server-netty:$ktorVersion")
  implementation("io.ktor:ktor-serialization:$ktorVersion")
  implementation("ch.qos.logback:logback-classic:1.2.9")

  // https://mvnrepository.com/artifact/com.h2database/h2
  implementation("com.h2database:h2:1.2.140")
  implementation("org.jetbrains.exposed:exposed-core:$exposedVersion")
  implementation("org.jetbrains.exposed:exposed-dao:$exposedVersion")
  implementation("org.jetbrains.exposed:exposed-jdbc:$exposedVersion")


}
