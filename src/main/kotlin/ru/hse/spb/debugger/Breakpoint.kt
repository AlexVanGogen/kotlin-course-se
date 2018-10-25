package ru.hse.spb.debugger

import ru.hse.spb.interpreter.ASTFactory
import ru.hse.spb.interpreter.ast.*

class Breakpoint(val lineNumber: Int, private val _condition: String? = null) {

    val condition: ASTElement? by lazy {
        _condition?.let { ASTFactory.fromString(it) }
    }

    val conditionRepresentation: String? = _condition

    fun isConditional() = _condition != null

    override fun equals(other: Any?): Boolean {
        if (other !is Breakpoint) {
            return false
        }
        return lineNumber == other.lineNumber
    }

    override fun hashCode(): Int {
        return lineNumber
    }
}