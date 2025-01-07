package io.github.runkang10.AdvancedCrash.paper.static

@Suppress("unused")
class ErrorData private constructor() {
    companion object {
        const val ACCESS_PLUGIN_INSTANCE_FAILED =
            "AdvancedCrash is not loaded.\n\t1) Ensure that the plugin is installed and enabled.\n\t2) Ensure that you're not shading this plugin."

        const val NMS_ACCESS_FAILED =
            "Could not use NMS packets.\n\t1) Ensure that you're using a compatible server version.\n\t2) Ensure that you're using 'Paper' & (Paper forks) based server instead of Spigot & Bukkit."
    }
}