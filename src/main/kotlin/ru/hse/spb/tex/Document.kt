package ru.hse.spb.tex

typealias Package = Pair<String, String?>

/**
 * Command that encloses `document` namespace.
 * Only following sequence of commands is allowed:
 *
 * document {
 *     documentClass(class)
 *     usePackage(!package1, package2 to option2, ..., !packageN)
 *     frame(frameTitle1) { ... }
 *     frame(frameTitle2) { ... }
 *     frame(frameTitle3) { ... }
 *     ...
 *     frame(frameTitleM) { ... }
 * }
 *
 * It will be translated to the following TeX code:
 *
 * \documentclass{class}
 * \usepackage{package1}
 * \usepackage\[option2\]{package2}
 * ...
 * \usepackage{packageN}
 * \begin{document}
 *     \begin{frame}
 *         \frametitle{frameTitle1}
 *             ...
 *     \end{frame}
 *
 *     \begin{frame}
 *         \frametitle{frameTitle2}
 *             ...
 *     \end{frame}
 *
 *     \begin{frame}
 *         \frametitle{frameTitle3}
 *             ...
 *     \end{frame}
 *
 *     ...
 *
 *     \begin{frame}
 *         \frametitle{frameTitleM}
 *             ...
 *     \end{frame}
 * \end{document}
 *
 * Note that `usePackage` method must accept at least 1 parameter.
 */
@TexCommandMarker
class Document: TexTag(TagKind.ENVIRONMENT, "document") {

    init {
        resetBuilder()
    }

    private var docClass: String? = null
    private var isPackagesInitialized = false

    private val declaredPackagesList = mutableListOf<String>()
    private var isDocumentStarted = false

    fun documentClass(className: String) {
        if (docClass != null)
            throw DocumentAlreadyHadClassException(docClass!!)
        append("\\documentclass{$className}")
        docClass = className
    }

    fun usePackage(firstPackage: Package, vararg otherPackages: Package) {
        if (isPackagesInitialized)
            throw IllegalPackageDefinitionPlaceException()
        append(firstPackage.makeTag())
        declaredPackagesList.add(firstPackage.first)
        for (nextPackage in otherPackages) {
            if (nextPackage.first in declaredPackagesList)
                throw DuplicatePackageException(nextPackage.first)
            append(nextPackage.makeTag())
            declaredPackagesList.add(nextPackage.first)
        }
    }

    fun frame(frameTitle: String, vararg params: NamedParameter, init: Frame.() -> Unit): Frame {
        if (docClass == null) {
            throw DocumentClassNotDefinedException()
        }
        startEnvironmentIfNotStarted()
        val frame = Frame(frameTitle, params)
        frame.nested(newIndentForEnvironment = true) {
            frame.append("\\frametitle{${frame.frameTitle}}")
            frame.init()
        }
        return frame
    }

    fun startEnvironmentIfNotStarted() {
        if (!isDocumentStarted) {
            if (docClass == null)
                throw DocumentClassNotDefinedException()
            append("\\begin{$tagName}")
            isDocumentStarted = true
        }
    }

    /**
     * !"packageName" means that package has not option.
     */
    operator fun String.not() = Package(this, null)

    private fun Package.makeTag(): String {
        return "\\usepackage${if (second == null) "" else "[$second]"}{$first}"
    }
}

/**
 * According to requirements to DSL, `documentclass` and `usepackage` tags must be used
 * inside block for `document` tag, but in TeX `\documentclass` and `\usepackage` tahs
 * are declared outside `\begin{document}...\end{document}` environment.
 */
fun document(init: Document.() -> Unit): Document {
    val document = Document()
    document.init()
    document.startEnvironmentIfNotStarted()
    document.append("\\end{${document.tagName}}")
    return document
}