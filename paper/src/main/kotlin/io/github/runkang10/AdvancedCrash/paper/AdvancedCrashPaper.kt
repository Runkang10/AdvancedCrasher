package io.github.runkang10.AdvancedCrash.paper

import dev.jorel.commandapi.CommandAPI
import dev.jorel.commandapi.CommandAPIBukkitConfig
import io.github.runkang10.AdvancedCrash.paper.commands.CrashCommand
import io.github.runkang10.AdvancedCrash.paper.static.ErrorData
import org.bukkit.plugin.java.JavaPlugin

class AdvancedCrashPaper : JavaPlugin() {
    override fun onLoad() {
        // Activate Instance
        instance = this
        // CommandAPI (Load)
        componentLogger.info("[CommandAPI] Loading ...")
        CommandAPI.onLoad(
            CommandAPIBukkitConfig(this)
                .usePluginNamespace()
                .shouldHookPaperReload(true)
                .silentLogs(true)
        )
        componentLogger.info("[CommandAPI] Loading commands ...")
        CrashCommand.register()
        // TempCrashCommand.register()
        // PermaCrashCommand.register()
        componentLogger.info("[CommandAPI] Loaded commands.")
        componentLogger.info("[CommandAPI] Loaded.")
    }

    override fun onEnable() {
        // CommandAPI (Enable)
        componentLogger.info("Enabling CommandAPI ...")
        CommandAPI.onEnable()
        componentLogger.info("Enabled CommandAPI.")
    }

    override fun onDisable() {
        componentLogger.warn("Cleaning AdvancedCrash ...")
        // CommandAPI (Disable)
        componentLogger.warn("Disabling CommandAPI ...")
        CommandAPI.onDisable()
        componentLogger.info("Disabled CommandAPI.")

        componentLogger.warn("AdvancedCrash is now cleaned. (Disabled)")
        instance = null
    }


    fun disablePlugin() {
        server.pluginManager.disablePlugin(this)
    }

    fun disablePlugin(plugin: JavaPlugin) {
        server.pluginManager.disablePlugin(plugin)
    }

    companion object {
        @Volatile
        private var instance: AdvancedCrashPaper? = null

        @Synchronized
        fun getInstance(): AdvancedCrashPaper {
            if (instance != null) {
                return instance as AdvancedCrashPaper
            }

            throw IllegalStateException(ErrorData.ACCESS_PLUGIN_INSTANCE_FAILED)
        }
    }
}
