package service_order_api.service_order_api.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import service_order_api.service_order_api.dtos.ClienteDTO;
import service_order_api.service_order_api.entity.Cliente;
import service_order_api.service_order_api.repository.ClienteRepository;
import service_order_api.service_order_api.services.ClienteService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClienteServiceTest {

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteService clienteService;

    @Test
    void deveSalvarClienteComSucesso() {
        ClienteDTO dto = new ClienteDTO();
        dto.setNome("Pedro");
        dto.setEmail("pedro@email.com");

        Cliente salvo = new Cliente();
        salvo.setId(1L);
        salvo.setNome("Pedro");

        when(clienteRepository.save(any())).thenReturn(salvo);

        Cliente resultado = clienteService.salvar(dto);

        assertNotNull(resultado);
        assertEquals("Pedro", resultado.getNome());
        verify(clienteRepository, times(1)).save(any());
    }
}
