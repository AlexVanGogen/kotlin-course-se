package ru.hse.spb.interpreter

import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import ru.hse.spb.antlr.LalalangParser
import ru.hse.spb.interpreter.ast.ASTElement
import ru.hse.spb.interpreter.ast.CustomASTMapper
import ru.hse.spb.interpreter.errors.ParsingErrorStrategy

class ASTFactory {
    companion object {
        fun fromFile(codeFile: String): ASTElement {
            val lexer = SafeLalalangLexer(CharStreams.fromFileName(codeFile))
            val tokens = CommonTokenStream(lexer)
            val parser = LalalangParser(tokens)
            parser.errorHandler = ParsingErrorStrategy()
            return CustomASTMapper().visitFile(parser.file())
        }
    }
}