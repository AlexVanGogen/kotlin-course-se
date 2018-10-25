package ru.hse.spb.debugger

import java.util.*

class BreakpointsList {
    private val breakpoints: MutableSet<Breakpoint> = TreeSet(kotlin.Comparator.comparing(Breakpoint::lineNumber))

    fun addBreakpoint(lineNumber: Int) {
        val breakpoint = Breakpoint(lineNumber)
        if (breakpoint in breakpoints) {
            breakpoints.remove(breakpoint)
        }
        breakpoints.add(breakpoint)
    }

    fun addConditionalBreakpoint(lineNumber: Int, condition: String) {
        val breakpoint = Breakpoint(lineNumber, condition)
        if (breakpoint in breakpoints) {
            breakpoints.remove(breakpoint)
        }
        breakpoints.add(breakpoint)
    }

    fun removeBreakpoint(lineNumber: Int): Boolean {
        val breakpoint = Breakpoint(lineNumber);
        return breakpoints.remove(breakpoint)
    }

    fun getBreakpointByLineNumber(lineNumber: Int): Breakpoint? {
        return breakpoints.firstOrNull { it.lineNumber == lineNumber }
    }

    fun listBreakpoints(): List<String> = breakpoints.map {
        "line: ${it.lineNumber}${if (it.isConditional()) ", condition: ${it.conditionRepresentation}" else ""}"
    }

    fun clear() {
        breakpoints.clear()
    }
}