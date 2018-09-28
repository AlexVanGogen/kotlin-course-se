package ru.hse.spb

import ru.hse.spb.tex.document

fun main(args: Array<String>) {
    println(document {
        documentClass("beamer")
        usePackage("babel", "russian")
        frame(frameTitle = "frame title") {
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
        }
        frame(frameTitle = "frame title 2") {

        }
    }.toString())
}