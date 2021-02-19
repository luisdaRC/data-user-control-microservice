package co.edu.unicartagena.control.application.builders;

import co.edu.unicartagena.control.application.dtos.PersonalApoyoDTO;
import co.edu.unicartagena.control.domain.entities.PersonalApoyo;
import co.edu.unicartagena.control.domain.entities.PropiedadHorizontal;

public class PersonalApoyoBuilder {

    public PersonalApoyoBuilder(){}

    public static PersonalApoyo crearPersonalApoyodesdeDTO(PersonalApoyoDTO personalApoyoDTO){
        return PersonalApoyo.builder()
                .idPropiedad(
                        PropiedadHorizontal.builder().id(personalApoyoDTO.getIdPropiedad()).build())
                .email(personalApoyoDTO.getEmail().toLowerCase())
                .pass(personalApoyoDTO.getPass())
                .estado(personalApoyoDTO.getEstado())
                .rol(personalApoyoDTO.getRol())
                .nombres(personalApoyoDTO.getNombres())
                .numeroDocumento(personalApoyoDTO.getNumeroDocumento())
                .tipoDocumento(personalApoyoDTO.getTipoDocumento())
                .build();
    }

    public static PersonalApoyoDTO crearPersonalApoyoDTODesdeEntidad(PersonalApoyo personalApoyo){
        return PersonalApoyoDTO.builder()
                .idPropiedad(personalApoyo.getIdPropiedad().getId())
                .email(personalApoyo.getEmail())
                .pass(personalApoyo.getPass())
                .estado(personalApoyo.getEstado())
                .rol(personalApoyo.getRol())
                .nombres(personalApoyo.getNombres())
                .numeroDocumento(personalApoyo.getNumeroDocumento())
                .tipoDocumento(personalApoyo.getTipoDocumento())
                .build();
    }
}
