package pe.com.sinergiacafe.mantenimiento.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EquipoResponseDTO {

    private Long idEquipo;
    private TipoEquipoResponseDTO tipoEquipo;
    private String nombre;
    private String modelo;
    private LocalDate fechaAdquisicion;
    private String estado;
}