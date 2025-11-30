package cl.detoxnow.usuarios.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.detoxnow.usuarios.dto.UsuarioDTO;
import cl.detoxnow.usuarios.model.Usuario;
import cl.detoxnow.usuarios.service.UsuarioService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping
    public Usuario registrar(@RequestBody UsuarioDTO usuarioDTO) {
        return usuarioService.registrar(usuarioDTO);
    }
    
}
