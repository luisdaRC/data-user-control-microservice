package co.edu.unicartagena.control.application.builders;

import co.edu.unicartagena.control.application.dtos.DataPersonalDTO;
import co.edu.unicartagena.control.application.dtos.PersonalApoyoDTO;
import co.edu.unicartagena.control.domain.entities.PersonalApoyo;

public class PersonalApoyoBuilder {

    public PersonalApoyoBuilder(){}

    public static PersonalApoyo crearPersonalApoyodesdeDTO(PersonalApoyoDTO personalApoyoDTO){
        return PersonalApoyo.builder()
                .idPropiedad(personalApoyoDTO.getIdPropiedadHorizontal())
                .estado(personalApoyoDTO.getEstado())
                .rol(personalApoyoDTO.getRol())
                .email(personalApoyoDTO.getDataPersonal().getEmail().toLowerCase())
                .pass(personalApoyoDTO.getDataPersonal().getPassword())
                .nombres(personalApoyoDTO.getDataPersonal().getNombres())
                .numeroDocumento(personalApoyoDTO.getDataPersonal().getNumeroDocumento())
                .tipoDocumento(personalApoyoDTO.getDataPersonal().getTipoDocumento())
                .build();
    }

    public static PersonalApoyoDTO crearPersonalApoyoDTODesdeEntidad(PersonalApoyo personalApoyo){
        DataPersonalDTO dataPersonalDTO =
                new DataPersonalDTO(personalApoyo.getEmail(),
                        personalApoyo.getPass(),
                        personalApoyo.getNombres(),
                        personalApoyo.getNumeroDocumento(),
                        personalApoyo.getTipoDocumento()
                );

        return PersonalApoyoDTO.builder()
                .idPropiedadHorizontal(personalApoyo.getIdPropiedad())
                .estado(personalApoyo.getEstado())
                .rol(personalApoyo.getRol())
                .dataPersonal(dataPersonalDTO)
                .build();
    }
}
