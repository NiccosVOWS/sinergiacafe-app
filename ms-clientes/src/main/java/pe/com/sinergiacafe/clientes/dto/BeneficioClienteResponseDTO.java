package pe.com.sinergiacafe.clientes.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BeneficioClienteResponseDTO {

    private Long idBeneficioCliente;
    private Long idCliente;
    private String nombreCliente;
    private ProgramaFidelizacionResponseDTO programaFidelizacion;
    private Integer puntosAcumulados;
    private Integer puntosCanjeados;
    private Integer puntosDisponibles;
    private LocalDateTime ultimaActualizacion;
}