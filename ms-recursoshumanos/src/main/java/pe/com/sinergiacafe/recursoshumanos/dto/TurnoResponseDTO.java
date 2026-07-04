package pe.com.sinergiacafe.recursoshumanos.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TurnoResponseDTO {

    private Long id;
    private String nombre;
    private java.time.LocalTime horaInicio;
    private java.time.LocalTime horaFin;
}
