package com.gitlab.candicey.inclumsy.packet

import com.gitlab.candicey.inclumsy.config.delay
import com.gitlab.candicey.inclumsy.config.enabled
import net.minecraft.network.Packet

data class DelayedPacket(
    val packet: Packet<*>,
    val time: Long = System.currentTimeMillis(),
) {
    fun isExpired(): Boolean = System.currentTimeMillis() > time + (if (enabled) delay else 0)
}
