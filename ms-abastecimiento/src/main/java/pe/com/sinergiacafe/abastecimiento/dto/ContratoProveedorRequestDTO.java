package pe.com.sinergiacafe.abastecimiento.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContratoProveedorRequestDTO {

    @NotNull
    private Long proveedorId;
    private java.time.LocalDate fechaInicio;
    private java.time.LocalDate fechaFin;
    private String condiciones;
    private String productosPactados;
}
