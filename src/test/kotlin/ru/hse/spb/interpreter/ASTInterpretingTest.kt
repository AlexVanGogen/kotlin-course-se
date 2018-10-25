package ru.hse.spb.interpreter

import kotlinx.coroutines.experimental.runBlocking
import org.antlr.v4.runtime.CommonToken
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import ru.hse.spb.interpreter.ast.*

typealias Function = FunctionDeclaration
typealias Variable = VariableDeclaration
typealias While = WhileStatement
typealias If = IfStatement
typealias Assign = AssignmentStatement
typealias Return = ReturnStatement

typealias Mult = MultiplicativeExpression
typealias Add = AdditiveExpression
typealias Comparison = ComparisonExpression
typealias Logical = LogicalExpression
typealias Call = FunctionCallExpression
typealias Unary = UnarySignedExpression

typealias L = LiteralExpression
typealias I = IdentifierExpression
typealias O = OperatorType

/**
 * Test correctness of interpreting the given AST, not input.
 */
class ASTInterpretingTest {

    @Test
    internal fun `interpret literals`() {
        for (i in 0..100)
            assertEquals(i, L.of(i).eval())
    }

    @Test
    internal fun `interpret simple expressions`() {
        assertEquals(99, Add(L.of(0), O.of("+"), L.of(99)).eval())
        assertEquals(25, Mult(L.of(5), O.of("*"), L.of(5)).eval())
        assertEquals(35, Mult(L.of(71), O.of("%"), L.of(36)).eval())
        assertEquals(1, Comparison(L.of(3), O.of(">"), L.of(2)).eval())
        assertEquals(0, Comparison(L.of(3), O.of("=="), L.of(2)).eval())
        assertEquals(0, Logical(L.of(100500), O.of("&&"), L.of(0)).eval())
        assertEquals(-50, Unary(L.of(50), Sign.of("-")!!).eval())
    }

    @Test
    internal fun `interpret long expressions`() {
        assertEquals(
                74,
                Add(
                        Add(L.of(0), O.of("+"), L.of(99)),
                        O.of("-"),
                        Mult(L.of(5), O.of("*"), L.of(5))
                ).eval()
        )
        assertEquals(
                1,
                Comparison(
                        Add(
                                Add(L.of(0), O.of("+"), L.of(99)),
                                O.of("-"),
                                Mult(L.of(5), O.of("*"), L.of(5))
                        ),
                        O.of(">="),
                        Mult(
                                Add(L.of(3), O.of("+"), L.of(4)),
                                O.of("*"),
                                Mult(L.of(125), O.of("/"), L.of(12))
                        )
                ).eval()
        )
    }

    @Test
    internal fun `interpret assignment`() {
        assertEquals(
                99,
                File(
                        Block(
                                listOf(
                                        Variable(
                                                Identifier("x", fakeToken),
                                                Add(L.of(0), O.of("+"), L.of(99))
                                        ),
                                        Return(
                                                I.of("x")
                                        )
                                )
                        )
                ).eval()
        )
    }

    @Test
    internal fun `interpret double assignment`() {
        assertEquals(
                25,
                File(
                        Block(
                                listOf(
                                        Variable(
                                                Identifier("y", fakeToken),
                                                Add(L.of(0), O.of("+"), L.of(99))
                                        ),
                                        Assign(
                                                Identifier("y", fakeToken),
                                                Mult(L.of(5), O.of("*"), L.of(5))
                                        ),
                                        Return(
                                                I.of("y")
                                        )
                                )
                        )
                ).eval()
        )
    }

    @Test
    internal fun `interpret if statement`() {
        assertEquals(
                42,
                File(
                        Block(
                                listOf(
                                        Variable(
                                                Identifier("y", fakeToken),
                                                L.of(5)
                                        ),
                                        If(
                                                Comparison(I.of("y"), O.of(">"), I.of("y")),
                                                Block(
                                                        listOf(
                                                                Return(
                                                                        I.of("y")
                                                                )
                                                        )
                                                ),
                                                Block(
                                                        listOf(
                                                                Return(
                                                                        L.of(42)
                                                                )
                                                        )
                                                )
                                        )
                                )
                        )
                ).eval()
        )
    }

    @Test
    internal fun `interpret while statement`() {
        assertEquals(
                1,
                File(
                        Block(
                                listOf(
                                        Variable(
                                                Identifier("y", fakeToken),
                                                L.of(5)
                                        ),
                                        While(
                                                Comparison(
                                                        I.of("y"),
                                                        O.of("<"),
                                                        Mult(
                                                                I.of("y"),
                                                                O.of("*"),
                                                                I.of("y")
                                                        )
                                                ),
                                                Block(
                                                        listOf(
                                                                Assign(
                                                                        Identifier("y", fakeToken),
                                                                        Add(
                                                                                I.of("y"),
                                                                                O.of("-"),
                                                                                L.of(1)
                                                                        )
                                                                )
                                                        )
                                                )
                                        ),
                                        Return(
                                                I.of("y")
                                        )
                                )
                        )
                ).eval()
        )
    }

    @Test
    internal fun `interpret function`() {
        assertEquals(
                42,
                File(
                        Block(
                                listOf(
                                        Function(
                                                Identifier("suc", fakeToken),
                                                listOf(
                                                        Identifier("n", fakeToken)
                                                ),
                                                Block(
                                                        listOf(
                                                                Return(
                                                                        Add(
                                                                                I.of("n"),
                                                                                O.of("+"),
                                                                                L.of(1)
                                                                        )
                                                                )
                                                        )
                                                )
                                        ),
                                        Return(
                                                Call(
                                                        Identifier("suc", fakeToken),
                                                        listOf(
                                                                L.of(41)
                                                        )
                                                )
                                        )
                                )
                        )
                ).eval()
        )
    }

    private fun ASTElement.eval(): Int = runBlocking {
        return@runBlocking accept(InterpretingVisitor())
    }

    private val fakeToken = CommonToken(0)
    private fun IdentifierExpression.Companion.of(variableName: String) = I(Identifier(variableName, fakeToken))
    private fun LiteralExpression.Companion.of(value: Int) = L(Literal(value.toString(), fakeToken))
}