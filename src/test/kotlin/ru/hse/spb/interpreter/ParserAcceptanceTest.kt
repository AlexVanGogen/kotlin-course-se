package ru.hse.spb.interpreter

import org.antlr.v4.runtime.RecognitionException
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import ru.hse.spb.interpreter.errors.LexerException

/**
 * Tests in this class are like `is parser in solidarity with grammar
 * and accepts the same programs`. There is no information about how
 * parser recognizes input, but only whether it recognizes that input
 * or not.
 */
class ParserAcceptanceTest {

    @Test
    fun `parser accepts an empty program`() {
        `parser must accept program`("")
    }

    @Test
    fun `parser accepts simple binary operations`() {
        `parser must accept program`(
                """
                    1 + -1
                    2-2
                    3* 3
                    4 / 4
                    5 %5
                    6 < 6
                    7 <= 7
                    8 > a
                    9 >= x
                    -10 == 11
                    p != 12
                    13 && 14
                    13 || 14
                """.trimIndent()
        )
    }

    @Test
    fun `parser accepts expressions`() {
        `parser must accept program`(
                """
                    1 + (2-3) * 4 + 5
                    3 < (2 && 18) < 4 < 5 > 4 > 3 > 2 > 1
                    5 % 0 % 9420958235088041
                """.trimIndent()
        )
    }

    @Test
    fun `parser accepts functions`() {
        `parser must accept program`(
                """
                    fun x(i, j, k) {
                        return i + j + k
                    }

                    fun y(i) {
                        println(i)
                    }

                    y(x(1, 2, 3))
                """.trimIndent()
        )
    }

    @Test
    fun `parser accepts nested blocks`() {
        `parser must accept program`(
                """
                    fun x(i, j, k) {
                        fun y(z) {

                            while (z < k) {
                                z = z * 2
                            }

                            return z + i + j
                        }

                        return y(k) < (k)
                    }
                """.trimIndent()
        )
    }

    @Test
    fun `parser accepts statements`() {
        `parser must accept program`(
                """
                    if (x < 2) {
                        println(x)
                    }

                    if (y < 2) {
                        println(y)
                    } else {
                        println(2)
                    }

                    while (z < 2) {
                        println(z)
                        z = z + 1
                    }
                """.trimIndent()
        )
    }

    /**
     * Parser will accept the code below, since it's grammatically correct.
     */
    @Test
    fun `parser accepts variable assignments`() {
        `parser must accept program`(
                """
                    x = 5
                    y = 1
                    x < y
                """.trimIndent()
        )
    }

    @Test
    fun `parser does not accept uncompleted expressions`() {
        val incorrectCodes = listOf(
                """
                    1 +
                """.trimIndent(),
                """
                    * 2
                """.trimIndent(),
                """
                    3 + 4 - 5 %
                """.trimIndent()
        )
        for (incorrectCode in incorrectCodes) {
            assertThrows(RecognitionException::class.java) {
                `parser must not accept program`(incorrectCode)
            }
        }
    }

    @Test
    fun `parser does not accept programs with unsupported symbols`() {
        assertThrows(LexerException::class.java) {
            `parser must not accept program`(
                    """
                        x := 2
                    """.trimIndent()
            )
        }
    }

    @Test
    fun `parser does not accept programs with extra braces`() {
        assertThrows(RecognitionException::class.java) {
            `parser must not accept program`(
                    """
                        if (x < 1) {
                            println(x)
                        }
                        {}
                    """.trimIndent()
            )
        }
    }

    @Test
    fun `parser does not accept programs with incorrect bracket sequence`() {
        assertThrows(RecognitionException::class.java) {
            `parser must not accept program`(
                    """
                        1 + (2 - (3 + 4) - 5) + 6) - 7
                    """.trimIndent()
            )
        }
    }

    /**
     * Next two functions throw [RecognitionException] or [LexerException]
     * because they encapsulate parsing stage.
     */
    private fun `parser must accept program`(code: String) {
        ASTFactory.fromString(code)
    }

    private fun `parser must not accept program`(code: String) {
        ASTFactory.fromString(code)
    }
}