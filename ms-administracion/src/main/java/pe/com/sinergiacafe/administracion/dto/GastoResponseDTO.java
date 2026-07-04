package pe.com.sinergiacafe.administracion.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
public class GastoResponseDTO {
    private Long idGasto;
    private CategoriaGastoResponseDTO categoriaGasto;
    private LocalDate fechaGasto;
    private BigDecimal monto;
    private String descripcion;
}
