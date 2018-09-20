package ru.hse.spb.interpreter

import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import ru.hse.spb.antlr.LalalangParser
import ru.hse.spb.interpreter.errors.ParsingErrorStrategy

class ParserFactory {
    companion object {
        fun fromFile(codeFile: String): LalalangParser {
            val lexer = SafeLalalangLexer(CharStreams.fromFileName(codeFile))
            val tokens = CommonTokenStream(lexer)
            val parser = LalalangParser(tokens)
            parser.errorHandler = ParsingErrorStrategy()
            return parser
        }
    }
}