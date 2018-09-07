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

private fun ParameterName.matchesType(type: ParameterType) = parametersTable[this] == type || type == ParameterType.ANY

private data class ProcedureDeclaration(
        val name: String,
        val parametersTypes: List<ParameterType>
)

private data class ProcedureInvocation(
        val name: String,
        val parametersNames: List<ParameterName>
) {

    fun matches(declaration: ProcedureDeclaration) =
            name == declaration.name
                    && parametersNames.size == declaration.parametersTypes.size
                    && parametersNames.zip(declaration.parametersTypes).all { (name, type) -> name.matchesType(type) }
}

private fun String.splitByWhitespaces(): List<String> = trim().split("\\s+".toRegex())

private fun String.splitToNameAndParameters(): List<String> =
        replace("[(),]".toRegex(), " ").splitByWhitespaces()

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

private val parametersTable = mutableMapOf<ParameterName, ParameterType>()
private val declaredProcedures = mutableListOf<ProcedureDeclaration>()

private fun solve(inputData: List<String>): List<Int> {
    val line = inputData.iterator()
    val numberOfDeclaredProcedures = line.next().toIntOrNull() ?: throw NumberFormatException()
    repeat(numberOfDeclaredProcedures) { declaredProcedures.add(line.next().toProcedureDeclaration()) }

    val numberOfDeclaredParameters = line.next().toIntOrNull() ?: throw NumberFormatException()
    repeat(numberOfDeclaredParameters) { parametersTable.addNewParameterFrom(line.next()) }

    val numberOfProcedureInvocations = line.next().toIntOrNull() ?: throw NumberFormatException()
    val appropriateProceduresCountForEachInvocation = MutableList(numberOfProcedureInvocations) { 0 }
    repeat(numberOfProcedureInvocations) {
        val nextInvocation = line.next().toProcedureInvocation()
        appropriateProceduresCountForEachInvocation[it] =
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