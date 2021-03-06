package dev.jombi.kordsb.core.entity

import dev.jombi.kordsb.core.cache.data.EmojiData
import equality.GuildEntityEqualityTest
import io.mockk.every
import io.mockk.mockk
import mockKord

internal class GuildEmojiTest : GuildEntityEqualityTest<GuildEmoji> by GuildEntityEqualityTest({ id, guildId ->
    val kord = mockKord()
    val data = mockk<EmojiData>()
    every { data.id } returns id
    every { data.guildId } returns guildId
    GuildEmoji(data, kord)
})