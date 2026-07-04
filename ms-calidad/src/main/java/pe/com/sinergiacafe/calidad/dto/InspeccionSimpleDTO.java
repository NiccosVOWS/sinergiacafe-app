package pe.com.sinergiacafe.calidad.dto;

import lombok.Builder;
import lombok.Data;
import pe.com.sinergiacafe.calidad.entity.ResultadoGeneralInspeccion;

@Data
@Builder
public class InspeccionSimpleDTO {

    private Long idInspeccion;
    private String areaInspeccionada;
    private ResultadoGeneralInspeccion resultadoGeneral;
}