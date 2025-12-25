package com.gitlab.candicey.inclumsy.helper

import com.gitlab.candicey.inclumsy.config.delay
import com.gitlab.candicey.inclumsy.config.enabled
import com.gitlab.candicey.zenithcore.versioned.v1_8.mc

object PingHelper {
    /*const val CALCULATION_DELAY = 5000L
    const val TICK_LIMIT = 5 * 20 // 5 seconds

    var ping = -1

    var tick = 0

    private var lastServerIp = ""
    private var lastServerPort = -1*/

    fun getEstimatedPingText(): String {
        /*return if (ping == -1) {
            "Calculating..."
        } else {
            (ping + (if (enabled) delay else 0).toInt()).toString() + "ms"
        }*/

        return "${if (enabled) getServerPing() + delay else getServerPing()}ms"
    }

    private fun getServerPing(): Int = mc.netHandler.getPlayerInfo(mc.thePlayer.uniqueID).responseTime

    /*@SubscribeEvent
    fun onServerConnect(event: ServerConnectEvent) {
        lastServerIp = event.ip
        lastServerPort = event.port
    }

    @SubscribeEvent
    fun onTick(event: TickEvent.Pre) {
        if (tick < TICK_LIMIT) {
            return
        }

        tick = 0
        ping = -1

        returnIf(!enabled)
        returnIf(lastServerIp == "")
        returnIf(lastServerPort == -1)

        val startTime = System.currentTimeMillis()

        val socket = Socket()
        socket.connect(InetSocketAddress(lastServerIp, lastServerPort), 3000)

        ping = (System.currentTimeMillis() - startTime).toInt()
    }*/
}