package pe.com.sinergiacafe.calidad.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IncidenciaResponseDTO {

    private Long idIncidencia;
    private TipoIncidenciaResponseDTO tipoIncidencia;
    private Long idEmpleado;
    private LocalDateTime fechaIncidencia;
    private String area;
    private String descripcion;
    private String estado;
}