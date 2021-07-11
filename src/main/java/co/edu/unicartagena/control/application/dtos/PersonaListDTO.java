package co.edu.unicartagena.control.application.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class PersonaListDTO {

    private final Integer id;
    private final String nombres;
    private final String apellido;
    private final String numeroDocumento;
    private final String tipoDocumento;
    private final String rol;
    private final BienPrivadoDTO bienPrivado;
}
