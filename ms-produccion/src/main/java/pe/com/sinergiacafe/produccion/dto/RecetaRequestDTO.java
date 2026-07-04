package pe.com.sinergiacafe.produccion.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecetaRequestDTO {

    @NotNull(message = "El producto es obligatorio")
    private Long idProducto;

    @NotBlank(message = "Las instrucciones son obligatorias")
    private String instrucciones;

    @NotNull(message = "El tiempo de preparación es obligatorio")
    @Min(value = 1, message = "El tiempo de preparación debe ser al menos 1 minuto")
    private Integer tiempoPrepMin;
}