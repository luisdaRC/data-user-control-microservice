package co.edu.unicartagena.control.application.builders;

import co.edu.unicartagena.control.application.dtos.PropiedadHorizontalDTO;
import co.edu.unicartagena.control.domain.entities.PropiedadHorizontal;

public class PropiedadHorizontalBuilder {

    public PropiedadHorizontalBuilder () {}

    public static PropiedadHorizontal crearPHDesdeDTO(PropiedadHorizontalDTO propiedadHorizontalDTO){
        return PropiedadHorizontal.builder()
                .id(propiedadHorizontalDTO.getId())
                .nombre(propiedadHorizontalDTO.getNombre())
                .tipo(propiedadHorizontalDTO.getTipo())
                .direccion(propiedadHorizontalDTO.getDireccion())
                .build();
    }

    public static PropiedadHorizontalDTO crearPHDTODesdeEntidad(PropiedadHorizontal propiedadHorizontal){
        return PropiedadHorizontalDTO.builder()
                .id(propiedadHorizontal.getId())
                .nombre(propiedadHorizontal.getNombre())
                .tipo(propiedadHorizontal.getTipo())
                .direccion(propiedadHorizontal.getDireccion())
                .build();
    }
}
