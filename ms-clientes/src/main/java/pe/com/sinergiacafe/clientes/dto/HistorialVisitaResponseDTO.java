package pe.com.sinergiacafe.clientes.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HistorialVisitaResponseDTO {

    private Long idHistorialVisita;
    private Long idCliente;
    private String nombreCliente;
    private LocalDateTime fechaVisita;
    private BigDecimal consumoTotal;
    private String observaciones;
}