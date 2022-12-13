package com.codewithmohsen.lastnews.data.remote.api

import com.codewithmohsen.domain.api.APIErrorResponse
import com.codewithmohsen.domain.api.ErrorModel
import com.codewithmohsen.domain.api.NetworkResponse
import okhttp3.Request
import okhttp3.ResponseBody
import okio.Timeout
import retrofit2.*
import timber.log.Timber
import java.io.IOException
import java.lang.reflect.Type
import java.util.concurrent.TimeUnit

class NetworkResponseAdapter<S : Any, E : com.codewithmohsen.domain.api.APIErrorResponse<com.codewithmohsen.domain.api.ErrorModel>>(
    private val successType: Type,
    private val errorModelConverter: Converter<ResponseBody, com.codewithmohsen.domain.api.ErrorModel>
) : CallAdapter<S, Call<com.codewithmohsen.domain.api.NetworkResponse<S, E>>> {

    override fun responseType(): Type = successType

    override fun adapt(call: Call<S>): Call<com.codewithmohsen.domain.api.NetworkResponse<S, E>> {
        return NetworkResponseCall(call, errorModelConverter)
    }

    internal class NetworkResponseCall<S : Any, E : com.codewithmohsen.domain.api.APIErrorResponse<com.codewithmohsen.domain.api.ErrorModel>>(
        private val delegate: Call<S>,
        private val errorConverter: Converter<ResponseBody, com.codewithmohsen.domain.api.ErrorModel>
    ) : Call<com.codewithmohsen.domain.api.NetworkResponse<S, E>> {

        override fun enqueue(callback: Callback<com.codewithmohsen.domain.api.NetworkResponse<S, E>>) {
            return delegate.enqueue(object : Callback<S> {
                override fun onResponse(call: Call<S>, response: Response<S>) {
                    val body = response.body()
                    val code = response.code()
                    val error = response.errorBody()

                    if (response.isSuccessful) {
                        Timber.d("API response successful.")
                        if (body != null) {
                            // API success
                            // No need to check 204, because we checked nullability of the body
                            // In this case we only have 200 for success but we follow general roles
                            check(code in 200..299)
                            Timber.d("API success")
                            callback.onResponse(
                                this@NetworkResponseCall,
                                Response.success(com.codewithmohsen.domain.api.NetworkResponse.Success(body))
                            )
                        } else {
                            Timber.d("API body is null")
                            // Response is successful but the body is null
                            callback.onResponse(
                                this@NetworkResponseCall,
                                Response.success(com.codewithmohsen.domain.api.NetworkResponse.Empty(null))
                            )
                        }
                    } else {
                        Timber.d("API response is not successful.")
                        val errorBody = convertToErrorBody(error)
                        Timber.d("Code: $code Error body: $errorBody")
                        callback.onResponse(
                            this@NetworkResponseCall,
                            Response.success(
                                com.codewithmohsen.domain.api.NetworkResponse.APIError(
                                    createApiErrorResponse(
                                        code,
                                        errorBody ?: com.codewithmohsen.domain.api.ErrorModel(
                                            ERROR_STATUS,
                                            ERROR_CODE,
                                            ERROR_MESSAGE
                                        )
                                    )
                                )
                                        as com.codewithmohsen.domain.api.NetworkResponse<S, E>
                            )
                        )
                    }
                }

                override fun onFailure(call: Call<S>, throwable: Throwable) {
                    Timber.d("API onFailure.")
                    val networkResponse = when (throwable) {
                        is IOException -> com.codewithmohsen.domain.api.NetworkResponse.NetworkError(throwable)
                        else -> com.codewithmohsen.domain.api.NetworkResponse.UnknownError(throwable)
                    }
                    callback.onResponse(this@NetworkResponseCall, Response.success(networkResponse))
                }
            })
        }

        private fun createApiErrorResponse(
            code: Int,
            errorModel: com.codewithmohsen.domain.api.ErrorModel
        ): com.codewithmohsen.domain.api.APIErrorResponse<com.codewithmohsen.domain.api.ErrorModel> {
            return when (code) {
                401 -> com.codewithmohsen.domain.api.APIErrorResponse.Unauthenticated(errorModel)
                in 400..499 -> com.codewithmohsen.domain.api.APIErrorResponse.ClientErrorResponse(errorModel)
                in 500..599 -> com.codewithmohsen.domain.api.APIErrorResponse.ServerErrorResponse(errorModel)
                else -> com.codewithmohsen.domain.api.APIErrorResponse.UnexpectedErrorResponse(errorModel)
            }
        }

        /**
         * We use Kotlin reflection for converting ResponseBody to ErrorBody.
         */
        private fun convertToErrorBody(error: ResponseBody?): com.codewithmohsen.domain.api.ErrorModel? {
            return when {
                error == null -> null
                error.contentLength() == 0L -> null
                else -> try {
                    //we use kotlin reflection for converting the body to ErrorBody Class
                    errorConverter.convert(error)
                } catch (ex: Exception) {
                    null
                }
            }
        }

        override fun isExecuted() = delegate.isExecuted

        override fun clone() = NetworkResponseCall<S, E>(delegate.clone(), errorConverter)

        override fun isCanceled() = delegate.isCanceled

        override fun cancel() = delegate.cancel()

        override fun execute(): Response<com.codewithmohsen.domain.api.NetworkResponse<S, E>> {
            throw UnsupportedOperationException("NetworkResponseCall doesn't support execute")
        }

        override fun request(): Request = delegate.request()

        override fun timeout(): Timeout {
            return Timeout().timeout(Constants.REQUEST_TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
        }
    }

    companion object {
        const val ERROR_STATUS = "No status"
        const val ERROR_CODE = "No code"
        const val ERROR_MESSAGE = "No message"
    }
}
