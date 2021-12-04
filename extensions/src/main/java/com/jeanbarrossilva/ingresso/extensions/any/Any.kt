package com.jeanbarrossilva.ingresso.extensions.any

/**
 * @return The result of the [operation] invocation or [this] if [condition] is `false`.
 **/
fun <T> T.orIf(condition: Boolean, operation: T.() -> T): T {
    return if (condition) operation() else this
}