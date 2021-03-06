package dev.jombi.kordsb.common.entity.optional

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerializationException
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.intellij.lang.annotations.Language
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class OptionalBooleanTest {

    @Serializable
    private class EmptyOptionalEntity(val value: OptionalBoolean = OptionalBoolean.Missing)

    @Test
    fun `deserializing nothing in optional assigns Missing`(){
        @Language("json")
        val json = """{}"""

        val entity = Json.decodeFromString<EmptyOptionalEntity>(json)

        assert(entity.value is OptionalBoolean.Missing)
    }

    @Serializable
    private class NullOptionalEntity(@Suppress("unused") val value: OptionalBoolean = OptionalBoolean.Missing)

    @Test
    fun `deserializing null in optional throws SerializationException`(){
        @Language("json")
        val json = """{ "value":null }"""

        org.junit.jupiter.api.assertThrows<SerializationException> {
            Json.decodeFromString<NullOptionalEntity>(json)
        }
    }

    @Serializable
    private class ValueOptionalEntity(@Suppress("unused") val value: OptionalBoolean = OptionalBoolean.Missing)

    @Test
    fun `deserializing value in optional assigns Value`(){
        @Language("json")
        val json = """{ "value":true }"""


        val entity = Json.decodeFromString<ValueOptionalEntity>(json)
        require(entity.value is OptionalBoolean.Value)

        Assertions.assertEquals(true, entity.value.value)
    }

}
