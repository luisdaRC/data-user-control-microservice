package co.edu.unicartagena.control.application.builders;

import co.edu.unicartagena.control.application.dtos.BienPrivadoDTO;
import co.edu.unicartagena.control.domain.entities.BienPrivado;
import co.edu.unicartagena.control.domain.entities.PropiedadHorizontal;

public class BienPrivadoBuilder {

    public BienPrivadoBuilder(){}

    public static BienPrivado crearBienPrivadoDesdeDTO(BienPrivadoDTO bienPrivadoDTO){
        return BienPrivado.builder()
                .id(bienPrivadoDTO.getId())
                .idPropiedad(bienPrivadoDTO.getIdPropiedadHorizontal())
                .coeficienteCopropiedad((float) 1)
                .matriculaInmobiliaria(bienPrivadoDTO.getMatriculaInmobiliaria())
                .referenciaCatastral(bienPrivadoDTO.getReferenciaCatastral())
                .build();
    }

    public static BienPrivadoDTO crearBienPrivadoDTODesdeEntidad(BienPrivado bienPrivado){
        return BienPrivadoDTO.builder()
                .id(bienPrivado.getId())
                .idPropiedadHorizontal(bienPrivado.getId())
                .matriculaInmobiliaria(bienPrivado.getMatriculaInmobiliaria())
                .referenciaCatastral(bienPrivado.getReferenciaCatastral())
                .build();
    }
}
