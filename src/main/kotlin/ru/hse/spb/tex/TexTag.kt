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

@DslMarker
annotation class TexCommandMarker

/**
 * Superclass for all TeX tags.
 */
@TexCommandMarker
abstract class TexTag(open val kind: TagKind, open val tagName: String) {

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
        if (kind == TagKind.ENVIRONMENT) {
            if (newIndentForEnvironment)
                indent++
            append("\\begin{$tagName}")
        }
        indent++
        block()
        indent--
        if (kind == TagKind.ENVIRONMENT) {
            if (newIndentForEnvironment)
                indent--
            append("\\end{$tagName}")
        }
    }

    override fun toString() = documentBuilder.toString()

    companion object {
        private val documentBuilder = StringBuilder()
        private var indent = 0
    }
}