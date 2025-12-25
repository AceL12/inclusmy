package com.gitlab.candicey.inclumsy.command

import com.gitlab.candicey.inclumsy.gui.SettingsGui
import com.gitlab.candicey.zenithcore.versioned.v1_8.command.CommandInfo
import com.gitlab.candicey.zenithcore.versioned.v1_8.helper.GuiScreenHelper

@CommandInfo("gui", "g", "settings", "setting", "st")
object GuiCommand : Entry() {
    override fun execute(args: List<String>, rawMessage: String, previousCommandName: List<String>) {
        GuiScreenHelper.addGuiOpenQueue(SettingsGui())
    }
}