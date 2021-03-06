package dev.jombi.kordsb.core.event.guild

import dev.jombi.kordsb.core.Kord
import dev.jombi.kordsb.core.entity.Guild
import dev.jombi.kordsb.core.event.Event
import dev.jombi.kordsb.core.event.kordCoroutineScope
import kotlinx.coroutines.CoroutineScope

public class GuildCreateEvent(
    public val guild: Guild,
    public val coroutineScope: CoroutineScope = kordCoroutineScope(guild.kord)
) : Event, CoroutineScope by coroutineScope {
    override val kord: Kord get() = guild.kord

    override fun toString(): String {
        return "GuildCreateEvent(guild=$guild)"
    }
}
