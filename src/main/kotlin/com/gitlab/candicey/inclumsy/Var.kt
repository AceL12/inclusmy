package com.gitlab.candicey.inclumsy

import com.gitlab.candicey.zenithcore.config.Config
import com.gitlab.candicey.zenithcore.config.ConfigManager
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import com.gitlab.candicey.inclumsy.config.InclumsyConfig
import com.gitlab.candicey.inclumsy.extension.addInclumsyPrefix
import com.gitlab.candicey.inclumsy.packet.DelayedPacket
import com.gitlab.candicey.zenithcore.versioned.v1_8.command.CommandInitialisationData
import com.gitlab.candicey.zenithcore.versioned.v1_8.command.CommandManager
import java.util.concurrent.ConcurrentLinkedQueue

val LOGGER: Logger = LogManager.getLogger("Inclumsy")

val configManager: ConfigManager by lazy {
    ConfigManager(
        mutableMapOf(
            "inclumsy" to Config(resolveConfigPath("inclumsy.json"), InclumsyConfig()),
        )
    )
}
val inclumsyConfig: Config<InclumsyConfig> by lazy { configManager.getConfig<InclumsyConfig>()!! }

val commandInitialisationData: CommandInitialisationData by lazy {
    CommandInitialisationData(
        listOf("inclumsy"),
        "/",
        String::addInclumsyPrefix
    )
}
val commandManager: CommandManager by lazy { CommandManager(commandInitialisationData) }

var delayedPacketList: ConcurrentLinkedQueue<DelayedPacket> = ConcurrentLinkedQueue()