package ru.hse.spb.interpreter.naming

import ru.hse.spb.interpreter.ast.Identifier

class VariableNotFoundException(variable: Identifier)
    : NamingException("Error at ${variable.getLocation()}: variable ${variable.name} not found")