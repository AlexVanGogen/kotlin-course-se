package ru.hse.spb.tex

/**
 * Any tag that can contain text/formula/enumeration/etc.
 */
abstract class ContentHolderTag(
        override val kind: TagKind,
        override val tagName: String,
        override val params: Array<out NamedParameter> = arrayOf()
): TexTag(kind, tagName, params) {

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

    fun customTag(kind: TagKind, name: String, vararg params: NamedParameter, init: CustomTag.() -> Unit) {
        when (kind) {
            TagKind.COMMAND -> {
                val customCommand = CustomCommand(name, params)
                customCommand.nested { customCommand.init() }
            }
            TagKind.ENVIRONMENT -> {
                val customEnvironment = CustomEnvironment(name, params)
                customEnvironment.nested { customEnvironment.init() }
            }
        }
    }

    operator fun String.unaryPlus() {
        split("\n").forEach { append(it) }
    }
}

abstract class EnumerableTag(override val tagName: String): TexTag(TagKind.ENVIRONMENT, tagName) {
    fun item(init: Item.() -> Unit) {
        val item = Item()
        item.nested { item.init() }
    }
}

enum class AlignmentKind {
    LEFT,
    CENTER,
    RIGHT
}

class Alignment(override val tagName: String): TexTag(TagKind.ENVIRONMENT, tagName) {
    operator fun String.unaryPlus() {
        append(this)
    }
}

class Frame(val frameTitle: String, override val params: Array<out NamedParameter>): ContentHolderTag(TagKind.ENVIRONMENT, "frame", params)

class ItemContainer(override val tagName: String): EnumerableTag(tagName)

class Item: ContentHolderTag(TagKind.COMMAND, "item")

/**
 * According to requirements to DSL, `documentclass` and `usepackage` tags must be used
 * inside block for `document` tag, but in TeX `\documentclass` and `\usepackage` tahs
 * are declared outside `\begin{document}...\end{document}` environment.
 */
fun document(init: Document.() -> Unit): Document {
    val document = Document()
    document.init()
    document.append("\\end{${document.tagName}}")
    return document
}