package com.gitlab.candicey.inclumsy.config

import com.gitlab.candicey.zenithcore.versioned.v1_8.gui.ConfigProperties

data class InclumsyConfig(
    @ConfigProperties.Shadow.Method(CONFIG_VALUES_KT_CLASS_NAME, "setEnabled")
    @ConfigProperties.DisplayText("Enable Inclumsy")
    var enabled: Boolean = false,

    @ConfigProperties.Shadow.Method(CONFIG_VALUES_KT_CLASS_NAME, "setDelay")
    @ConfigProperties.DisplayText("Delay (ms)")
    @ConfigProperties.Text.Number.Min(0.0f)
    var delay: Int = 0,

    @ConfigProperties.Shadow.Method(CONFIG_VALUES_KT_CLASS_NAME, "setDelayChangeStep")
    @ConfigProperties.DisplayText("Delay Increment/Decrement Step (ms)")
    @ConfigProperties.Text.Number.Min(0.0f)
    var delayChangeStep: Int = 25,

    @ConfigProperties.Shadow.Method(CONFIG_VALUES_KT_CLASS_NAME, "setNotifyInChat")
    @ConfigProperties.DisplayText("Notify in Chat")
    var notifyInChat: Boolean = true
)
