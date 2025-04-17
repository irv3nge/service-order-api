package service_order_api.service_order_api.dtos;

import lombok.Data;

@Data
public class OrdemDeServicoDTO {
    private Long clienteId;
    private String descricao;
}
