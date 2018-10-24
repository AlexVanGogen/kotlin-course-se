package ru.hse.spb

import org.antlr.v4.runtime.RecognitionException
import ru.hse.spb.interpreter.InterpretingVisitor
import ru.hse.spb.interpreter.ASTFactory
import ru.hse.spb.interpreter.errors.LexerException

fun main(args: Array<String>) {
    if (args.size != 1) {
        println("You can pass only 1 argument: file identifier")
        return
    }

    try {
        val file = ASTFactory.fromFile(args[0])
        file.accept(InterpretingVisitor())
    } catch (e: LexerException) {
        println(e.message)
        return
    } catch (e: RecognitionException) {
        println(e.message)
        return
    }
}