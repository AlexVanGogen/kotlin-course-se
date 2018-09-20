package ru.hse.spb.interpreter.errors

import org.antlr.v4.runtime.*
import ru.hse.spb.interpreter.getLocation

class ParsingErrorStrategy: DefaultErrorStrategy() {

    override fun recover(recognizer: Parser, recognitionException: RecognitionException) {
        throw recognitionException
    }

    override fun reportMissingToken(recognizer: Parser) {
        val token = recognizer.currentToken
        val message = ("expected one of ${recognizer.expectedTokens.toString(recognizer.vocabulary)} " +
                "but ${token.text} was found").asLocatedError(token)
        throw RecognitionException(message, recognizer, recognizer.inputStream, recognizer.context)
    }

    override fun reportNoViableAlternative(recognizer: Parser, noViableAltException: NoViableAltException) {
        val token = noViableAltException.offendingToken
        val msg = "no viable alternative for symbol: ${getTokenErrorDisplay(token)}"
        val recognitionException = RecognitionException(
                msg.asLocatedError(token),
                recognizer,
                recognizer.inputStream,
                recognizer.context
        )
        recognitionException.initCause(noViableAltException)
        throw recognitionException
    }

    override fun reportInputMismatch(recognizer: Parser, inputMismatchException: InputMismatchException) {
        val token = inputMismatchException.offendingToken
        val msg = "mismatched symbol: ${getTokenErrorDisplay(token)}"
        val recognitionException = RecognitionException(
                msg.asLocatedError(token),
                recognizer,
                recognizer.inputStream,
                recognizer.context
        )
        recognitionException.initCause(inputMismatchException)
        throw recognitionException
    }

    override fun reportUnwantedToken(recognizer: Parser) {
        val token = recognizer.currentToken
        val message = ("expected one of ${recognizer.expectedTokens.toString(recognizer.vocabulary)} " +
                "but ${token.text} was found").asLocatedError(token)
        throw RecognitionException(message, recognizer, recognizer.inputStream, recognizer.context)
    }

    override fun reportError(recognizer: Parser, recognitionException: RecognitionException) {
        beginErrorCondition(recognizer)
        if (recognitionException is NoViableAltException) {
            reportNoViableAlternative(recognizer, recognitionException)
        } else if (recognitionException is InputMismatchException) {
            reportInputMismatch(recognizer, recognitionException)
        } else if (recognitionException is FailedPredicateException) {
            reportFailedPredicate(recognizer, recognitionException)
        } else {
            reportUnwantedToken(recognizer)
        }
    }

    private fun String.asLocatedError(token: Token) = "Syntax error at ${token.getLocation()}: $this"
}