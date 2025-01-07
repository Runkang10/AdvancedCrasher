package io.github.runkang10.AdvancedCrash.paper.api

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextDecoration
import net.kyori.adventure.title.Title
import java.time.Duration

class CrashTitle private constructor() {
    companion object {
        val DEFAULT_TITLE: Title = Title.title(
            Component.text("Oops!").color(NamedTextColor.RED).decorate(TextDecoration.BOLD),
            Component.text("You have been crashed!").color(NamedTextColor.YELLOW),
            Title.Times.times(
                Duration.ofSeconds(1), Duration.ofSeconds(3), Duration.ofSeconds(1)
            )
        )

        fun generate(title: String, content: String) = Title.title(
            Component.text(title).color(NamedTextColor.RED).decorate(TextDecoration.BOLD),
            Component.text(content).color(NamedTextColor.YELLOW),
            Title.Times.times(
                Duration.ofSeconds(1), Duration.ofSeconds(3), Duration.ofSeconds(1)
            )
        )
    }
}