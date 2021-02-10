package co.edu.unicartagena.control.domain.entities;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@Builder
@ToString
@RequiredArgsConstructor
public class PersonalApoyo {

    private final int idPropiedad;
    private final String email;
    private final String pass;
    private final Boolean estado;
    private final String rol;
    private final String nombres;
    private final String numeroDocumento;
    private final String tipoDocumento;

}
