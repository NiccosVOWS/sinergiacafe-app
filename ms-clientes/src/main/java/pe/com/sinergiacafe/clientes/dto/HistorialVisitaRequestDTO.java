package pe.com.sinergiacafe.clientes.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HistorialVisitaRequestDTO {

    @NotNull(message = "El cliente es obligatorio")
    private Long idCliente;

    @NotNull(message = "El consumo total es obligatorio")
    @PositiveOrZero(message = "El consumo total no puede ser negativo")
    private BigDecimal consumoTotal;

    private String observaciones;
}