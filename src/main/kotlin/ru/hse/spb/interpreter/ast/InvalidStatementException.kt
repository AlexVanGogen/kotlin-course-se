package ru.hse.spb.interpreter.ast

import org.antlr.v4.runtime.Token
import ru.hse.spb.interpreter.getLocation

class InvalidStatementException(token: Token)
    : ASTException("Error at ${token.getLocation()}: invalid ${token.text} statement")