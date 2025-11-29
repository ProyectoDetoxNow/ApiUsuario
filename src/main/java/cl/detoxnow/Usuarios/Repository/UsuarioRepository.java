package cl.detoxnow.Usuarios.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import cl.detoxnow.Usuarios.Model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, String> {
    boolean existsByCorreo(String correo);
}
