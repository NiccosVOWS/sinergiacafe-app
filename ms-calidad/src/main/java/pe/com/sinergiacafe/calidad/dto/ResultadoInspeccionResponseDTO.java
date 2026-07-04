package pe.com.sinergiacafe.calidad.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResultadoInspeccionResponseDTO {

    private Long idResultadoInspeccion;

    private InspeccionSimpleDTO inspeccion;

    private EstandarCalidadSimpleDTO estandarCalidad;

    private Boolean cumple;

    private String observaciones;
}