package dev.jombi.kordsb.core.entity

import dev.jombi.kordsb.core.cache.data.IntegrationData
import equality.GuildEntityEqualityTest
import io.mockk.every
import io.mockk.mockk
import mockKord

internal class IntegrationTest : GuildEntityEqualityTest<Integration> by GuildEntityEqualityTest ({ id, guildId ->
    val kord = mockKord()
    val data = mockk<IntegrationData>()
    every { data.id } returns id
    every { data.guildId } returns guildId
    Integration(data, kord)
})