package pe.com.sinergiacafe.abastecimiento.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrdenCompraRequestDTO {

    @NotNull(message = "El proveedor es obligatorio")
    private Long idProveedor;

    @NotNull(message = "El empleado es obligatorio")
    private Long idEmpleado;

    private String observaciones;
}