package service_order_api.service_order_api.controller;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import service_order_api.service_order_api.entity.Usuario;
import service_order_api.service_order_api.repository.UsuarioRepository;
import service_order_api.service_order_api.security.AuthRequest;
import service_order_api.service_order_api.security.JwtUtil;

import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UsuarioRepository usuarioRepo;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    @PostConstruct
    public void criaUsuarioAdmin() {
        if (usuarioRepo.findByLogin("admin").isEmpty()) {
            Usuario u = new Usuario();
            u.setLogin("admin");
            u.setSenha(passwordEncoder.encode("123456"));
            u.setRole("ADMIN");
            usuarioRepo.save(u);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {
        System.out.println("🔒 ENTROU NO MÉTODO /auth/login");
        System.out.println("📥 Login recebido: " + request.getLogin());
        System.out.println("📥 Senha recebida: " + request.getSenha());

        Usuario usuario = usuarioRepo.findByLogin(request.getLogin())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        System.out.println("Usuário encontrado: " + usuario.getLogin());

        if (!passwordEncoder.matches(request.getSenha(), usuario.getSenha())) {
            System.out.println("Senha incorreta!");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Senha inválida");
        }

        String token = jwtUtil.generateToken(usuario.getLogin());
        System.out.println("Token gerado: " + token);
        return ResponseEntity.ok(Map.of("token", token));
    }

    @GetMapping("/protegido")
    public ResponseEntity<String>protegido(){
        return ResponseEntity.ok("✅ Você acessou uma rota protegida com JWT!");
    }

}
