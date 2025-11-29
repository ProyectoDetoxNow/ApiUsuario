package cl.detoxnow.Usuarios.Service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import cl.detoxnow.Usuarios.DTO.UsuarioDTO;
import cl.detoxnow.Usuarios.Model.Usuario;
import cl.detoxnow.Usuarios.Repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository repository;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public Usuario registrar(UsuarioDTO dto) {
        
        if (repository.existsByCorreo(dto.getCorreo())) {
            throw new RuntimeException("El correo ya está registrado");
        }

        Usuario usuario = new Usuario(
            dto.getCorreo(),
            dto.getNombre(),
            encoder.encode(dto.getPassword()),
            dto.getTelefono(),
            dto.getDireccion(),
            dto.getRegion(),
            dto.getComuna()
        );
        return repository.save(usuario);
    }
    
}
