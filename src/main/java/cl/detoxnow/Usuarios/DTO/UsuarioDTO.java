package cl.detoxnow.Usuarios.DTO;

import lombok.Data;

@Data
public class UsuarioDTO {

    private String nombre;
    private String correo;
    private String password;
    private String telefono;
    private String direccion;
    private String region;
    private String comuna;
    
}
