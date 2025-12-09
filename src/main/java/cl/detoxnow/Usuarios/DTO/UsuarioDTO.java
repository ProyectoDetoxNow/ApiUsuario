package cl.detoxnow.usuarios.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class UsuarioDTO {

    @Schema(description = "Nombre del usuario")
    private String nombre;

    @Schema(description = "Correo del usuario (debe ser único)")
    private String correo;

    @Schema(description = "Contraseña del usuario")
    private String password;

    @Schema(description = "Telefono")
    private String telefono;

    @Schema(description = "Dirección")
    private String direccion;

    @Schema(description = "Región")
    private String region;

    @Schema(description = "Comuna")
    private String comuna;
}