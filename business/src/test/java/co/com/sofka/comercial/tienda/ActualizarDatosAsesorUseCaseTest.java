package co.com.sofka.comercial.tienda;

import co.com.sofka.business.generic.UseCaseHandler;
import co.com.sofka.business.repository.DomainEventRepository;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.comercial.tienda.commands.ActualizarDatosAsesor;
import co.com.sofka.comercial.tienda.events.AsesorActualizado;
import co.com.sofka.comercial.tienda.events.AsesorAsignado;
import co.com.sofka.comercial.tienda.events.TiendaCreada;
import co.com.sofka.comercial.tienda.values.AsesorId;
import co.com.sofka.comercial.tienda.values.Comision;
import co.com.sofka.comercial.tienda.values.TiendaId;
import co.com.sofka.comercial.tienda.values.Ubicacion;
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
class ActualizarDatosAsesorUseCaseTest {
    @InjectMocks
    ActualizarDatosAsesorUseCase useCase;

    @Mock
    private DomainEventRepository repository;

    @Test
    void actualizarDatosAsesorHappyPass() {
        //arrange
        var tiendaId = TiendaId.of("fakeTiendaId");
        var idAsesor = AsesorId.of("fakeAsesorId");
        var nombre = new Nombre("Camilo","morales");
        var comando = new ActualizarDatosAsesor(tiendaId,idAsesor,nombre);

        when(repository.getEventsBy("fakeTiendaId")).thenReturn(history());

        useCase.addRepository(repository);

        var events = UseCaseHandler.getInstance()
                .setIdentifyExecutor(comando.getTiendaId().value())
                .syncExecutor(useCase, new RequestCommand<>(comando))
                .orElseThrow()
                .getDomainEvents();


        //act

        var event = (AsesorActualizado)events.get(0);
        //assert
        Assertions.assertEquals("Camilo",event.getNombre().value().nombre());

    }

    private List<DomainEvent> history() {
        var ubicacion = new Ubicacion("Cll tal # tal-tal", "Cúcuta", "Colombia");
        var event = new TiendaCreada(ubicacion);
        event.setAggregateRootId("cccc");

        var entityId = AsesorId.of("0");
        var nombre = new Nombre("Karen", "Manzano");
        var comision = new Comision(0.01F);
        var event2 = new AsesorAsignado(entityId, nombre, comision);

        return List.of(event, event2);

    }

}