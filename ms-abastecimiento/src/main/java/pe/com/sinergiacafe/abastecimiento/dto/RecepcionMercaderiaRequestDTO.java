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
public class RecepcionMercaderiaRequestDTO {

    @NotNull(message = "La orden de compra es obligatoria")
    private Long idOrdenCompra;

    @NotNull(message = "El empleado es obligatorio")
    private Long idEmpleado;

    private Boolean conforme;

    @Size(max = 300, message = "Las observaciones no pueden superar 300 caracteres")
    private String observaciones;
}