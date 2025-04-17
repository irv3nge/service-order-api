package service_order_api.service_order_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import service_order_api.service_order_api.entity.Usuario;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario , Long> {

    Optional<Usuario> findByLogin(String login);
}
