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

    // READ - por correo
    @GetMapping("/{correo}")
    public Usuario buscarPorCorreo(@PathVariable String correo) {
        return usuarioService.buscarPorCorreo(correo);
    }

    // UPDATE
    @PutMapping("/{correo}")
    public Usuario actualizar(
            @PathVariable String correo, 
            @RequestBody UsuarioDTO usuarioDTO) {

        return usuarioService.actualizar(correo, usuarioDTO);
    }

    // DELETE
    @DeleteMapping("/{correo}")
    public void eliminar(@PathVariable String correo) {
        usuarioService.eliminar(correo);
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginDTO loginDTO) {
        return usuarioService.login(loginDTO.getCorreo(), loginDTO.getPassword());
    }
}