package pe.com.sinergiacafe.abastecimiento.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContratoProveedorResponseDTO {

    private Long idContratoProveedor;
    private ProveedorResponseDTO proveedor;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private String condiciones;
    private Boolean activo;
}