package com.gitlab.candicey.inclumsy.gui

import com.gitlab.candicey.inclumsy.inclumsyConfig
import net.minecraft.client.renderer.GlStateManager
import com.gitlab.candicey.inclumsy.helper.PingHelper
import com.gitlab.candicey.zenithcore.versioned.v1_8.extension.drawCenteredString
import com.gitlab.candicey.zenithcore.versioned.v1_8.gui.ConfigGui
import com.gitlab.candicey.zenithcore.versioned.v1_8.gui.ConfigOptionsGenerator

class SettingsGui : ConfigGui("Inclumsy Settings", ConfigOptionsGenerator.generateConfigOptions(inclumsyConfig.config)) {
    override fun drawScreen(mouseX: Int, mouseY: Int, partialTicks: Float) {
        try {
            super.drawScreen(mouseX, mouseY, partialTicks)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        GlStateManager.pushMatrix()
        val scale = 1.25f
        GlStateManager.scale(scale, scale, scale)
        fontRendererObj.drawCenteredString("Estimated Ping: ${PingHelper.getEstimatedPingText()}", (width / 2) / scale, (height - 20) / scale, 0xFFFFFF)
        GlStateManager.popMatrix()
    }
}