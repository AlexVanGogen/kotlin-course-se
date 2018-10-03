package ru.hse.spb.tex

/**
 * Any command that can contain text/formula/enumeration/etc.
 */
abstract class ContentHolderCommand(override val commandName: String): TexCommand(commandName) {

    fun itemize(init: ItemContainer.() -> Unit) {
        val itemContainer = ItemContainer("itemize")
        itemContainer.nested { itemContainer.init() }
    }

    fun enumerate(init: ItemContainer.() -> Unit) {
        val itemContainer = ItemContainer("enumerate")
        itemContainer.nested { itemContainer.init() }
    }

    fun math(formula: String) {
        append("$$formula$")
    }

    fun alignment(kind: AlignmentKind, init: Alignment.() -> Unit) {
        val texAlignmentCommand = when (kind) {
            AlignmentKind.LEFT -> "flushleft"
            AlignmentKind.CENTER -> "center"
            AlignmentKind.RIGHT -> "flushright"
        }
        val alignment = Alignment(texAlignmentCommand)
        alignment.nested { alignment.init() }
    }

    operator fun String.unaryPlus() {
        append(this)
    }
}

abstract class EnumerableCommand(override val commandName: String): TexCommand(commandName) {
    fun item(init: Item.() -> Unit) {
        val item = Item()
        item.append("\\${item.commandName}")
        item.nested(scoped = false) { item.init() }
    }
}

enum class AlignmentKind {
    LEFT,
    CENTER,
    RIGHT
}

class Alignment(override val commandName: String): TexCommand(commandName) {
    operator fun String.unaryPlus() {
        append(this)
    }
}

class Frame(val frameTitle: String): ContentHolderCommand("frame")

class ItemContainer(override val commandName: String): EnumerableCommand(commandName)

class Item: ContentHolderCommand("item")

/**
 * According to requirements to DSL, `documentclass` and `usepackage` commands must be used
 * inside block for `document` command, but in TeX `\documentclass` and `\usepackage` commands
 * are declared outside `\begin{document}...\end{document}` frame.
 */
fun document(init: Document.() -> Unit): Document {
    val document = Document()
    document.init()
    document.append("\\end{${document.commandName}}")
    return document
}