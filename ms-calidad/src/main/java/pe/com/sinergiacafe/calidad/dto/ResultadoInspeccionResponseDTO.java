package pe.com.sinergiacafe.calidad.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResultadoInspeccionResponseDTO {

    private Long idResultadoInspeccion;
    private InspeccionResponseDTO inspeccion;
    private EstandarCalidadResponseDTO estandarCalidad;
    private Boolean cumple;
    private String observaciones;
}