package pe.com.sinergiacafe.calidad.dto;

import lombok.Builder;
import lombok.Data;
import pe.com.sinergiacafe.calidad.entity.EstadoIncidencia;

import java.time.LocalDateTime;

@Data
@Builder
public class IncidenciaResponseDTO {

    private Long idIncidencia;

    private TipoIncidenciaSimpleDTO tipoIncidencia;

    private Long idEmpleado;

    private LocalDateTime fechaIncidencia;

    private String area;

    private String descripcion;

    private EstadoIncidencia estado;
}