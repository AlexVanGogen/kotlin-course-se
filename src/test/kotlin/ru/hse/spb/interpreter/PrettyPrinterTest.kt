package ru.hse.spb.interpreter

import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import ru.hse.spb.interpreter.represent.PrettyPrintingVisitor
import java.io.ByteArrayOutputStream
import java.io.PrintStream

/**
 * Checks pretty-printing for correctness.
 * Moreover, correctness of pretty-printing partially proves correctness of
 * parsing, because the are different printing algorithms for different
 * language elements (e.g. blocks inside if/while statements, or operation priorities).
 */
class PrettyPrinterTest {

    @AfterEach
    internal fun tearDown() {
        byteArrayOutputStream.reset()
    }

    @Test
    internal fun `test expression`() {
        val code = """
            1+2 * (  3  -   -4)  /  ( 5  %   6   <=   -18 )  &&   2  == 3
        """.trimIndent()
        val expectedCode = """
            (1 + ((2 * (3 - -4)) / ((5 % 6) <= -18))) && (2 == 3)
        """.trimIndent()
        assertEquals(expectedCode, prettyPrint(code))
    }

    @Test
    internal fun `test statements`() {
        val code = """
            var   x    =1
            while    (x   <30)                                  {

if (x > 10) { println(2,3,4) } else { println(2*3*4)
 }
    x = x * 2
            }


                                return      x
        """.trimIndent()
        val expectedCode = """
            var x = 1
            while (x < 30) {
                if (x > 10) {
                    println(2, 3, 4)
                } else {
                    println((2 * 3) * 4)
                }
                x = x * 2
            }
            return x
        """.trimIndent()
        assertEquals(expectedCode, prettyPrint(code))
    }

    @Test
    internal fun `test functions`() {
        val code = """
            fun        plusplus   (x   ,y,      z     ) {
                return x  + y+z
                }



            println (plusplus(2, 3, 5))
        """.trimIndent()
        val expectedCode = """
            fun plusplus(x, y, z) {
                return (x + y) + z
            }
            println(plusplus(2, 3, 5))
        """.trimIndent()
        assertEquals(expectedCode, prettyPrint(code))
    }

    private fun prettyPrint(code: String): String {
        val file = ASTFactory.fromString(code)
        file.accept(PrettyPrintingVisitor())
        return byteArrayOutputStream.toString().trim()
    }

    companion object {
        private val commonOutputStream = System.out
        private val byteArrayOutputStream = ByteArrayOutputStream()

        @BeforeAll
        @JvmStatic
        private fun redirectOutput() {
            val printStream = PrintStream(byteArrayOutputStream)
            System.setOut(printStream)
        }

        @AfterAll
        @JvmStatic
        private fun restoreOutput() {
            System.out.flush()
            System.setOut(commonOutputStream)
        }
    }
}