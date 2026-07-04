package pe.com.sinergiacafe.administracion.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CategoriaGastoRequestDTO {

    @NotBlank(message = "El nombre es obligatorio.")
    @Size(max = 80, message = "El nombre no puede superar los 80 caracteres.")
    private String nombre;

    @Size(max = 200, message = "La descripción no puede superar los 200 caracteres.")
    private String descripcion;
}
