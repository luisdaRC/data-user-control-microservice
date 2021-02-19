package co.edu.unicartagena.control.domain.entities;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
public class PersonalApoyo {

    public PersonalApoyo(){}

    private Integer id;
    private PropiedadHorizontal idPropiedad;
    private String email;
    private String pass;
    private Boolean estado;
    private String rol;
    private String nombres;
    private String numeroDocumento;
    private String tipoDocumento;

}
