package ru.hse.spb.interpreter.naming

import ru.hse.spb.interpreter.ast.FunctionCallExpression
import ru.hse.spb.interpreter.ast.FunctionDeclaration

class FunctionsTable {
    private val functions: MutableSet<FunctionDeclaration> = mutableSetOf()

    fun add(declaration: FunctionDeclaration) {
        functions.add(declaration)
    }

    operator fun contains(declaration: FunctionDeclaration) = functions.any { it.name == declaration.name }
    operator fun contains(call: FunctionCallExpression) = functions.any { it.name == call.name }

    fun getFunctionDeclarationOrNullFor(call: FunctionCallExpression) = functions.find { it.signature == call.signature }
}