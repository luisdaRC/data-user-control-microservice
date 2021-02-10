package co.edu.unicartagena.control.domain.entities;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@Builder
@ToString
@RequiredArgsConstructor
public class PropiedadHorizontal {

    private final int id;
    private final String nombre;
    private final String tipo;
    private final String direccion;

}
