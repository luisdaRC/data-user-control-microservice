package co.edu.unicartagena.control.application.builders;

import co.edu.unicartagena.control.application.dtos.BienPrivadoDTO;
import co.edu.unicartagena.control.domain.entities.BienPrivado;
import co.edu.unicartagena.control.domain.entities.PropiedadHorizontal;

public class BienPrivadoBuilder {

    public BienPrivadoBuilder(){}

    public BienPrivado crearBienPrivadoDesdeDTO(BienPrivadoDTO bienPrivadoDTO){
        return BienPrivado.builder()
                .id(bienPrivadoDTO.getId())
                .idPropiedad(
                        PropiedadHorizontal.builder().id(bienPrivadoDTO.getIdPropiedad()).build()
                )
                .coeficienteCopropiedad((float) 1)
                .matriculaInmobiliaria(bienPrivadoDTO.getMatriculaInmobiliaria())
                .referenciaCatastral(bienPrivadoDTO.getReferenciaCatastral())
                .build();
    }

    public BienPrivadoDTO crearBienPrivadoDTODesdeEntidad(BienPrivado bienPrivado){
        return BienPrivadoDTO.builder()
                .id(bienPrivado.getId())
                .idPropiedad(bienPrivado.getIdPropiedad().getId())
                .matriculaInmobiliaria(bienPrivado.getMatriculaInmobiliaria())
                .referenciaCatastral(bienPrivado.getReferenciaCatastral())
                .build();
    }
}
