package co.com.sofka.comercial.tienda;


import co.com.sofka.business.repository.DomainEventRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
class AsignarEncargadoUseCaseTest {
    @InjectMocks
    AsignarEncargadoUseCase useCase;

    @Mock
    private DomainEventRepository repository;

    @Test
    public void asignarEncargadoHappyPass() {


        //act


        //assert


    }
}