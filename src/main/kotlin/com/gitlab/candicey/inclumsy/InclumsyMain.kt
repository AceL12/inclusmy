package com.gitlab.candicey.inclumsy

import com.gitlab.candicey.inclumsy.command.DelayChangeStepCommand
import com.gitlab.candicey.inclumsy.command.DelayCommand
import com.gitlab.candicey.inclumsy.command.GuiCommand
import com.gitlab.candicey.inclumsy.config.delay
import com.gitlab.candicey.inclumsy.config.delayChangeStep
import com.gitlab.candicey.inclumsy.config.enabled
import com.gitlab.candicey.inclumsy.config.notifyInChat
import com.gitlab.candicey.inclumsy.extension.addInclumsyPrefix
import com.gitlab.candicey.inclumsy.hook.NetworkManagerHook
import com.gitlab.candicey.inclumsy.packet.DelayedPacketSender
import com.gitlab.candicey.zenithcore.extension.add
import com.gitlab.candicey.zenithcore.extension.registerHook
import com.gitlab.candicey.zenithcore.util.DARK_AQUA
import com.gitlab.candicey.zenithcore.util.GREEN
import com.gitlab.candicey.zenithcore.util.RED
import com.gitlab.candicey.zenithcore.util.YELLOW
import com.gitlab.candicey.zenithcore.versioned.v1_8.extension.addChatMessage
import com.gitlab.candicey.zenithcore.versioned.v1_8.extension.toChatComponent
import com.gitlab.candicey.zenithcore.versioned.v1_8.hook.LocaleHook
import com.gitlab.candicey.zenithcore.versioned.v1_8.keybind.KeyBind
import com.gitlab.candicey.zenithcore.versioned.v1_8.keybind.KeyBindManager
import com.gitlab.candicey.zenithloader.ZenithLoader
import net.weavemc.api.ModInitializer
import net.weavemc.api.event.EventBus
import net.weavemc.loader.InjectionHandler
import org.lwjgl.input.Keyboard
import java.lang.instrument.Instrumentation

class InclumsyMain : ModInitializer {
    override fun preInit(inst: Instrumentation) {
        info("Initialising...")

        ZenithLoader.loadDependencies("inclumsy", inst)

        arrayOf(
            NetworkManagerHook,
        ).forEach(InjectionHandler::registerHook)

        EventBus.subscribe(DelayedPacketSender)

        configManager.init()

        commandManager.registerCommand(
            GuiCommand,
            DelayCommand,
            DelayChangeStepCommand,
        )

        initKeyBinds()
        initLocalisation()

        info("Initialised.")
    }

    private fun initKeyBinds() {
        fun notifyChat(message: String) {
            if (notifyInChat) {
                message
                    .addInclumsyPrefix()
                    .toChatComponent()
                    .addChatMessage()
            }
        }

        val keyToggle = KeyBind("inclumsy.key.toggle", Keyboard.KEY_I, "Inclumsy") { _, _ ->
            enabled = !enabled

            notifyChat("${if (enabled) "${GREEN}Enabled" else "${RED}Disabled"}${YELLOW} ping spoofing.")
        }

        val keyIncreasePing = KeyBind("inclumsy.key.ping.increase", Keyboard.KEY_NONE, "Inclumsy") { _, _ ->
            val oldDelay = delay
            delay += delayChangeStep

            notifyChat("Ping: $DARK_AQUA$oldDelay$YELLOW -> $DARK_AQUA$delay")
        }

        val keyDecreasePing = KeyBind("inclumsy.key.ping.decrease", Keyboard.KEY_NONE, "Inclumsy") { _, _ ->
            val oldDelay = delay
            delay = (delay - delayChangeStep).coerceAtLeast(0)

            notifyChat("Ping: $DARK_AQUA$oldDelay$YELLOW -> $DARK_AQUA$delay")
        }

        KeyBindManager.keyBindings.add(
            keyToggle,
            keyIncreasePing,
            keyDecreasePing,
        )
    }

    private fun initLocalisation() {
        LocaleHook.messages.add(
            "inclumsy.key.toggle" to "Toggle Inclumsy",
            "inclumsy.key.ping.increase" to "Increase Ping",
            "inclumsy.key.ping.decrease" to "Decrease Ping",
        )
    }
}