package co.edu.unicartagena.control.domain.entities;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@Builder
@ToString
@RequiredArgsConstructor
public class BienPrivado {

    private final int id;
    private final int idPropiedad;
    private final float coeficienteCopropiedad; //Al insertar en BD hacerlo con valor de 1.
    private final String matriculaInmobiliaria;
    private final String referenciaCatastral;

}
