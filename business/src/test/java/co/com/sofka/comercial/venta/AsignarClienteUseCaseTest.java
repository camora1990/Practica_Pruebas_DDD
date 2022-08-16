package co.com.sofka.comercial.venta;


import co.com.sofka.business.generic.UseCaseHandler;
import co.com.sofka.business.repository.DomainEventRepository;

import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.comercial.tienda.values.TiendaId;
import co.com.sofka.comercial.venta.commands.AsignarCliente;
import co.com.sofka.comercial.venta.events.ClienteAsignado;
import co.com.sofka.comercial.venta.events.VentaCreada;
import co.com.sofka.comercial.venta.values.Telefono;
import co.com.sofka.comercial.venta.values.Total;
import co.com.sofka.comercial.venta.values.VentaId;
import co.com.sofka.domain.generic.DomainEvent;
import co.com.sofka.generic.values.Fecha;
import co.com.sofka.generic.values.Nombre;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class AsignarClienteUseCaseTest {
    @InjectMocks
    AsignarClienteUseCase useCase;

    @Mock
    private DomainEventRepository repository;
    @Test
    public void asignarClienteHappyPass() {
        var ventaId = VentaId.of("zzzz");
        var nombre = new Nombre("Lidia", "Bustamante");
        var telefono = new Telefono("3203331111");
        var command = new AsignarCliente(ventaId, nombre, telefono);

        when(repository.getEventsBy("zzzz")).thenReturn(history());
        useCase.addRepository(repository);
//act
        var events = UseCaseHandler.getInstance()
                .setIdentifyExecutor(command.getVentaId().value())
                .syncExecutor(useCase, new RequestCommand<>(command))
                .orElseThrow()
                .getDomainEvents();
//assert
        var event = (ClienteAsignado) events.get(0);
        Assertions.assertEquals("Lidia", event.getNombre().value().nombre());
        Assertions.assertEquals("Bustamante", event.getNombre().value().apellido());
        Assertions.assertEquals("3203331111", event.getTelefono().value());
    }

    private List<DomainEvent> history() {
        var tiendaId = TiendaId.of("zzzz");
        var fecha = new Fecha(LocalDateTime.of(2022, 05, 20, 9, 50));
        var total = new Total(200000D);
        var event = new VentaCreada(
                tiendaId,
                fecha,
                total);
        event.setAggregateRootId("zzzz");
        return List.of(event);
    }

}


