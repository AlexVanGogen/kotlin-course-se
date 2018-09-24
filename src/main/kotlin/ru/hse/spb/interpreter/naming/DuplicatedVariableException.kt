package ru.hse.spb.interpreter.naming

import ru.hse.spb.interpreter.ast.Identifier

class DuplicatedVariableException(identifier: Identifier)
    : NamingException("Error at ${identifier.getLocation()}: function with name '${identifier.name}' already exists")