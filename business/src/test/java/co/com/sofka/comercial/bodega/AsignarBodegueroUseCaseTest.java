package co.com.sofka.comercial.bodega;

import co.com.sofka.business.repository.DomainEventRepository;
import co.com.sofka.comercial.bodega.events.BodegaCreada;
import co.com.sofka.comercial.bodega.values.Dimension;
import co.com.sofka.domain.generic.DomainEvent;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;



@ExtendWith(MockitoExtension.class)
class AsignarBodegueroUseCaseTest {
    @InjectMocks
    private AsignarBodegueroUseCase useCase;

    @Mock
    private DomainEventRepository repository;

    @Test
    void asignarBodegueroHappyPass() {
        //arrange


        //act


        //assert

    }

    private List<DomainEvent> history() {
        var dimension = new Dimension(2.8F, 2.5F, 2.8F);
        var event = new BodegaCreada(
                dimension);
        event.setAggregateRootId("xxxx");
        return List.of(event);
    }

}