package ru.hse.spb.interpreter.naming

import ru.hse.spb.interpreter.ast.FunctionCallExpression
import ru.hse.spb.interpreter.ast.FunctionDeclaration
import ru.hse.spb.interpreter.ast.Identifier

class Namespace(
        val enclosingNamespace: Namespace? = null
) {
    private val functionsTable = FunctionsTable()
    private val variablesTable = VariablesTable()

    fun addFunction(declaration: FunctionDeclaration) {
        if (functionContainsInCurrentNamespace(declaration))
            throw DuplicatedFunctionException(declaration.identifier)
        functionsTable.add(declaration)
    }

    fun addVariable(variable: Identifier, value: Int) {
        if (variableContainsInCurrentNamespace(variable))
            throw DuplicatedVariableException(variable)
        variablesTable.add(variable, value)
    }

    fun getFunctionByCall(functionCall: FunctionCallExpression): FunctionDeclaration {
        val declaration = getFunctionDeclarationOrNullFor(functionCall)
        if (declaration == null || declaration.signature != functionCall.signature)
            throw FunctionNotFoundException(functionCall)
        return declaration
    }

    fun getVariableValue(variable: Identifier): Int {
        return getVariableValueOrNull(variable) ?: throw VariableNotFoundException(variable)
    }

    fun updateVariableValue(variable: Identifier, value: Int) {
        if (variable in variablesTable)
            variablesTable.add(variable, value)
        else
            enclosingNamespace?.updateVariableValue(variable, value)
    }

    operator fun contains(declaration: FunctionDeclaration): Boolean
            = functionContainsInCurrentNamespace(declaration) || containsInEnclosingNamespaces(declaration)

    operator fun contains(variable: Identifier): Boolean
            = variableContainsInCurrentNamespace(variable) || containsInEnclosingNamespaces(variable)

    private fun getFunctionDeclarationOrNullFor(call: FunctionCallExpression): FunctionDeclaration?
            = functionsTable.getFunctionDeclarationOrNullFor(call) ?: enclosingNamespace?.getFunctionDeclarationOrNullFor(call)

    private fun getVariableValueOrNull(variable: Identifier): Int?
            = variablesTable.getVariableValueOrNull(variable) ?: enclosingNamespace?.getVariableValueOrNull(variable)

    private fun variableContainsInCurrentNamespace(variable: Identifier) = variable in variablesTable

    private fun functionContainsInCurrentNamespace(declaration: FunctionDeclaration) = declaration in functionsTable

    private fun containsInEnclosingNamespaces(declaration: FunctionDeclaration) = enclosingNamespace?.contains(declaration) == true

    private fun containsInEnclosingNamespaces(variable: Identifier) = enclosingNamespace?.contains(variable) == true
}