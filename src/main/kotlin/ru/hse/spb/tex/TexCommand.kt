package ru.hse.spb.tex

@DslMarker
annotation class TexCommandMarker

/**
 * Superclass of all TeX commands
 */
@TexCommandMarker
abstract class TexCommand(open val commandName: String) {

    fun append(text: String) {
        documentBuilder.appendln("${"  ".repeat(indent)}$text")
    }

    fun appendNoNewline(text: String) {
        documentBuilder.append("${"  ".repeat(indent)}$text")
    }

    fun appendToLine(text: String) {
        documentBuilder.appendln(text)
    }

    fun nested(block: () -> Unit) {
        indent++
        block()
        indent--
    }

    override fun toString() = documentBuilder.toString()

    companion object {
        private val documentBuilder = StringBuilder()
        private var indent = 0
    }
}