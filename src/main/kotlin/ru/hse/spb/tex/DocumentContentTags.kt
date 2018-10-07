package ru.hse.spb.tex

/**
 * Any tag that can contain text/formula/enumeration/etc.
 */
@TexCommandMarker
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

@TexCommandMarker
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

@TexCommandMarker
class Alignment(override val tagName: String): TexTag(TagKind.ENVIRONMENT, tagName) {
    operator fun String.unaryPlus() {
        append(this)
    }
}

@TexCommandMarker
class Frame(val frameTitle: String, override val params: Array<out NamedParameter>): ContentHolderTag(TagKind.ENVIRONMENT, "frame", params)

@TexCommandMarker
class ItemContainer(override val tagName: String): EnumerableTag(tagName)

@TexCommandMarker
class Item: ContentHolderTag(TagKind.COMMAND, "item")