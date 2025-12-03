package cl.detoxnow.usuarios.service;

import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import cl.detoxnow.usuarios.dto.LoginResponse;
import cl.detoxnow.usuarios.dto.UsuarioDTO;
import cl.detoxnow.usuarios.model.Usuario;
import cl.detoxnow.usuarios.repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository repository;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    // CREATE
    public Usuario registrar(UsuarioDTO dto) {

        if (repository.existsByCorreo(dto.getCorreo())) {
            throw new RuntimeException("El correo ya está registrado");
        }

        Usuario usuario = new Usuario(
                null, // ID autogenerado
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

    // READ - listar todos
    public List<Usuario> listar() {
        return repository.findAll();
    }

    // READ - buscar por ID
    public Usuario buscar(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    // READ - buscar por correo (para login y validaciones)
    public Usuario buscarPorCorreo(String correo) {
        Usuario usuario = repository.findByCorreo(correo);
        if (usuario == null) {
            throw new RuntimeException("Usuario no encontrado");
        }
        return usuario;
    }

    // UPDATE
    public Usuario actualizar(Long id, UsuarioDTO dto) {

        Usuario usuario = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        usuario.setNombre(dto.getNombre());
        usuario.setTelefono(dto.getTelefono());
        usuario.setDireccion(dto.getDireccion());
        usuario.setRegion(dto.getRegion());
        usuario.setComuna(dto.getComuna());

        if (dto.getPassword() != null && !dto.getPassword().isBlank()) {
            usuario.setPassword(encoder.encode(dto.getPassword()));
        }

        return repository.save(usuario);
    }

    // DELETE
    public void eliminar(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Usuario no existe");
        }
        repository.deleteById(id);
    }

    // LOGIN
    public LoginResponse login(String correo, String password) {

        Usuario usuario = buscarPorCorreo(correo);

        if (!encoder.matches(password, usuario.getPassword())) {
            return new LoginResponse(false, "Contraseña incorrecta");
        }

        return new LoginResponse(true, "Login exitoso");
    }
}