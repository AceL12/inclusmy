package com.gitlab.candicey.inclumsy.command

import com.gitlab.candicey.zenithcore.util.DARK_AQUA
import com.gitlab.candicey.zenithcore.util.RED
import com.gitlab.candicey.zenithcore.util.YELLOW
import com.gitlab.candicey.inclumsy.config.delayChangeStep
import com.gitlab.candicey.inclumsy.extension.addInclumsyPrefix
import com.gitlab.candicey.zenithcore.versioned.v1_8.command.CommandInfo
import com.gitlab.candicey.zenithcore.versioned.v1_8.extension.addChatMessage
import com.gitlab.candicey.zenithcore.versioned.v1_8.extension.toChatComponent

@CommandInfo("delaychangestep", "delaystep", "dcs", "ds")
object DelayChangeStepCommand : Entry() {
    override fun execute(args: List<String>, rawMessage: String, previousCommandName: List<String>) {
        if (args.isEmpty()) {
            "${RED}Usage: /${previousCommandName.joinToString(" ")} ${aliases.first()} <delay>"
                .addInclumsyPrefix()
                .toChatComponent()
                .addChatMessage()
            return
        }

        val newStep = args[0].toIntOrNull()
        if (newStep == null || newStep < 0) {
            "${RED}Invalid value!"
                .addInclumsyPrefix()
                .toChatComponent()
                .addChatMessage()
            return
        }

        delayChangeStep = newStep
        "${YELLOW}Delay increment/decrement step has been set to ${DARK_AQUA}$delayChangeStep ${YELLOW}ms."
            .addInclumsyPrefix()
            .toChatComponent()
            .addChatMessage()
    }
}