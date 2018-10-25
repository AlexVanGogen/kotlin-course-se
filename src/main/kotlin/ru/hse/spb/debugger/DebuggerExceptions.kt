package ru.hse.spb.debugger

sealed class DebuggerException(override val message: String): Exception(message)
class FileNotLoadedException: DebuggerException("File not loaded")
class ASTNotExistsException: DebuggerException("AST not exists")