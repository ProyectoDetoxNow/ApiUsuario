package cl.detoxnow.usuarios.dto;

import cl.detoxnow.usuarios.model.Usuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {

    private boolean success;
    private String mensaje;
    private Usuario usuario;
}
