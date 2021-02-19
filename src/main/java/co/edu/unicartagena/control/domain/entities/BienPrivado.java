package co.edu.unicartagena.control.domain.entities;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
public class BienPrivado {

    public BienPrivado(){}

    private Integer id;
    private PropiedadHorizontal idPropiedad;
    private Float coeficienteCopropiedad;
    private String matriculaInmobiliaria;
    private String referenciaCatastral;

}
