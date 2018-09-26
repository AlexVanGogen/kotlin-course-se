package ru.hse.spb.interpreter

import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonToken
import org.antlr.v4.runtime.CommonTokenStream
import ru.hse.spb.antlr.LalalangParser
import ru.hse.spb.interpreter.ast.*
import ru.hse.spb.interpreter.errors.ParsingErrorStrategy

internal fun eval(code: String): Int {
    return ASTFactory.fromString(code).accept(InterpretingVisitor())
}

internal fun ASTFactory.Companion.fromString(code: String): File {
    val lexer = SafeLalalangLexer(CharStreams.fromString(code))
    val tokens = CommonTokenStream(lexer)
    val parser = LalalangParser(tokens)
    parser.errorHandler = ParsingErrorStrategy()
    return CustomASTMapper().visitFile(parser.file())
}