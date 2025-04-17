package service_order_api.service_order_api.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import service_order_api.service_order_api.dtos.OrdemDeServicoDTO;
import service_order_api.service_order_api.entity.Cliente;
import service_order_api.service_order_api.entity.OrdemDeServico;
import service_order_api.service_order_api.entity.StatusOrdem;
import service_order_api.service_order_api.repository.ClienteRepository;
import service_order_api.service_order_api.repository.OrdemDeServicoRepository;
import service_order_api.service_order_api.services.OrdemDeServicoService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrdemDeServicoServiceTest {

    @Mock
    private OrdemDeServicoRepository osRepository;

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private OrdemDeServicoService osService;

    @Test
    void deveAbrirOrdemComClienteExistente() {
        OrdemDeServicoDTO dto = new OrdemDeServicoDTO();
        dto.setClienteId(1L);
        dto.setDescricao("Trocar HD");

        Cliente cliente = new Cliente();
        cliente.setId(1L);
        cliente.setNome("João");

        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));
        when(osRepository.save(any())).thenAnswer(invoc -> invoc.getArgument(0));

        OrdemDeServico os = osService.abrir(dto);

        assertNotNull(os);
        assertEquals("Trocar HD", os.getDescricao());
        assertEquals(StatusOrdem.ABERTA, os.getStatus());
        verify(osRepository).save(any());
    }

    @Test
    void deveFalharSeClienteNaoExiste() {
        OrdemDeServicoDTO dto = new OrdemDeServicoDTO();
        dto.setClienteId(999L);
        dto.setDescricao("Trocar fonte");

        when(clienteRepository.findById(999L)).thenReturn(Optional.empty());

        Exception ex = assertThrows(RuntimeException.class, () -> osService.abrir(dto));

        assertEquals("Cliente não encontrado", ex.getMessage());
    }
}

