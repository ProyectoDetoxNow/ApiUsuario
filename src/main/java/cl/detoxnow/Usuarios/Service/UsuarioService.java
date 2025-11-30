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

    // READ - por correo
    public Usuario buscarPorCorreo(String correo) {
        return repository.findById(correo)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    // UPDATE
    public Usuario actualizar(String correo, UsuarioDTO dto) {

        Usuario usuario = buscarPorCorreo(correo);

        usuario.setNombre(dto.getNombre());
        usuario.setTelefono(dto.getTelefono());
        usuario.setDireccion(dto.getDireccion());
        usuario.setRegion(dto.getRegion());
        usuario.setComuna(dto.getComuna());

        // si viene password, actualizarla
        if (dto.getPassword() != null && !dto.getPassword().isBlank()) {
            usuario.setPassword(encoder.encode(dto.getPassword()));
        }

        return repository.save(usuario);
    }

    // DELETE
    public void eliminar(String correo) {
        if (!repository.existsById(correo)) {
            throw new RuntimeException("No existe un usuario con ese correo");
        }
        repository.deleteById(correo);
    }

    public LoginResponse login(String correo, String password) {

        Usuario usuario = repository.findById(correo)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (!encoder.matches(password, usuario.getPassword())) {
            return new LoginResponse(false, "Contraseña incorrecta");
        }
        return new LoginResponse(true, "Login exitoso");
    }

}