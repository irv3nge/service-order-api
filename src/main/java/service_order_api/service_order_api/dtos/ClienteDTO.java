package service_order_api.service_order_api.dtos;

import lombok.Data;

@Data
public class ClienteDTO {
    private String nome;
    private String email;
    private String cpfOuCnpj;
    private String telefone;
    private String endereco;

}
