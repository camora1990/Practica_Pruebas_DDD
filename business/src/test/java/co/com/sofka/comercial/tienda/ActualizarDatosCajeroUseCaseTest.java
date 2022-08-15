package co.com.sofka.comercial.tienda;

import co.com.sofka.business.generic.UseCaseHandler;
import co.com.sofka.business.repository.DomainEventRepository;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.comercial.tienda.commands.ActualizarDatosCajero;
import co.com.sofka.comercial.tienda.events.*;
import co.com.sofka.comercial.tienda.values.*;
import co.com.sofka.domain.generic.DomainEvent;
import co.com.sofka.generic.values.Nombre;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ActualizarDatosCajeroUseCaseTest {
    @InjectMocks
    ActualizarDatosCajeroUseCase useCase;

    @Mock
    private DomainEventRepository repository;

    @Test
    void actualizarDatosCajeroHappyPass() {
        //arrange


        //act


        //assert

    }

    private List<DomainEvent> history() {
        var ubicacion = new Ubicacion("Cll tal # tal-tal", "Cúcuta", "Colombia");
        var event = new TiendaCreada(ubicacion);
        event.setAggregateRootId("cccc");

        var entityId = CajeroId.of("0");
        var nombre = new Nombre("Karen", "Manzano");
        var caja = new Caja(200000D);
        var event2 = new CajeroAsignado(entityId, nombre, caja);

        return List.of(event, event2);

    }

}