package io.github.runkang10.AdvancedCrash.paper.commands

import dev.jorel.commandapi.CommandAPICommand
import dev.jorel.commandapi.CommandPermission
import dev.jorel.commandapi.arguments.PlayerArgument
import dev.jorel.commandapi.arguments.SafeSuggestions
import dev.jorel.commandapi.executors.CommandExecutor
import io.github.runkang10.AdvancedCrash.paper.AdvancedCrashPaper
import io.github.runkang10.AdvancedCrash.paper.api.ComponentFormatter
import io.github.runkang10.AdvancedCrash.paper.api.Crash
import io.github.runkang10.AdvancedCrash.paper.api.CrashTitle
import io.github.runkang10.AdvancedCrash.paper.static.ErrorData
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class CrashCommand private constructor() {
    companion object {
        private val plugin = AdvancedCrashPaper.getInstance()

        private val PREFIX: String
            get() {
                val TEXT = "<bold><gradient:red:aqua>AdvancedCrash</gradient></bold>"
                val HOVER =
                    "<hover:show_text:'<yellow>AdvancedCrasher</yellow>\n<white>Click here to see\nabout AdvancedCrasher'>$TEXT</hover>"
                return "<click:run_command:/advancedcrash info>$HOVER</click>"
            }

        private fun crashTask(target: Player, executor: CommandSender) = try {
            executor.sendMessage(ComponentFormatter.compileWithMiniMessage("$PREFIX <yellow>Executing crash task to '${target.name}' ..."))
            // Execute Crash Task
            Crash.crash(target)
            executor.sendMessage(ComponentFormatter.compileWithMiniMessage("$PREFIX <green>'${target.name}' is now crashed."))
            executor.sendMessage(ComponentFormatter.compileWithMiniMessage("$PREFIX <green>Task executed successfully!"))
        } catch (e: Exception) {
            target.sendMessage(ComponentFormatter.compileWithMiniMessage("<green>You're lucky!"))
            executor.sendMessage(ComponentFormatter.compileWithMiniMessage("$PREFIX <dark_red>Task failed for unexpected reasons!"))
            plugin.componentLogger.error(ErrorData.NMS_ACCESS_FAILED)
        }

        fun register() {
            return CommandAPICommand("crash")
                // Arguments (1 argument -> Player)
                .withArguments(PlayerArgument("target").replaceSafeSuggestions(SafeSuggestions.suggest {
                    plugin.server.onlinePlayers.toTypedArray()
                }))
                // Required permission to use this command (Players requirement)
                .withPermission(CommandPermission.fromString("advancedcrash.crash"))
                .executes(CommandExecutor { sender, args ->
                    val target = args["target"] as Player
                    target.showTitle(CrashTitle.DEFAULT_TITLE)

                    sender.sendMessage(ComponentFormatter.compileWithMiniMessage("$PREFIX Please wait ... (5.5s)"))

                    plugin.server.scheduler.runTaskLaterAsynchronously(plugin, Runnable {
                        crashTask(target, sender)
                    }, 110L)

                    return@CommandExecutor
                })
                // Register
                .register()
        }
    }
}