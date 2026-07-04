package pe.com.sinergiacafe.mantenimiento.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EquipoResponseDTO {

    private Long id;
    private Long idTipoEquipo;
    private String nombre;
    private String modelo;
    private java.time.LocalDate fechaAdquisicion;
    private String estado;
}
