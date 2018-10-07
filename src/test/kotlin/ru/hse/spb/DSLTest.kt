package ru.hse.spb

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import ru.hse.spb.tex.AlignmentKind
import ru.hse.spb.tex.TagKind
import ru.hse.spb.tex.TexException
import ru.hse.spb.tex.document

class DSLTest {

    @Test
    internal fun `test empty document`() {
        val document = document {
            documentClass("beamer")
        }
        val expected = """
            \documentclass{beamer}
            \begin{document}
            \end{document}
        """.trimIndent()
        assertEquals(expected, document.toString().trim())
    }

    @Test
    internal fun `test document with class and packages`() {
        val document = document {
            documentClass("beamer")
            usePackage(!"babel", "hott" to "nat")
        }
        val expected = """
            \documentclass{beamer}
            \usepackage{babel}
            \usepackage[nat]{hott}
            \begin{document}
            \end{document}
        """.trimIndent()
        assertEquals(expected, document.toString().trim())
    }

    @Test
    internal fun `test document with some tags`() {
        val document = document {
            documentClass("beamer")
            usePackage(!"babel", "fontenc" to "T1", "inputenc" to "utf8", !"pythontex")
            frame("frame title", "arg1" to "v1", "arg2" to "v2") {
                itemize {
                    item { +"item 1" }
                    item {
                        itemize {
                            for (i in 1..5) {
                                item { +"subitem $i" }
                            }
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
                customTag(TagKind.ENVIRONMENT, "pyglist", "language" to "python", "encoding" to "utf8") {
                    +"""
                    val x = 1
                    println(x + 2)
                """.trimIndent()
                }
            }
        }

        val expected = """
            \documentclass{beamer}
            \usepackage{babel}
            \usepackage[T1]{fontenc}
            \usepackage[utf8]{inputenc}
            \usepackage{pythontex}
            \begin{document}
              \begin{frame}[arg1=v1, arg2=v2]
                \frametitle{frame title}
                \begin{itemize}
                  \item{
                    item 1
                  }
                  \item{
                    \begin{itemize}
                      \item{
                        subitem 1
                      }
                      \item{
                        subitem 2
                      }
                      \item{
                        subitem 3
                      }
                      \item{
                        subitem 4
                      }
                      \item{
                        subitem 5
                      }
                    \end{itemize}
                  }
                  \item{
                    item 3
                  }
                  \item{
                    We have the following formula:
                    ${'$'}x=3y^2${'$'}
                  }
                \end{itemize}
                \begin{flushright}
                  Lorem ipsum dolor sit amet, blablabla
                \end{flushright}
              \end{frame}
              \begin{frame}
                \frametitle{frame title 2}
                \begin{pyglist}[language=python, encoding=utf8]
                  val x = 1
                  println(x + 2)
                \end{pyglist}
              \end{frame}
            \end{document}
        """.trimIndent()

        assertEquals(expected, document.toString().trim())
    }

    @Test
    internal fun `test document with incorrect placement of usePackage`() {
        assertThrows(TexException::class.java) {
            document {
                frame("title") {
                    +"text"
                }
                usePackage(!"babel")
            }
        }
    }

    @Test
    internal fun `test document with incorrect placement of documentclass`() {
        assertThrows(TexException::class.java) {
            document {
                usePackage(!"babel")
                frame("title") {
                    +"text"
                }
                documentClass("beamer")
            }
        }
    }

    @Test
    internal fun `test document with duplicated usePackage`() {
        assertThrows(TexException::class.java) {
            document {
                usePackage(!"babel")
                usePackage(!"russian")
                frame("title") {
                    +"text"
                }
            }
        }
    }

    @Test
    internal fun `test document with duplicated documentclass`() {
        assertThrows(TexException::class.java) {
            document {
                documentClass("beamer")
                documentClass("article")
                frame("title") {
                    +"text"
                }
            }
        }
    }

    @Test
    internal fun `test document without documentclass`() {
        assertThrows(TexException::class.java) {
            document {
                usePackage(!"babel")
                frame("title") {
                    +"text"
                }
            }
        }
    }
}