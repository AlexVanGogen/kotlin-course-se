package ru.hse.spb.interpreter

import org.antlr.v4.runtime.Token

internal fun Token.getLocation() = "$line:${charPositionInLine + 1}"