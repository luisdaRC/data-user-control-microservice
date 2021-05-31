package co.edu.unicartagena.control.application.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class RestriccionDTO {

    private final String idPropiedadHorizontal;
    private final Boolean consejo;
    private final Boolean admin;
    private final Boolean presupuesto;
    private final Boolean proposicionGeneral;
    private final Boolean comiteConvivencia;
    private final Boolean revisor;

}
