package cl.detoxnow.usuarios.dto;

import lombok.AllArgsConstructor;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponse {

    @Schema(description = "Indica si el login fue exitoso")
    private boolean success;

    @Schema(description = "Mensaje relacionado al resultado del login")
    private String mensaje;
    
}
