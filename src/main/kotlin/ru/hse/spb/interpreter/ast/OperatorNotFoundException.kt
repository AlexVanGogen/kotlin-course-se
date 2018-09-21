package ru.hse.spb.interpreter.ast

import org.antlr.v4.runtime.Token
import ru.hse.spb.interpreter.getLocation

class OperatorNotFoundException(operator: Token)
    : ASTException("Error at ${operator.getLocation()}: operator not found: ${operator.text}")