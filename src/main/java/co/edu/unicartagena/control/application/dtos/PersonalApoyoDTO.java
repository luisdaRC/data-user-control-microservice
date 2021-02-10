package co.edu.unicartagena.control.application.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class PersonalApoyoDTO {

    private final int idPropiedad;
    private final String email;
    private final String pass;
    private final Boolean estado;
    private final String rol;
    private final String nombres;
    private final String numeroDocumento;
    private final String tipoDocumento;
}
