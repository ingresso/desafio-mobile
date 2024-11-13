package br.com.hectorfortuna.ingressoapp.core

data class State<out T>(
    val status: Status,
    val loading: Boolean?,
    val data: T?,
    val error: Throwable?,
) {
    companion object {
        fun <T> success(data: T?): State<T> =
            State(Status.SUCCESS, false, data, null)

        fun <T> error(error: Throwable): State<T> =
            State(Status.ERROR, false, null, error)

        fun <T> loading(loading: Boolean): State<T> =
            State(Status.LOADING, loading, null, null)
    }

}
