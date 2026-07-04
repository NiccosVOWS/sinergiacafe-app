package pe.com.sinergiacafe.calidad.dto;

import lombok.Builder;
import lombok.Data;
import pe.com.sinergiacafe.calidad.entity.ResultadoGeneralInspeccion;

import java.time.LocalDateTime;

@Data
@Builder
public class InspeccionResponseDTO {

    private Long idInspeccion;
    private Long idEmpleado;
    private LocalDateTime fechaInspeccion;
    private String areaInspeccionada;
    private ResultadoGeneralInspeccion resultadoGeneral;
}
