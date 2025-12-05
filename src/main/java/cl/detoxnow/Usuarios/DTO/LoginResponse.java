package cl.detoxnow.usuarios.dto;

import cl.detoxnow.Usuarios.Model.Usuario;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {

    private boolean success;
    private String mensaje;
    private Usuario usuario;
    
}
