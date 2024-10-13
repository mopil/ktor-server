package com.example.api.util

import com.example.common.ErrorResponse
import io.github.smiley4.ktorswaggerui.dsl.routes.OpenApiResponse

object SwaggerUtils {

    fun notFound(): OpenApiResponse.() -> Unit =
        {
            body<ErrorResponse> {
                example("example1") {
                    value = ErrorResponse.ofNotFound()
                }
            }
        }

    fun internalServerError(): OpenApiResponse.() -> Unit =
        {
            body<ErrorResponse> {
                example("example1") {
                    value = ErrorResponse.ofUnexpectedError()
                }
            }
        }

}