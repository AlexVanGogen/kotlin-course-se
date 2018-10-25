package ru.hse.spb.debugger

import java.io.ByteArrayOutputStream
import java.io.PrintStream

fun runScenario(scenario: String, action: () -> Unit): String {
    val output = ByteArrayOutputStream()
    System.setIn(scenario.byteInputStream())
    System.setOut(PrintStream(output))
    action()
    val result = output.toString()
            .replace("dbg> ", "")
            .replace("dbg\$condition> ", "")
            .replace("dbg\$expression> ", "")
            .trim()
    System.setIn(System.`in`)
    System.setOut(System.out)
    return result
}