package ru.hse.spb.tex

enum class TagKind {
    /**
     * TeX tags with format "\<name>...", such as \text, \item, etc.
     */
    COMMAND,

    /**
     * TeX tags that wrap text with \begin{<name>} ... \end{<name>} construction.
     */
    ENVIRONMENT
}

typealias NamedParameter = Pair<String, Any>

@DslMarker
annotation class TexCommandMarker

/**
 * Superclass for all TeX tags.
 */
@TexCommandMarker
abstract class TexTag(open val kind: TagKind, open val tagName: String, open val params: Array<out NamedParameter> = arrayOf()) {

    fun append(text: String) {
        documentBuilder.appendln("${"  ".repeat(indent)}$text")
    }

    fun appendNoNewline(text: String) {
        documentBuilder.append("${"  ".repeat(indent)}$text")
    }

    fun appendToLine(text: String) {
        documentBuilder.appendln(text)
    }

    fun nested(newIndentForEnvironment: Boolean = false, block: () -> Unit) {
        when (kind) {
            TagKind.COMMAND -> nestedCommand(block)
            TagKind.ENVIRONMENT -> nestedEnvironment(newIndentForEnvironment, block)
        }
    }

    override fun toString() = documentBuilder.toString()

    private fun makeParametersList() = if (params.isEmpty()) "" else params.joinToString(", ", "[", "]") { "${it.first}=${it.second}" }

    private fun nestedEnvironment(isNewIndentNeeded: Boolean = false, block: () -> Unit) {
        if (isNewIndentNeeded)
            indent++
        append("\\begin{$tagName}${makeParametersList()}")
        indent++
        block()
        indent--
        append("\\end{$tagName}")
        if (isNewIndentNeeded)
            indent--
    }

    private fun nestedCommand(block: () -> Unit) {
        append("\\$tagName${makeParametersList()}{")
        indent++
        block()
        indent--
        append("}")
    }

    companion object {
        private val documentBuilder = StringBuilder()
        private var indent = 0
    }
}