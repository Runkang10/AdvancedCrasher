plugins {
    kotlin("jvm") version "latest.integration"
    id("com.gradleup.shadow") version "latest.integration"
}

allprojects {
    apply(plugin = "org.jetbrains.kotlin.jvm")
    apply(plugin = "com.gradleup.shadow")

    group = "io.github.runkang10.AdvancedCrash"
    version = "1.0.0"

    repositories {
        mavenCentral()
        gradlePluginPortal()

        maven("https://repo.papermc.io/repository/maven-public/") {
            name = "papermc-repo"
        }
        maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/") {
            name = "spigot-repo"
            isAllowInsecureProtocol = true
        }
        maven("https://oss.sonatype.org/content/groups/public/") {
            name = "sonatype"
        }
        maven("https://repo.codemc.org/repository/maven-public/") {
            name = "codemc"
        }
        maven("https://jitpack.io") {
            name = "jitpack"
        }
        maven("https://repo.lushplugins.org/releases/") {
            name = "lushplugins"
        }
        maven("https://repo.extendedclip.com/releases/") {
            name = "extendedclip"
        }
    }

    dependencies {
        implementation("dev.jorel:commandapi-bukkit-shade-mojang-mapped:latest.integration")
        compileOnly("org.lushplugins:ChatColorHandler:latest.integration")
    }

    tasks {
        jar {
            enabled = false
        }
    }

    kotlin {
        jvmToolchain(21)
    }
}