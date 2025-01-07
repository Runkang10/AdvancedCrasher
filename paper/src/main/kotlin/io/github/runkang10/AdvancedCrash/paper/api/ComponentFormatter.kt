package io.github.runkang10.AdvancedCrash.paper.api

import me.clip.placeholderapi.PlaceholderAPI
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.minimessage.MiniMessage
import org.bukkit.entity.Player

/**
 *  Convert String, MiniMessage, and PlaceholderAPI to Component or String.
 *
 *  **Requirements**
 *  - PlaceholderAPI (Required if you want to convert from Placeholder to String)
 *  - AdventureAPI (It should be included on every Paper and Paper forks servers)
 */
class ComponentFormatter private constructor() {
    companion object {
        private fun papi(player: Player, content: String): String {
            return try {
                PlaceholderAPI.setPlaceholders(player, content)
            } catch (_: Exception) {
                content
            }
        }

        private fun mm(content: String): Component = MiniMessage.miniMessage().deserialize(content)

        /**
         *  Convert MiniMessage and Placeholders string to Component.
         *  @param player Bukkit Player class.
         *  @param content String with MiniMessage and Placeholders format.
         *
         *  @return Component
         */
        fun compileAll(player: Player, content: String): Component = mm(papi(player, content))

        /**
         *  Convert MiniMessage string to Component.
         *  @param content String with MiniMessages format.
         *
         *  @return Component
         */
        fun compileWithMiniMessage(content: String): Component = mm(content)

        /**
         *  Convert PlaceholderAPI to String.
         *  @param player Bukkit Player class.
         *  @param content String with Placeholders format.
         *
         *  @return String
         */
        fun compileWithPlaceholderAPI(player: Player, content: String): String = papi(player, content)

        /**
         *  Convert String to Component.
         *  @param content String
         *
         *  @return Component
         */
        fun compileToComponent(content: String): Component = Component.text(content)
    }
}