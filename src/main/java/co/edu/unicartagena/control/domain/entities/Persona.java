package co.edu.unicartagena.control.domain.entities;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@Builder
@ToString
@RequiredArgsConstructor
public class Persona {

    private final int idPersona;
    private final int idBienPrivado;
    private final String nombres;
    private final String apellidos;
    private final String numeroDocumento;
    private final String tipoDocumento;
    private final String rol;
}
