package pe.com.sinergiacafe.abastecimiento.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecepcionMercaderiaRequestDTO {

    @NotNull
    private Long idOrdenCompra;
    @NotNull
    private Long idEmpleado;
    private Boolean conforme;
    private String observaciones;
}
