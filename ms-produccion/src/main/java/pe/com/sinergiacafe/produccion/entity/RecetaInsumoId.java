package pe.com.sinergiacafe.produccion.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class RecetaInsumoId implements Serializable {

    private Long idReceta;
    private Long idInsumo;
}