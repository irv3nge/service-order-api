package service_order_api.service_order_api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String email;
    private String cpfOuCnpj;
    private String telefone;
    private String endereco;

    @OneToMany(mappedBy = "cliente" , cascade = CascadeType.ALL)
    private List<OrdemDeServico> orders = new ArrayList<>();
}
