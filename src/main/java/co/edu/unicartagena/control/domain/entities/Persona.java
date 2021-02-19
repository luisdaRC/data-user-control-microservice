package co.edu.unicartagena.control.domain.entities;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
public class Persona {

    public Persona(){}

    private Integer idPersona;
    private BienPrivado idBienPrivado;
    private String nombres;
    private String apellidos;
    private String numeroDocumento;
    private String tipoDocumento;
    private String rol;
}
