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

    private final Integer idPropiedadHorizontal;
    private final Boolean estado;
    private final String rol;
    private final DataPersonalDTO dataPersonal;
}
