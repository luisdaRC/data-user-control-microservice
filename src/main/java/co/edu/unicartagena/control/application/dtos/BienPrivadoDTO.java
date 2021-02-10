package co.edu.unicartagena.control.application.dtos;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
public class BienPrivadoDTO {

    private final int id;
    private final int idPropiedad;
    private final int idNomenclatura;
    private final String matriculaInmobiliaria;
    private final String referenciaCatastral;
}
