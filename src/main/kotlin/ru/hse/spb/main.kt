package ru.hse.spb

import ru.hse.spb.tex.AlignmentKind
import ru.hse.spb.tex.TagKind
import ru.hse.spb.tex.document

fun main(args: Array<String>) {
    println(document {
        documentClass("beamer")
        usePackage("babel")
        frame("frame title", "arg1" to "v1", "arg2" to "v2") {
            itemize {
                item { +"item 1" }
                item {
                    itemize {
                        item { +"subitem 1" }
                        item { +"subitem 2" }
                        item { +"subitem 3" }
                    }
                }
                item { +"item 3" }
                item {
                    +"We have the following formula:"
                    math("x=3y^2")
                }
            }
            alignment(AlignmentKind.RIGHT) {
                +"Lorem ipsum dolor sit amet, blablabla"
            }
        }
        frame(frameTitle = "frame title 2") {
            customTag(TagKind.ENVIRONMENT, "pyglist", "language" to "kotlin", "encoding" to "utf8") {
                +"""
                    val x = 1
                    println(x + 2)
                """.trimIndent()
            }
        }
    }.toString())
}