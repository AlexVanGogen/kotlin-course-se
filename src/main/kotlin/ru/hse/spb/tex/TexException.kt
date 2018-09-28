package ru.hse.spb.tex

open class TexException(override val message: String): Exception(message)

class DocumentAlreadyHadClassException(existingDocumentClass: String)
    : TexException("Document already has class $existingDocumentClass")

class IllegalPackageDefinitionPlaceException
    : TexException("Packages must be declared after `documentClass` tag and before `frame` tags")

class DuplicatePackageException(packageName: String)
    : TexException("Duplicated package declaration: $packageName")

class DocumentClassNotDefinedException
    : TexException("`frame` was found but `documentClass` command was not defined")