package gateway

import dev.jombi.kordsb.core.gateway.DefaultMasterGateway
import dev.jombi.kordsb.gateway.Command
import dev.jombi.kordsb.gateway.Event
import dev.jombi.kordsb.gateway.Gateway
import dev.jombi.kordsb.gateway.GatewayConfiguration
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext
import kotlin.time.Duration
import kotlin.time.Duration.Companion.milliseconds

internal class DefaultMasterGatewayTest {

    @Test
    fun `Gateway takes ping of single child`() {
        val dummy = DummyGateway()
        val ping = 150.milliseconds

        dummy.ping.value = ping

        val gateway = DefaultMasterGateway(
            dummy
        )

        assertEquals(dummy.ping.value, gateway.averagePing)
    }

    @Test
    fun `Gateway takes ping average of multiple children`() {
        val dummy1 = DummyGateway()
        val dummy2 = DummyGateway()
        val ping1 = 150.milliseconds
        val ping2 = 200.milliseconds

        dummy1.ping.value = ping1
        dummy2.ping.value = ping2

        val gateway = DefaultMasterGateway(
            dummy1
            //mapOf(0 to dummy1, 1 to dummy2) // sorry, but self-bot api doesn't support multiple gateway!
        )

        assertEquals(150.milliseconds, gateway.averagePing)
    }

    @Test
    fun `Gateway returns null ping when no gateway pings`(){

        val dummy = DummyGateway()

        val gateway = DefaultMasterGateway(
            dummy
        )

        assertEquals(null, gateway.averagePing)
    }


    private class DummyGateway : Gateway {
        override val events: SharedFlow<Event>
            get() = MutableSharedFlow()

        override val ping: MutableStateFlow<Duration?> = MutableStateFlow(null)

        override val coroutineContext: CoroutineContext
            get() = EmptyCoroutineContext

        override suspend fun detach() {}

        override suspend fun send(command: Command) {}

        override suspend fun start(configuration: GatewayConfiguration) {}

        override suspend fun stop() {}

    }

}
