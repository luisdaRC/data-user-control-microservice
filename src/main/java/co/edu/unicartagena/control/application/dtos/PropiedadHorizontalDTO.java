package co.edu.unicartagena.control.application.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class PropiedadHorizontalDTO {

    private final int id;
    private final String nombre;
    private final String tipo;
    private final String direccion;
}
