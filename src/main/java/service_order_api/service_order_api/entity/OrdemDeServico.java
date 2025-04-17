package service_order_api.service_order_api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrdemDeServico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    private String descricao;
    private LocalDateTime dataAbertura = LocalDateTime.now();
    private LocalDateTime dataFinalizacao;

    @Enumerated(EnumType.STRING)
    private StatusOrdem status = StatusOrdem.ABERTA;
}
