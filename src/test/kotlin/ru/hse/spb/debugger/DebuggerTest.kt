package ru.hse.spb.debugger

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import ru.hse.spb.Repl

class DebuggerTest {

    @Test
    fun `test code without breakpoints`() {
        val scenario = """
            load src/test/resources/linearcode.lll
            run
        """.trimIndent()
        Assertions.assertEquals(
                """
                    1
                    1
                    2
                    1
                    2
                    3
                    1
                    2
                    3
                    4
                    36
                    Evaluation completed
                """.trimIndent(),
                runScenario(scenario) { Repl.runBlocking() }
        )
    }

    @Test
    fun `test linear code with unconditional breakpoint`() {
        var scenario = """
            load src/test/resources/linearcode.lll
            breakpoint 5
            breakpoint 14
            run
            continue
            continue
        """.trimIndent()
        Assertions.assertEquals(
                """
                    1
                    1
                    Suspended at line 5
                    2
                    1
                    2
                    3
                    1
                    2
                    3
                    Suspended at line 14
                    4
                    36
                    Evaluation completed
                """.trimIndent(),
                runScenario(scenario) { Repl.runBlocking() }
        )
    }

    @Test
    fun `test linear code with true conditional breakpoint`() {
        var scenario = """
            load src/test/resources/linearcode.lll
            condition 5
            a > 0
            breakpoint 14
            run
            continue
            continue
        """.trimIndent()
        Assertions.assertEquals(
                """
                    1
                    1
                    Suspended at line 5
                    2
                    1
                    2
                    3
                    1
                    2
                    3
                    Suspended at line 14
                    4
                    36
                    Evaluation completed
                """.trimIndent(),
                runScenario(scenario) { Repl.runBlocking() }
        )
    }

    @Test
    fun `test linear code with false conditional breakpoint`() {
        var scenario = """
            load src/test/resources/linearcode.lll
            condition 5
            a < 0
            breakpoint 14
            run
            continue
        """.trimIndent()
        Assertions.assertEquals(
                """
                    1
                    1
                    2
                    1
                    2
                    3
                    1
                    2
                    3
                    Suspended at line 14
                    4
                    36
                    Evaluation completed
                """.trimIndent(),
                runScenario(scenario) { Repl.runBlocking() }
        )
    }

    @Test
    fun `test code with unreachable breakpoint`() {
        var scenario = """
            load src/test/resources/branch.lll
            breakpoint 3
            breakpoint 5
            run
            continue
        """.trimIndent()
        Assertions.assertEquals(
                """
                    Suspended at line 5
                    2
                    Evaluation completed
                """.trimIndent(),
                runScenario(scenario) { Repl.runBlocking() }
        )
    }

    @Test
    fun `test code with breakpoint inside loop`() {
        var scenario = """
            load src/test/resources/loop.lll
            breakpoint 3
            run
            continue
            continue
            continue
            continue
            continue
            continue
            continue
            continue
            continue
            continue
        """.trimIndent()
        Assertions.assertEquals(
                """
                    Suspended at line 3
                    0
                    Suspended at line 3
                    1
                    Suspended at line 3
                    2
                    Suspended at line 3
                    3
                    Suspended at line 3
                    4
                    Suspended at line 3
                    5
                    Suspended at line 3
                    6
                    Suspended at line 3
                    7
                    Suspended at line 3
                    8
                    Suspended at line 3
                    9
                    Evaluation completed
                """.trimIndent(),
                runScenario(scenario) { Repl.runBlocking() }
        )
    }

    @Test
    fun `test code with breakpoint inside function`() {
        var scenario = """
            load src/test/resources/fun.lll
            breakpoint 3
            run
            continue
        """.trimIndent()
        Assertions.assertEquals(
                """
                    1 2 3
                    Suspended at line 3
                    6
                    Evaluation completed
                """.trimIndent(),
                runScenario(scenario) { Repl.runBlocking() }
        )
    }

    @Test
    fun `test code with breakpoint inside recursive function`() {
        var scenario = """
            load src/test/resources/recursive_fun.lll
            breakpoint 5
            run
            continue
            continue
            continue
            continue
            continue
        """.trimIndent()
        Assertions.assertEquals(
                """
                    Suspended at line 5
                    7
                    Suspended at line 5
                    6
                    Suspended at line 5
                    5
                    Suspended at line 5
                    4
                    Suspended at line 5
                    3
                    5040
                    Evaluation completed
                """.trimIndent(),
                runScenario(scenario) { Repl.runBlocking() }
        )
    }

    @Test
    fun `test code with breakpoint inside complex recursive function`() {
        var scenario = """
            load src/test/resources/fib.lll
            condition 5
            n > 2
            run
            continue
            continue
            continue
            continue
        """.trimIndent()
        Assertions.assertEquals(
                """
                    Suspended at line 5
                    5
                    Suspended at line 5
                    4
                    Suspended at line 5
                    3
                    2
                    2
                    Suspended at line 5
                    3
                    2
                    8
                    Evaluation completed
                """.trimIndent(),
                runScenario(scenario) { Repl.runBlocking() }
        )
    }

    @Test
    fun `test custom expression evaluation inside linear code`() {
        var scenario = """
            load src/test/resources/linearcode2.lll
            breakpoint 2
            breakpoint 3
            breakpoint 5
            breakpoint 8
            run
            evaluate
            a
            continue
            evaluate
            a + b
            continue
            evaluate
            a + b
            continue
            evaluate
            a + b
            continue
        """.trimIndent()
        Assertions.assertEquals(
                """
                    Suspended at line 2
                    a = 1
                    Suspended at line 3
                    a + b = 3
                    Suspended at line 5
                    a + b = 5
                    Suspended at line 8
                    a + b = 11
                    Evaluation completed
                """.trimIndent(),
                runScenario(scenario) { Repl.runBlocking() }
        )
    }
}