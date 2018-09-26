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
        `parser must accept program`("empty.lll")
    }

    @Test
    fun `parser accepts simple binary operations`() {
        `parser must accept program`("binary_operations.lll")
    }

    @Test
    fun `parser accepts expressions`() {
        `parser must accept program`("expressions.lll")
    }

    @Test
    fun `parser accepts functions`() {
        `parser must accept program`("functions.lll")
    }

    @Test
    fun `parser accepts nested blocks`() {
        `parser must accept program`("nested_blocks.lll")
    }

    @Test
    fun `parser accepts statements`() {
        `parser must accept program`("statements.lll")
    }

    @Test
    fun `parser accepts variable declarations`() {
        `parser must accept program`("variables.lll")
    }

    @Test
    fun `parser does not accept uncompleted expressions`() {
        for (i in 1..3) {
            assertThrows(RecognitionException::class.java) {
                `parser must not accept program`("uncompleted_expr_$i.lll")
            }
        }
    }

    @Test
    fun `parser does not accept programs with unsupported symbols`() {
        assertThrows(LexerException::class.java) {
            `parser must not accept program`("invalid_symbol.lll")
        }
    }

    @Test
    fun `parser does not accept programs with extra braces`() {
        assertThrows(RecognitionException::class.java) {
            `parser must not accept program`("extra_braces.lll")
        }
    }

    @Test
    fun `parser does not accept programs with incorrect bracket sequence`() {
        assertThrows(RecognitionException::class.java) {
            `parser must not accept program`("incorrect_bracket_sequence.lll")
        }
    }

    /**
     * Next two functions throw [RecognitionException] or [LexerException]
     * because they encapsulate parsing stage.
     * @see [ASTFactory.fromFile]
     */
    private fun `parser must accept program`(filename: String) {
        ASTFactory.fromFile("$GOOD_EXAMPLES_DIR_NAME/$filename")
    }

    private fun `parser must not accept program`(filename: String) {
        ASTFactory.fromFile("$FAILING_EXAMPLES_DIR_NAME/$filename")
    }

    companion object {
        private const val GOOD_EXAMPLES_DIR_NAME = "src/test/resources/parsing/good_examples"
        private const val FAILING_EXAMPLES_DIR_NAME = "src/test/resources/parsing/failing_examples"
    }
}