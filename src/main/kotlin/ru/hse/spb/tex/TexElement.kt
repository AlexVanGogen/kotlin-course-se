package ru.hse.spb.tex

/**
 * Any command that can contain text/formula/enumeration/etc.
 */
abstract class ContentHolderCommand(override val commandName: String): TexCommand(commandName) {

    fun itemize(init: ItemContainer.() -> Unit) {
        val itemContainer = ItemContainer()
        itemContainer.append("\\begin{itemize}")
        itemContainer.nested { itemContainer.init() }
        itemContainer.append("\\end{itemize}")
    }

    fun enumerate(init: ItemContainer.() -> Unit) {
        val itemContainer = ItemContainer()
        itemContainer.append("\\begin{enumerate}")
        itemContainer.nested { itemContainer.init() }
        itemContainer.append("\\end{enumerate}")
    }

    fun math(formula: String) {
        append("$$formula$")
    }

    operator fun String.unaryPlus() {
        append(this)
    }
}

class Frame(val frameTitle: String): TexCommand("frame") {

    fun itemize(init: ItemContainer.() -> Unit) {
        val itemContainer = ItemContainer()
        itemContainer.append("\\begin{itemize}")
        itemContainer.nested { itemContainer.init() }
        itemContainer.append("\\end{itemize}")
    }
}

class ItemContainer: TexCommand("itemize") {

    fun item(init: Item.() -> Unit) {
        val item = Item()
        item.append("\\item ")
        item.nested { item.init() }
    }
}

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