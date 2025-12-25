package com.gitlab.candicey.inclumsy.command

import com.gitlab.candicey.zenithcore.util.DARK_AQUA
import com.gitlab.candicey.zenithcore.util.RED
import com.gitlab.candicey.zenithcore.util.YELLOW
import com.gitlab.candicey.inclumsy.config.delay
import com.gitlab.candicey.inclumsy.extension.addInclumsyPrefix
import com.gitlab.candicey.zenithcore.versioned.v1_8.command.CommandInfo
import com.gitlab.candicey.zenithcore.versioned.v1_8.extension.addChatMessage
import com.gitlab.candicey.zenithcore.versioned.v1_8.extension.toChatComponent

@CommandInfo("delay", "d")
object DelayCommand : Entry() {
    override fun execute(args: List<String>, rawMessage: String, previousCommandName: List<String>) {
        if (args.isEmpty()) {
            "${RED}Usage: /${previousCommandName.joinToString(" ")} ${aliases.first()} <delay>"
                .addInclumsyPrefix()
                .toChatComponent()
                .addChatMessage()
            return
        }

        val newDelay = args[0].toIntOrNull()
        if (newDelay == null || newDelay < 0) {
            "${RED}Invalid delay!"
                .addInclumsyPrefix()
                .toChatComponent()
                .addChatMessage()
            return
        }

        delay = newDelay
        "${YELLOW}Delay has been set to ${DARK_AQUA}$delay ${YELLOW}ms."
            .addInclumsyPrefix()
            .toChatComponent()
            .addChatMessage()
    }
}