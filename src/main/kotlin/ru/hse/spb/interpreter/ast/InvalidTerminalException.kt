package ru.hse.spb.interpreter.ast

import org.antlr.v4.runtime.tree.TerminalNode
import ru.hse.spb.interpreter.getLocation

class InvalidTerminalException(token: TerminalNode)
    : ASTException("Error at ${token.symbol.getLocation()}: invalid terminal symbol ${token.symbol.text}")