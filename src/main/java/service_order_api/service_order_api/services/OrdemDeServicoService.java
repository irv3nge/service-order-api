package service_order_api.service_order_api.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import service_order_api.service_order_api.dtos.OrdemDeServicoDTO;
import service_order_api.service_order_api.entity.Cliente;
import service_order_api.service_order_api.entity.OrdemDeServico;
import service_order_api.service_order_api.entity.StatusOrdem;
import service_order_api.service_order_api.repository.ClienteRepository;
import service_order_api.service_order_api.repository.OrdemDeServicoRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrdemDeServicoService {
    private final OrdemDeServicoRepository osRepository;
    private final ClienteRepository clienteRepository;

    public OrdemDeServico abrir(OrdemDeServicoDTO dto) {
        Cliente cliente = clienteRepository.findById(dto.getClienteId())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        OrdemDeServico os = new OrdemDeServico();
        os.setCliente(cliente);
        os.setDescricao(dto.getDescricao());
        os.setStatus(StatusOrdem.ABERTA);
        os.setDataAbertura(LocalDateTime.now());
        return osRepository.save(os);
    }

    public List<OrdemDeServico> listar() {
        return osRepository.findAll();
    }
}
