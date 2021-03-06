package dev.jombi.kordsb.core.entity

import dev.jombi.kordsb.core.cache.data.AttachmentData
import equality.EntityEqualityTest
import io.mockk.every
import io.mockk.mockk
import mockKord

internal class AttachmentTest : EntityEqualityTest<Attachment> by EntityEqualityTest({
    val kord = mockKord()
    val data = mockk<AttachmentData>()
    every { data.id } returns it
    Attachment(data, kord)
})
