package io.iskopasi.somedemo.managers

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.http.isSuccess
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json


data class ParsedData<T>(
    val data: T?,
    val code: Int,
    val message: String?,
)

class RestApi {
    val client: HttpClient = HttpClient(OkHttp) {
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
                explicitNulls = false
                ignoreUnknownKeys = true
            })
        }
        install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.BODY
        }

        engine {
            config {
                followRedirects(true)
            }
        }
    }

    suspend inline fun <reified T> getData(path: String) = client.post(path) {
        contentType(ContentType.Application.Json)
        setBody(SomeSampleRequest(path = path))
    }.parse<T>()

    suspend inline fun <reified T> HttpResponse.parse(): ParsedData<T> {
        val data = if (this.status.isSuccess()) {
            try {
                this.body<T>()
            } catch (ex: Exception) {
                ex.printStackTrace()

                null
            }
        } else null

        return ParsedData(
            data = data,
            code = this.status.value,
            message = this.status.description
        )
    }
}