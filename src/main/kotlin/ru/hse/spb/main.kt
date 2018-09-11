package ru.hse.spb

private class UnexpectedTypeException(override val message: String): IllegalArgumentException(message)

private enum class ParameterType(val typeName: String) {
    INT("int"),
    STRING("string"),
    DOUBLE("double"),
    ANY("T");

    companion object {
        fun getTypeByName(value: String): ParameterType =
            values().find { it.typeName == value } ?: throw UnexpectedTypeException("Unexpected type: $value")
    }
}

private typealias ParameterName = String
private typealias ParametersTable = MutableMap<ParameterName, ParameterType>

private class ProcedureDeclaration(
        val name: String,
        val parametersTypes: List<ParameterType>
)

private class ProcedureInvocation(
        val name: String,
        val parametersNames: List<ParameterName>
)

private val oneOrMoreWhitespacesPattern = "\\s+".toRegex()
private val parametersSeparatingSymbolsPattern = "[(),]".toRegex()

private fun String.splitByWhitespaces(): List<String> = trim().split(oneOrMoreWhitespacesPattern)

private fun String.splitToNameAndParameters(): List<String> =
        replace(parametersSeparatingSymbolsPattern, " ").splitByWhitespaces()

private fun ParametersTable.addNewParameterFrom(parameterDeclarationLine: String) {
    val (type, name) = parameterDeclarationLine.splitByWhitespaces()
    put(name, ParameterType.getTypeByName(type))
}

private fun String.toProcedureDeclaration(): ProcedureDeclaration {
    // Skip `void` keyword
    val procedureAndParametersNames = splitToNameAndParameters().drop(1)
    return ProcedureDeclaration(
            name = procedureAndParametersNames[0],
            parametersTypes = procedureAndParametersNames.drop(1).map { ParameterType.getTypeByName(it) }
    )
}

private fun String.toProcedureInvocation(): ProcedureInvocation {
    val procedureAndParametersNames = splitToNameAndParameters()
    return ProcedureInvocation(
            name = procedureAndParametersNames[0],
            parametersNames = procedureAndParametersNames.drop(1)
    )
}

private fun solve(inputData: List<String>): List<Int> {
    val declaredProcedures = mutableListOf<ProcedureDeclaration>()
    val parametersTable = mutableMapOf<ParameterName, ParameterType>()

    fun ParameterName.matchesType(type: ParameterType) = parametersTable[this] == type || type == ParameterType.ANY

    fun ProcedureInvocation.matches(declaration: ProcedureDeclaration) =
            name == declaration.name
                    && parametersNames.size == declaration.parametersTypes.size
                    && parametersNames.zip(declaration.parametersTypes).all { (name, type) -> name.matchesType(type) }

    val line = inputData.iterator()
    val numberOfDeclaredProcedures = line.next().toInt()
    for (i in 1..numberOfDeclaredProcedures) { declaredProcedures.add(line.next().toProcedureDeclaration()) }

    val numberOfDeclaredParameters = line.next().toInt()
    for (i in 1..numberOfDeclaredParameters) { parametersTable.addNewParameterFrom(line.next()) }

    val numberOfProcedureInvocations = line.next().toInt()
    val appropriateProceduresCountForEachInvocation = MutableList(numberOfProcedureInvocations) { 0 }
    for (i in 1..numberOfProcedureInvocations) {
        val nextInvocation = line.next().toProcedureInvocation()
        appropriateProceduresCountForEachInvocation[i - 1] =
                declaredProcedures.count { declaration -> nextInvocation.matches(declaration) }
    }
    return appropriateProceduresCountForEachInvocation
}

internal fun solve(inputData: String) = solve(inputData.split("\n"))

private fun readInput(): List<String> {
    val data = mutableListOf<String>()
    var nextLine: String? = readLine()
    while (nextLine != null) {
        data.add(nextLine)
        nextLine = readLine()
    }
    return data
}

fun main(args: Array<String>) {
    println(solve(readInput()).joinToString("\n"))
}