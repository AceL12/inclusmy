package com.gitlab.candicey.inclumsy

import java.io.File

fun info(message: String) = LOGGER.info("[Inclumsy] $message")

internal fun resolveConfigPath(fileName: String): File {
    val directory = File(System.getProperty("user.home"), ".weave/Inclumsy")
    if (!directory.exists()) {
        directory.mkdirs()
    }
    return File(directory, fileName)
}

