package pe.com.sinergiacafe.mantenimiento.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TipoEquipoResponseDTO {

    private Long idTipoEquipo;
    private String nombre;
    private String descripcion;
}