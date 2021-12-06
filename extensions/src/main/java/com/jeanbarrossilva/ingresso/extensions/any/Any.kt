package com.jeanbarrossilva.ingresso.extensions.any

/** @see orIf **/
fun <T> T.doIf(condition: Boolean, operation: T.() -> Unit): T {
    return orIf(condition) {
        operation()
        this
    }
}

/**
 * @return The result of the [operation] invocation or [this] if [condition] is `false`.
 **/
fun <T> T.orIf(condition: Boolean, operation: T.() -> T): T {
    return if (condition) operation() else this
}

/** @see orIf **/
fun <T> T.orIf(condition: T.() -> Boolean, operation: T.() -> T): T {
    return orIf(condition(), operation)
}