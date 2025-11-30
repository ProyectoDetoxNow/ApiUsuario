package cl.detoxnow.usuarios.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.detoxnow.usuarios.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, String> {
    boolean existsByCorreo(String correo);
}
