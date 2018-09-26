package ru.hse.spb.interpreter

import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import ru.hse.spb.antlr.LalalangParser
import ru.hse.spb.interpreter.ast.CustomASTMapper
import ru.hse.spb.interpreter.errors.ParsingErrorStrategy
import ru.hse.spb.interpreter.naming.DuplicatedFunctionException
import ru.hse.spb.interpreter.naming.DuplicatedVariableException
import ru.hse.spb.interpreter.naming.FunctionNotFoundException
import ru.hse.spb.interpreter.naming.VariableNotFoundException

/**
 * Test correctness of name resolving.
 */
class NameConflictsTest {

    @Test
    internal fun `using of undeclared variable causes error 1`() {
        val code = """
            x = 1
        """.trimIndent()
        assertThrows<VariableNotFoundException> { evaluate(code) }
    }

    @Test
    internal fun `using of undeclared variable causes error 2`() {
        val code = """
            var y = x
        """.trimIndent()
        assertThrows<VariableNotFoundException> { evaluate(code) }
    }

    @Test
    internal fun `variable duplicating causes error`() {
        val code = """
            var x = 1
            var x = 2
        """.trimIndent()
        assertThrows<DuplicatedVariableException> { evaluate(code) }
    }

    @Test
    internal fun `variable can be shadowed by declaration inside enclosed namespace`() {
        val code = """
            var x = 1
            if (x < 2) {
                var x = 2
                return x
            }
        """.trimIndent()
        assertEquals(2, evaluate(code))
    }

    @Test
    internal fun `enclosed namespaces do not modify the current namespace`() {
        val code = """
            var x = 1
            if (x < 2) {
                var x = 2
            }
            return x
        """.trimIndent()
        assertEquals(1, evaluate(code))
    }

    @Test
    internal fun `variable can be shadowed by function parameter names`() {
        val code = """
            var x = 1
            fun f(x) {
                return x
            }
            return f(2)
        """.trimIndent()
        assertEquals(2, evaluate(code))
    }

    @Test
    internal fun `function parameter can be shadowed by local variable`() {
        val code = """
            fun f(x) {
                var x = 2
                return x
            }
            return f(1)
        """.trimIndent()
        assertEquals(2, evaluate(code))
    }

    @Test
    internal fun `invocation of undeclared function causes error`() {
        val code = """
            return f(x)
        """.trimIndent()
        assertThrows<FunctionNotFoundException> { evaluate(code) }
    }

    @Test
    internal fun `function duplicating causes error`() {
        val code = """
            fun f() {
                return 10
            }
            fun f() {
                return 20
            }
        """.trimIndent()
        assertThrows<DuplicatedFunctionException> { evaluate(code) }
    }

    @Test
    internal fun `function overloading causes error`() {
        val code = """
            fun f() {
                return 10
            }
            fun f(x) {
                return x
            }
        """.trimIndent()
        assertThrows<DuplicatedFunctionException> { evaluate(code) }
    }

    @Test
    internal fun `function can be shadowed by declaration inside enclosed namespace`() {
        val code = """
            fun f(x) {
                fun f(x, y) {
                    return x + y
                }
                return f(1, 2) * x
            }
            return f(10)
        """.trimIndent()
        assertEquals(30, evaluate(code))
    }

    @Test
    internal fun `function parameters duplication is not allowed`() {
        val code = """
            fun f(x, x, y) {
                return x - y
            }
            return f(10, 10, 5)
        """.trimIndent()
        assertThrows<DuplicatedVariableException> { evaluate(code) }
    }

    private fun evaluate(code: String): Int {
        val parser = ASTFactory.fromString(code)
        val visitor = CustomASTMapper()
        val file = visitor.visitFile(parser.file())
        return file.accept(InterpretingVisitor())
    }

    private fun ASTFactory.Companion.fromString(code: String): LalalangParser {
        val lexer = SafeLalalangLexer(CharStreams.fromString(code))
        val tokens = CommonTokenStream(lexer)
        val parser = LalalangParser(tokens)
        parser.errorHandler = ParsingErrorStrategy()
        return parser
    }
}