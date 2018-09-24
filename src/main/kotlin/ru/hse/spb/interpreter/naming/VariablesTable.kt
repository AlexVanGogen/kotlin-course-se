package ru.hse.spb.interpreter.naming

import ru.hse.spb.interpreter.ast.Identifier
import ru.hse.spb.interpreter.ast.VariableDeclaration

class VariablesTable {
    val variables: MutableMap<String, Int> = mutableMapOf()

    fun add(variable: Identifier, value: Int) {
        variables[variable.name] = value
    }

    operator fun contains(variable: Identifier) = variables[variable.name] != null

    fun getVariableValueOrNull(variable: Identifier): Int? = variables[variable.name]
}