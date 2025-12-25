package com.gitlab.candicey.inclumsy.packet

import net.minecraft.network.INetHandler
import net.minecraft.network.Packet
import com.gitlab.candicey.inclumsy.delayedPacketList
import com.gitlab.candicey.zenithcore.versioned.v1_8.event.TickEvent
import com.gitlab.candicey.zenithcore.versioned.v1_8.mc
import net.weavemc.api.event.SubscribeEvent

object DelayedPacketSender {
    @SubscribeEvent
    fun onTick(event: TickEvent.Pre) {
        val netHandler = mc.thePlayer?.sendQueue?.networkManager?.netHandler ?: return

        while (delayedPacketList.isNotEmpty()) {
            val delayedPacket = delayedPacketList.peek()

            if (!delayedPacket.isExpired()) {
                return
            }

            val packet = delayedPacket.packet as Packet<INetHandler>

            runCatching {
                packet.processPacket(netHandler)
            }

            delayedPacketList.poll()
        }
    }
}