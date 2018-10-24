package ru.hse.spb.interpreter.naming

import ru.hse.spb.interpreter.ast.FunctionCallExpression

class FunctionNotFoundException(functionCall: FunctionCallExpression)
    : NamingException("Error at ${functionCall.identifier.getLocation()}: function ${functionCall.signature} not found")