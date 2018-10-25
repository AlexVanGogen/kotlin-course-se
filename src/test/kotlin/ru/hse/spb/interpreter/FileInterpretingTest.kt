package ru.hse.spb.interpreter

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

/**
 * Test correctness of real programs interpreting.
 */
class FileInterpretingTest {

    @Test
    internal suspend fun `test literals`() {
        val code = """
            1
            2
            3
            4
            5
        """.trimIndent()
        assertEquals(0, eval(code))
    }

    @Test
    internal suspend fun `test literals with return as the last statement`() {
        val code = """
            1
            2
            3
            4
            return 5
        """.trimIndent()
        assertEquals(5, eval(code))
    }

    @Test
    internal suspend fun `test literals with return as the middle statement`() {
        val code = """
            1
            2
            return 3
            4
            return 5
        """.trimIndent()
        assertEquals(3, eval(code))
    }

    @Test
    internal suspend fun `test expression "2 + 2 * 2"`() {
        val code = """
            return 2 + 2 * 2
        """.trimIndent()
        assertEquals(6, eval(code))
    }

    @Test
    internal suspend fun `test expression "(2 + 2) * 2"`() {
        val code = """
            return (2 + 2) * 2
        """.trimIndent()
        assertEquals(8, eval(code))
    }

    @Test
    internal suspend fun `test expression "3 + (4 - 5 % 3) && 1"`() {
        val code = """
            return 3 + (4 - 5 % 3) && 1
        """.trimIndent()
        assertEquals(1, eval(code))
    }

    @Test
    internal suspend fun `test expression "3 + (4 - 5 % 3) && 0"`() {
        val code = """
            return 3 + (4 - 5 % 3) && 0
        """.trimIndent()
        assertEquals(0, eval(code))
    }

    @Test
    internal suspend fun `test branching, true condition`() {
        val code = """
            if (3 != 5) {
                return 2
            } else {
                return 3
            }
        """.trimIndent()
        assertEquals(2, eval(code))
    }

    @Test
    internal suspend fun `test branching, false condition, "else" branch exists`() {
        val code = """
            if (3 == 5) {
                return 2
            } else {
                return 3
            }
        """.trimIndent()
        assertEquals(3, eval(code))
    }

    @Test
    internal suspend fun `test branching, false condition, "else" branch do not exists`() {
        val code = """
            if (3 == 5) {
                return 2
            }
        """.trimIndent()
        assertEquals(0, eval(code))
    }

    @Test
    internal suspend fun `test assignment "var x = 4"`() {
        val code = """
            var x = 4
            x = -x * 2
            return x
        """.trimIndent()
        assertEquals(-8, eval(code))
    }

    @Test
    internal suspend fun `test loop 1`() {
        val code = """
            var i = 0
            while (i < 10) {
                i = i + 1
            }
            return i
        """.trimIndent()
        assertEquals(10, eval(code))
    }

    @Test
    internal suspend fun `test loop 2`() {
        val code = """
            var i = 0
            while (i < 10) {
                i = i + 9
            }
            return i
        """.trimIndent()
        assertEquals(18, eval(code))
    }

    @Test
    internal suspend fun `test loop 3`() {
        val code = """
            var i = 0
            while (i < 10) {
                return 20
            }
            return i
        """.trimIndent()
        assertEquals(20, eval(code))
    }

    @Test
    internal suspend fun `test function`() {
        val code = """
            fun foo(n) {
                fun bar(m) {
                    return m + n
                }

                return bar(1)
            }

            return foo(41)
        """.trimIndent()
        assertEquals(42, eval(code))
    }

    @Test
    internal suspend fun `test recursive function`() {
        val code = """
            fun fib(n) {
                if (n <= 1) {
                    return n
                }
                return fib(n - 1) + fib(n - 2)
            }

            return fib(10)
        """.trimIndent()
        assertEquals(55, eval(code))
    }

    @Test
    internal suspend fun `test code with comments`() {
        val code = """
            /**
             * Function that calculates n-th Fibonacci number.
             * First elements of Fibonacci sequence:
             * 0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, ...
             * (OEIS A000045)
             */
            fun fib(n) {
                if (n <= 1) {
                    return n
                }
                return fib(n - 1) + fib(n - 2)
            }

            // 10th Fibonacci number is 55
            return fib(10) // returns 55
        """.trimIndent()
        assertEquals(55, eval(code))
    }
}