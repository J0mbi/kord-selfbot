package dev.jombi.kordsb.core.behavior.interaction.response

import dev.jombi.kordsb.common.entity.MessageFlag
import dev.jombi.kordsb.common.entity.Snowflake
import dev.jombi.kordsb.core.Kord
import dev.jombi.kordsb.core.behavior.interaction.*
import dev.jombi.kordsb.core.entity.interaction.Interaction
import dev.jombi.kordsb.core.entity.interaction.response.EphemeralMessageInteractionResponse
import dev.jombi.kordsb.core.supplier.EntitySupplier
import dev.jombi.kordsb.core.supplier.EntitySupplyStrategy
import dev.jombi.kordsb.rest.builder.message.modify.InteractionResponseModifyBuilder
import dev.jombi.kordsb.rest.request.RestRequestException
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

/**
 * An [InteractionResponseBehavior] returned when using [respondEphemeral][ActionInteractionBehavior.respondEphemeral],
 * [respond][DeferredEphemeralMessageInteractionResponseBehavior.respond],
 * [deferEphemeralMessageUpdate][ComponentInteractionBehavior.deferEphemeralMessageUpdate] or
 * [updateEphemeralMessage][ComponentInteractionBehavior.updateEphemeralMessage].
 *
 * This is the handle to an [ephemeral][MessageFlag.Ephemeral] message, it supports
 * [editing][EphemeralMessageInteractionResponseBehavior.edit] and sending followup messages to the interaction.
 *
 * The message is only visible to the [user][Interaction.user] who invoked the interaction.
 */
public interface EphemeralMessageInteractionResponseBehavior :
    EphemeralInteractionResponseBehavior,
    MessageInteractionResponseBehavior {

    override fun withStrategy(strategy: EntitySupplyStrategy<*>): EphemeralMessageInteractionResponseBehavior =
        EphemeralMessageInteractionResponseBehavior(applicationId, token, kord, strategy.supply(kord))
}

public fun EphemeralMessageInteractionResponseBehavior(
    applicationId: Snowflake,
    token: String,
    kord: Kord,
    supplier: EntitySupplier = kord.defaultSupplier,
): EphemeralMessageInteractionResponseBehavior = object : EphemeralMessageInteractionResponseBehavior {
    override val applicationId: Snowflake = applicationId
    override val token: String = token
    override val kord: Kord = kord
    override val supplier: EntitySupplier = supplier
}

/**
 * Requests to edit this [EphemeralMessageInteractionResponseBehavior].
 *
 * @return The edited [EphemeralMessageInteractionResponse].
 *
 * @throws RestRequestException if something went wrong during the request.
 */
public suspend inline fun EphemeralMessageInteractionResponseBehavior.edit(
    builder: InteractionResponseModifyBuilder.() -> Unit,
): EphemeralMessageInteractionResponse {
    contract { callsInPlace(builder, InvocationKind.EXACTLY_ONCE) }

    return editEphemeralOriginalResponse(builder)
}
