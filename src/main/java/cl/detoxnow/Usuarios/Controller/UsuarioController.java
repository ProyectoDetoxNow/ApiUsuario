package cl.detoxnow.usuarios.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;  
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.detoxnow.usuarios.dto.LoginDTO;
import cl.detoxnow.usuarios.dto.LoginResponse;
import cl.detoxnow.usuarios.dto.UsuarioDTO;
import cl.detoxnow.usuarios.model.Usuario;
import cl.detoxnow.usuarios.service.UsuarioService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    // CREATE
    @PostMapping
    public Usuario registrar(@RequestBody UsuarioDTO usuarioDTO) {
        return usuarioService.registrar(usuarioDTO);
    }

    // READ - listar todos
    @GetMapping
    public List<Usuario> listar() {
        return usuarioService.listar();
    }

    // READ - por ID
    @GetMapping("/{id}")
    public Usuario buscar(@PathVariable Long id) {
        return usuarioService.buscar(id);
    }

    // UPDATE - por ID
    @PutMapping("/{id}")
    public Usuario actualizar(
            @PathVariable Long id, 
            @RequestBody UsuarioDTO usuarioDTO) {

        return usuarioService.actualizar(id, usuarioDTO);
    }

    // DELETE - por ID
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        usuarioService.eliminar(id);
    }

    // LOGIN
    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginDTO loginDTO) {
        return usuarioService.login(loginDTO.getCorreo(), loginDTO.getPassword());
    }
}