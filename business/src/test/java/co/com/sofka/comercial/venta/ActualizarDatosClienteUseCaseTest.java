package co.com.sofka.comercial.venta;

import co.com.sofka.business.repository.DomainEventRepository;
import co.com.sofka.comercial.venta.commands.ActualizarDatosCliente;
import co.com.sofka.comercial.venta.values.ClienteId;
import co.com.sofka.comercial.venta.values.VentaId;
import co.com.sofka.generic.values.Nombre;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;



@ExtendWith(MockitoExtension.class)
class ActualizarDatosClienteUseCaseTest {
    @InjectMocks
    ActualizarDatosClienteUseCase useCase;

    @Mock
    private DomainEventRepository repository;

    @Test
    void actualizarDatosClienteHappyPass() {
        //arrange
        var ventaId = VentaId.of("bbbb");
        var entityId = ClienteId.of("0");
        var nombre = new Nombre("Hernan", "Cuy");
        var command = new ActualizarDatosCliente(ventaId, entityId, nombre);



        //act


        //assert

    }


    }

