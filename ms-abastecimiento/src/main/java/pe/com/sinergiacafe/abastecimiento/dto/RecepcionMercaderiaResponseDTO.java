package pe.com.sinergiacafe.abastecimiento.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecepcionMercaderiaResponseDTO {

    private Long idRecepcionMercaderia;
    private OrdenCompraResponseDTO ordenCompra;
    private Long idEmpleado;
    private LocalDateTime fechaRecepcion;
    private Boolean conforme;
    private String observaciones;
}