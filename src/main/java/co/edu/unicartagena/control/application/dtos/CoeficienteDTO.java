package co.edu.unicartagena.control.application.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class CoeficienteDTO {

    private final String tipoDocumento;
    private final String numeroDocumento;
    private final Float coeficiente;
}
