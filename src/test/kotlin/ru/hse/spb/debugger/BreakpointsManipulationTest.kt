package ru.hse.spb.debugger

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import ru.hse.spb.Repl

/**
 * Test correctness of adding/changing/removing breakpoints
 */
class BreakpointsManipulationTest {

    @Test
    fun `test unconditional breakpoints adding`() {
        val scenario = """
            load src/test/resources/linearcode.lll
            breakpoint 5
            breakpoint 14
            list
        """.trimIndent()
        assertEquals(
                """
                    line: 5
                    line: 14
                """.trimIndent(),
                runScenario(scenario) { Repl.runBlocking() }
        )
    }

    @Test
    fun `test conditional breakpoints adding`() {
        val scenario = """
            load src/test/resources/linearcode.lll
            breakpoint 5
            condition 8
            (a + 1) < 0
            breakpoint 14
            list
        """.trimIndent()
        assertEquals(
                """
                    line: 5
                    line: 8, condition: (a + 1) < 0
                    line: 14
                """.trimIndent(),
                runScenario(scenario) { Repl.runBlocking() }
        )
    }

    @Test
    fun `test breakpoints updating`() {
        val scenario = """
            load src/test/resources/linearcode.lll
            breakpoint 5
            condition 8
            (a + 1) < 0
            breakpoint 14
            condition 8
            (b + 2) > 1
            condition 14
            1 == 1
            list
        """.trimIndent()
        assertEquals(
                """
                    line: 5
                    line: 8, condition: (b + 2) > 1
                    line: 14, condition: 1 == 1
                """.trimIndent(),
                runScenario(scenario) { Repl.runBlocking() }
        )
    }

    @Test
    fun `test breakpoints removing`() {
        val scenario = """
            load src/test/resources/linearcode.lll
            breakpoint 5
            condition 8
            (a + 1) < 0
            breakpoint 14
            condition 8
            (b + 2) > 1
            condition 14
            1 == 1
            remove 8
            list
        """.trimIndent()
        assertEquals(
                """
                    line: 5
                    line: 14, condition: 1 == 1
                """.trimIndent(),
                runScenario(scenario) { Repl.runBlocking() }
        )
    }

    @Test
    fun `test non-existing breakpoint removing`() {
        val scenario = """
            load src/test/resources/linearcode.lll
            breakpoint 5
            condition 8
            (a + 1) < 0
            breakpoint 14
            condition 8
            (b + 2) > 1
            condition 14
            1 == 1
            remove 9
            list
        """.trimIndent()
        assertEquals(
                """
                    Cannot find breakpoint at line 9
                    line: 5
                    line: 8, condition: (b + 2) > 1
                    line: 14, condition: 1 == 1
                """.trimIndent(),
                runScenario(scenario) { Repl.runBlocking() }
        )
    }
}