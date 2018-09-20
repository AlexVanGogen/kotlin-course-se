package ru.hse.spb

import org.antlr.v4.runtime.RecognitionException
import ru.hse.spb.interpreter.ast.LalalangASTMakingVisitor
import ru.hse.spb.interpreter.ParserFactory
import ru.hse.spb.interpreter.errors.LexerException

fun main(args: Array<String>) {
    if (args.size != 1) {
        println("You can pass only 1 argument: file name")
        return
    }

    try {
        val parser = ParserFactory.fromFile(args[0])
        val visitor = LalalangASTMakingVisitor()
        visitor.visitFile(parser.file())
    } catch (e: LexerException) {
        println(e.message)
        return
    } catch (e: RecognitionException) {
        println(e.message)
        return
    }
}