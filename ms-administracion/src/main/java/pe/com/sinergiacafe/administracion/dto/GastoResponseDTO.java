package pe.com.sinergiacafe.administracion.dto;

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
public class GastoResponseDTO {

    private Long idGasto;
    private CategoriaGastoResponseDTO categoriaGasto;
    private LocalDate fechaGasto;
    private BigDecimal monto;
    private String descripcion;
}