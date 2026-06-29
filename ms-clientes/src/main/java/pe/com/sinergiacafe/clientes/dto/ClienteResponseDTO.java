package pe.com.sinergiacafe.clientes.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClienteResponseDTO {

    private Long idCliente;
    private TipoClienteResponseDTO tipoCliente;
    private String nombres;
    private String apellidos;
    private String dni;
    private String telefono;
    private String correo;
    private LocalDate fechaNacimiento;
    private LocalDateTime fechaRegistro;
    private Boolean activo;
}