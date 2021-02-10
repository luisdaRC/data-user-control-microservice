package co.edu.unicartagena.control.application.builders;

import co.edu.unicartagena.control.application.dtos.PersonaDTO;
import co.edu.unicartagena.control.domain.entities.Persona;

public class PersonaBuilder {

    public PersonaBuilder(){}

    public Persona crearPersonaDesdeDTO(PersonaDTO personaDTO){
        return Persona.builder()
                .idBienPrivado(personaDTO.getIdBienPrivado())
                .nombres(personaDTO.getNombres())
                .apellidos(personaDTO.getApellidos())
                .numeroDocumento(personaDTO.getNumeroDocumento())
                .tipoDocumento(personaDTO.getTipoDocumento())
                .build();
    }

    public PersonaDTO crearPersonaDTODesdeEntidad(Persona persona){
        return PersonaDTO.builder()
                .idBienPrivado(persona.getIdBienPrivado())
                .nombres(persona.getNombres())
                .apellidos(persona.getApellidos())
                .numeroDocumento(persona.getNumeroDocumento())
                .tipoDocumento(persona.getTipoDocumento())
                .build();
    }
}
