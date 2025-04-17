package service_order_api.service_order_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import service_order_api.service_order_api.entity.OrdemDeServico;


@Repository
public interface OrdemDeServicoRepository extends JpaRepository<OrdemDeServico , Long> {
}
