package pe.com.sinergiacafe.recursoshumanos.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmpleadoResponseDTO {

    private Long id;
    private Long idCargo;
    private Long idTurno;
    private String nombres;
    private String apellidos;
    private String dni;
    private String telefono;
    private String correo;
    private java.time.LocalDate fechaIngreso;
    private String estadoLaboral;
}
