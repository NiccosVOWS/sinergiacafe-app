package pe.com.sinergiacafe.abastecimiento.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContratoProveedorRequestDTO {

    @NotNull(message = "El proveedor es obligatorio")
    private Long idProveedor;

    @NotNull(message = "La fecha de inicio es obligatoria")
    private LocalDate fechaInicio;

    private LocalDate fechaFin;

    @Size(max = 300, message = "Las condiciones no pueden superar 300 caracteres")
    private String condiciones;
}