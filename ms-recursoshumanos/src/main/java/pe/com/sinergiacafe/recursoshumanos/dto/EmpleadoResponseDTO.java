package pe.com.sinergiacafe.recursoshumanos.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmpleadoResponseDTO {

    private Long idEmpleado;
    private CargoResponseDTO cargo;
    private TurnoResponseDTO turno;
    private String nombres;
    private String apellidos;
    private String dni;
    private String telefono;
    private String correo;
    private LocalDate fechaIngreso;
    private String estadoLaboral;
}