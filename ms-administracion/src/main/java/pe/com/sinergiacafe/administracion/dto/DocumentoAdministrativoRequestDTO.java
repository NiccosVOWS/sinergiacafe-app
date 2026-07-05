package pe.com.sinergiacafe.administracion.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DocumentoAdministrativoRequestDTO {

    @NotBlank(message = "El tipo de documento es obligatorio")
    @Size(max = 80, message = "El tipo de documento no puede superar 80 caracteres")
    private String tipoDocumento;

    @NotNull(message = "La fecha del documento es obligatoria")
    private LocalDate fechaDocumento;

    @NotBlank(message = "La descripción es obligatoria")
    @Size(max = 300, message = "La descripción no puede superar 300 caracteres")
    private String descripcion;

    @NotNull(message = "El empleado es obligatorio")
    private Long idEmpleado;
}