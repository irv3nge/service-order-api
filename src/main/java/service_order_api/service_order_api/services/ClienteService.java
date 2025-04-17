package service_order_api.service_order_api.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import service_order_api.service_order_api.dtos.ClienteDTO;
import service_order_api.service_order_api.entity.Cliente;
import service_order_api.service_order_api.repository.ClienteRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public List<Cliente>listarTodos(){
        return clienteRepository.findAll();
    }
    public void excluir(Long id){
        clienteRepository.deleteById(id);
    }
    public Optional<Cliente> buscarPorId(Long id) {
        return clienteRepository.findById(id);
    }

    public Cliente salvar(ClienteDTO dto) {
        Cliente cliente = new Cliente();
        BeanUtils.copyProperties(dto, cliente);
        return clienteRepository.save(cliente);
    }
}
