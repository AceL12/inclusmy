package com.gitlab.candicey.inclumsy.extension

import com.gitlab.candicey.zenithcore.util.GREY
import com.gitlab.candicey.zenithcore.util.LIGHT_PURPLE
import com.gitlab.candicey.zenithcore.util.YELLOW

fun String.addInclumsyPrefix(): String = "${GREY}[${LIGHT_PURPLE}Inclumsy${GREY}]$YELLOW $this"