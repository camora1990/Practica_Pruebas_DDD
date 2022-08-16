package co.com.sofka.comercial.bodega;

import co.com.sofka.business.generic.UseCaseHandler;
import co.com.sofka.business.repository.DomainEventRepository;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.comercial.bodega.commands.AsignarBodeguero;
import co.com.sofka.comercial.bodega.events.BodegaCreada;
import co.com.sofka.comercial.bodega.events.BodegueroAsignado;
import co.com.sofka.comercial.bodega.values.BodegaId;
import co.com.sofka.comercial.bodega.values.BodegueroId;
import co.com.sofka.comercial.bodega.values.Dimension;
import co.com.sofka.comercial.bodega.values.Salario;
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
class AsignarBodegueroUseCaseTest {
    @InjectMocks
    private AsignarBodegueroUseCase useCase;

    @Mock
    private DomainEventRepository repository;

    @Test
    void asignarBodegueroHappyPass() {
        //arrange
        BodegaId bodegaId = BodegaId.of("fakeBodegaID");
        Nombre nombre = new Nombre("Nombre # 1", "apellido #1");
        Salario salario = new Salario("COP",1500000D);
        var command = new AsignarBodeguero(bodegaId,nombre,salario);

        when(repository.getEventsBy("fakeBodegaID")).thenReturn(history());

        var events = UseCaseHandler.getInstance()
                .setIdentifyExecutor(command.getBodegaId().value())
                .syncExecutor(useCase, new RequestCommand<>(command))
                .orElseThrow()
                .getDomainEvents();

        //act

        var event = (BodegueroAsignado) events.get(0);

        //assert

        Assertions.assertEquals("Nombre # 1",event.getNombre().value().nombre());
        Assertions.assertEquals("apellido #1",event.getNombre().value().apellido());

    }

    private List<DomainEvent> history() {
        var dimension = new Dimension(2.8F, 2.5F, 2.8F);
        var event = new BodegaCreada(
                dimension);
        event.setAggregateRootId("asdasfasdf");
        return List.of(event);
    }

}