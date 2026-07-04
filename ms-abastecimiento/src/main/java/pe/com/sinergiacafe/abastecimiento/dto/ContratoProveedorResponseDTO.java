package pe.com.sinergiacafe.abastecimiento.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContratoProveedorResponseDTO {

    private Long id;
    private Long idProveedor;
    private java.time.LocalDate fechaInicio;
    private java.time.LocalDate fechaFin;
    private String condiciones;
    private Boolean activo;
}
