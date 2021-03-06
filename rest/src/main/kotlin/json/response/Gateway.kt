package dev.jombi.kordsb.rest.json.response

import dev.jombi.kordsb.common.serialization.DurationInMilliseconds
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class GatewayResponse(val url: String)

@Serializable
public data class BotGatewayResponse(
    val url: String,
    @SerialName("session_start_limit")
    val sessionStartLimit: SessionStartLimitResponse
)

@Serializable
public data class SessionStartLimitResponse(
    val total: Int,
    val remaining: Int,
    @SerialName("reset_after")
    val resetAfter: DurationInMilliseconds,
    @SerialName("max_concurrency")
    val maxConcurrency: Int
)
