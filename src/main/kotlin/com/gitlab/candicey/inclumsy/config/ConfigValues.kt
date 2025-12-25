package com.gitlab.candicey.inclumsy.config

import com.gitlab.candicey.inclumsy.inclumsyConfig

const val CONFIG_VALUES_KT_CLASS_NAME = "com/gitlab/candicey/inclumsy/config/ConfigValuesKt"

var enabled: Boolean by inclumsyConfig

var delay: Int by inclumsyConfig

var delayChangeStep: Int by inclumsyConfig

var notifyInChat: Boolean by inclumsyConfig