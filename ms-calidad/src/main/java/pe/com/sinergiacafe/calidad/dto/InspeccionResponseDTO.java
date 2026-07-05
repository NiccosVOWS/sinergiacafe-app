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
public class InspeccionResponseDTO {

    private Long idInspeccion;
    private Long idEmpleado;
    private LocalDateTime fechaInspeccion;
    private String areaInspeccionada;
    private String resultadoGeneral;
}