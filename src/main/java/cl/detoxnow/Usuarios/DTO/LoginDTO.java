package cl.detoxnow.usuarios.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;


@Data
public class LoginDTO {

    @Schema(description = "Correo del usuario")
    private String correo;

    @Schema(description = "Contraseña del usuario")
    private String password;
    
}
