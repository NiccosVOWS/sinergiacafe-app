package pe.com.sinergiacafe.clientes.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProgramaFidelizacionResponseDTO {

    private Long idProgramaFidelizacion;
    private String nombre;
    private String descripcion;
    private BigDecimal puntosPorSol;
    private Integer minPuntosCanje;
    private Boolean activo;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
}