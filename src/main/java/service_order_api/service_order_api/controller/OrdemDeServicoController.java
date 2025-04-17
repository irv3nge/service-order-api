package service_order_api.service_order_api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service_order_api.service_order_api.dtos.OrdemDeServicoDTO;
import service_order_api.service_order_api.entity.OrdemDeServico;
import service_order_api.service_order_api.services.OrdemDeServicoService;

import java.util.List;

@RestController
@RequestMapping("/api/ordens")
@RequiredArgsConstructor
public class OrdemDeServicoController {

    private final OrdemDeServicoService service;

    @PostMapping
    public ResponseEntity<OrdemDeServico> abrir(@RequestBody OrdemDeServicoDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.abrir(dto));
    }

    @GetMapping
    public List<OrdemDeServico> listar() {
        return service.listar();
    }
}
