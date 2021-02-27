package co.edu.unicartagena.control.application.builders;

import co.edu.unicartagena.control.application.dtos.PersonaDTO;
import co.edu.unicartagena.control.application.dtos.PersonaListDTO;
import co.edu.unicartagena.control.domain.entities.Persona;

public class PersonaBuilder {

    public PersonaBuilder(){}

    public static Persona crearPersonaDesdePersonaList(PersonaListDTO personaList){
        return Persona.builder().idPersona(personaList.getId())
                .idBienPrivado(personaList.getBienPrivado().getId())
                .nombres(personaList.getNombres())
                .apellido(personaList.getApellido())
                .numeroDocumento(personaList.getNumeroDocumento())
                .tipoDocumento(personaList.getTipoDocumento())
                .rol(personaList.getRol()).build();
    }

    public PersonaDTO crearPersonaDTODesdeEntidad(Persona persona){
        return PersonaDTO.builder()
                .nombres(persona.getNombres())
                .apellidos(persona.getApellido())
                .numeroDocumento(persona.getNumeroDocumento())
                .tipoDocumento(persona.getTipoDocumento())
                .build();
    }
}
