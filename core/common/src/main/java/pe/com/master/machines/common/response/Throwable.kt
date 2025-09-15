package pe.com.master.machines.common.response

import java.io.IOException
import java.net.SocketTimeoutException

fun Throwable.toErrorType() = when (this) {
    is SocketTimeoutException -> ErrorType.Api.Timeout(this.message.orEmpty())
    is IOException -> ErrorType.Api.Network(this.message.orEmpty())
    /*is ResponseException -> when (response.status.value) {
        RESOURCE_NOT_FOUND -> ErrorType.Api.NotFound(this.message.orEmpty())
        INTERNAL_SERVER -> ErrorType.Api.Server(this.message.orEmpty())
        SERVICE_UNAVAILABLE -> ErrorType.Api.ServiceUnavailable(this.message.orEmpty())
        UNAUTHORIZED -> ErrorType.Api.Unauthorized(this.message.orEmpty())
        else -> ErrorType.Unknown(this.message ?: "Error desconocido")
    }

    is FirebaseAuthException -> ErrorType.ErrorAuth(this.message.orEmpty())*/
    else -> ErrorType.Unknown(this.message.orEmpty())
}