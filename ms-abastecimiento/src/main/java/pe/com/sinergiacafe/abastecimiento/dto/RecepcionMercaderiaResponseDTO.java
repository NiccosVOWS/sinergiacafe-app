package pe.com.sinergiacafe.abastecimiento.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecepcionMercaderiaResponseDTO {

    private Long id;
    private Long idOrdenCompra;
    private Long idEmpleado;
    private java.time.LocalDateTime fechaRecepcion;
    private Boolean conforme;
    private String observaciones;
}
