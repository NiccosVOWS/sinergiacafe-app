package pe.com.sinergiacafe.recursoshumanos.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AsistenciaResponseDTO {

    private Long id;
    private Long empleadoId;
    private java.time.LocalDate fecha;
    private java.time.LocalTime horaEntrada;
    private java.time.LocalTime horaSalida;
    private String observaciones;
}
