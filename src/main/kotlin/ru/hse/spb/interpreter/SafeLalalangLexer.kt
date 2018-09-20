package ru.hse.spb.interpreter

import org.antlr.v4.runtime.CharStream
import org.antlr.v4.runtime.Token
import ru.hse.spb.antlr.LalalangLexer
import ru.hse.spb.interpreter.errors.LexerException

class SafeLalalangLexer(input: CharStream): LalalangLexer(input) {

    override fun emit(token: Token) {
        when (token.type) {
            MismatchedSymbol -> throw LexerException("Error in ${token.getLocation()}: invalid symbol '${token.text}'")
            else -> super.emit(token)
        }
    }
}