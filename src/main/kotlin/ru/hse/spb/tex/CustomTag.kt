package ru.hse.spb.tex

interface CustomTag

class CustomCommand(
            override val tagName: String,
            override val params: Array<out NamedParameter> = arrayOf()
): TexTag(TagKind.COMMAND, tagName, params), CustomTag

class CustomEnvironment(
            override val tagName: String,
            override val params: Array<out NamedParameter> = arrayOf()
): ContentHolderTag(TagKind.ENVIRONMENT, tagName, params), CustomTag