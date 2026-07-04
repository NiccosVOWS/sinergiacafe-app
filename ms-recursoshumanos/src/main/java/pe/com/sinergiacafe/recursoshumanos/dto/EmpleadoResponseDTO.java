package pe.com.sinergiacafe.recursoshumanos.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmpleadoResponseDTO {

    private Long id;
    private String nombres;
    private String apellidos;
    private String dni;
    private Long cargoId;
    private java.time.LocalDate fechaIngreso;
    private String estado;
}
