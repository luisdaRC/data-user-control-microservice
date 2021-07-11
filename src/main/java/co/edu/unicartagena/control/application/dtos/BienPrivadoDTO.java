package co.edu.unicartagena.control.application.dtos;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
public class BienPrivadoDTO {

    private final Integer id;
    private final Integer idPropiedadHorizontal;
    private final Integer idNomeclatura;
    private final String matriculaInmobiliaria;
    private final String referenciaCatastral;
}
