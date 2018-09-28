package ru.hse.spb.tex

/**
 * Command that encloses `document` namespace.
 * Only following sequence of commands is allowed:
 *
 * document {
 *     documentClass(class)
 *     usePackage(package1, package2, ..., packageN)
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
 * \usepackage{package2}
 * ...
 * \usepackage{packageN}
 * \begin{document}
 *     \begin{frame}
 *     \frametitle{frameTitle1}
 *         ...
 *     \end{frame}
 *
 *     \begin{frame}
 *     \frametitle{frameTitle2}
 *         ...
 *     \end{frame}
 *
 *     \begin{frame}
 *     \frametitle{frameTitle3}
 *         ...
 *     \end{frame}
 *
 *     ...
 *
 *     \begin{frame}
 *     \frametitle{frameTitleM}
 *         ...
 *     \end{frame}
 * \end{document}
 *
 * Note that `usePackage` method must accept at least 1 parameter.
 */
class Document: TexCommand("document") {

    private var docClass: String? = null
    private var isPackagesInitialized = false

    private val declaredPackagesList = mutableListOf<String>()

    fun documentClass(className: String) {
        if (docClass != null)
            throw DocumentAlreadyHadClassException(docClass!!)
        append("\\documentclass{$className}")
        docClass = className
    }

    fun usePackage(firstPackage: String, vararg otherPackages: String) {
        if (isPackagesInitialized || docClass == null)
            throw IllegalPackageDefinitionPlaceException()
        append("\\usepackage{$firstPackage}")
        declaredPackagesList.add(firstPackage)
        for (nextPackage in otherPackages) {
            if (nextPackage in declaredPackagesList)
                throw DuplicatePackageException(nextPackage)
            append("\\usepackage{$nextPackage}")
            declaredPackagesList.add(nextPackage)
        }
        isPackagesInitialized = true
        append("\\begin{$commandName}")

    }

    fun frame(frameTitle: String, init: Frame.() -> Unit): Frame {
        if (docClass == null) {
            throw DocumentClassNotDefinedException()
        }
        val frame = Frame(frameTitle)
        nested {
            frame.append("\\begin{${frame.commandName}}")
            frame.append("\\frametitle{${frame.frameTitle}}")
            frame.nested { frame.init() }
            frame.append("\\end{${frame.commandName}}")
        }
        return frame
    }
}