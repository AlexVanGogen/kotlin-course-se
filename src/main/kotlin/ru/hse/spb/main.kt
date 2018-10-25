package ru.hse.spb

import kotlinx.coroutines.experimental.runBlocking
import ru.hse.spb.debugger.Debugger
import java.util.*

object Repl {
    suspend fun run() {
        val debugger = Debugger()
        while (true) {
            print("dbg> ")
            val nextCommand = readLine()?.split(" ") ?: break
            if (nextCommand.isEmpty())
                println(help())
            else {
                try {
                    when (nextCommand[0]) {
                        "load" -> withAssertionOfArgsNumber(2, nextCommand) { debugger.loadFile(nextCommand[1]) }
                        "breakpoint" -> withAssertionOfArgsNumber(2, nextCommand) { debugger.addBreakpoint(nextCommand[1].toInt()) }
                        "condition" -> withAssertionOfArgsNumber(2, nextCommand) {
                            print("dbg\$condition> ")
                            val condition = readLine()
                            debugger.addConditionalBreakpoint(nextCommand[1].toInt(), condition ?: throw InputMismatchException("Condition not found"))
                        }
                        "list" -> withAssertionOfArgsNumber(1, nextCommand) { debugger.listBreakpoints() }
                        "remove" -> withAssertionOfArgsNumber(2, nextCommand) {
                            val lineNumber = nextCommand[1].toInt()
                            if (!debugger.removeBreakpoint(lineNumber)) {
                                println("Cannot find breakpoint at line $lineNumber")
                            }
                        }
                        "run" -> withAssertionOfArgsNumber(1, nextCommand) { runBlocking { debugger.run() } }
                        "evaluate" -> withAssertionOfArgsNumber(1, nextCommand) {
                            print("dbg\$expression> ")
                            val expression = readLine()
                            println(debugger.evaluateExpression(expression ?: throw InputMismatchException("Expression not found")))
                        }
                        "stop" -> withAssertionOfArgsNumber(1, nextCommand) { debugger.stopInterpreting() }
                        "continue" -> withAssertionOfArgsNumber(1, nextCommand) { debugger.continueInterpreting() }
                        "quit" -> return
                        else -> println(help())
                    }
                } catch (e: Exception) {
                    println("[Error]: " + e.message)
                }
            }
        }
    }

    fun runBlocking() {
        runBlocking { run() }
    }

    private fun withAssertionOfArgsNumber(number: Int, args: List<String>, action: () -> Unit) {
        if (args.size != number) {
            println(help())
            return
        }
        action()
    }

    fun help() = """
    Usage:
       load <filename> – load program code to memory.
       breakpoint <line-number> – set breakpoint to the line with given number.
       condition <line-number> <condition-expression> – set breakpoint with given condition to the line with given number.
       list – show list of breakpoints with their line numbers and conditions.
       remove <line-number> – remove breakpoint by given line number.
       run – run loaded file.
       evaluate <expression> – evaluate given expression with context of current debugger state.
       stop – suspend current program evaluation in the next breakpoint.
       continue – resume current program evaluation until next breakpoint.
       quit – exit from REPL.
""".trimIndent()
}

fun main(args: Array<String>) {
    Repl.runBlocking()
}

