package co.edu.unicartagena.control.domain.entities;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
public class PropiedadHorizontal {

    public PropiedadHorizontal() {
    }

    private Integer id;
    private String nombre;
    private String tipo;
    private String direccion;/*
    private List<PersonalApoyo> personalApoyoList;
    private List<BienPrivado> bienPrivadoList;*/

}
