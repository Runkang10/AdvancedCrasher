plugins {
    id("io.papermc.paperweight.userdev") version "2.0.0-beta.11"
    id("xyz.jpenilla.run-paper") version "2.3.1"
}

dependencies {
    paperweight.paperDevBundle("1.21.4-R0.1-SNAPSHOT")
    compileOnly("me.clip:placeholderapi:latest.integration")
}

paperweight {
    reobfArtifactConfiguration = io.papermc.paperweight.userdev.ReobfArtifactConfiguration.MOJANG_PRODUCTION
}

tasks {
    shadowJar {
        archiveBaseName.set(rootProject.name)
        archiveVersion.set(rootProject.version as String)
        archiveClassifier.set("")

        relocate("dev.jorel.commandapi", "io.github.runkang10.AdvancedCrash.commandapi")
    }

    reobfJar {
        dependsOn(shadowJar)
        base.archivesName.set(rootProject.name)
    }

    assemble {
        dependsOn(reobfJar)
    }

    build {
        dependsOn(shadowJar)
    }

    processResources {
        val props = mapOf("version" to version)
        inputs.properties(props)
        filteringCharset = "UTF-8"
        filesMatching("paper-plugin.yml") {
            expand(props)
        }
    }

    runServer {
        minecraftVersion("1.21.4")
    }
}

