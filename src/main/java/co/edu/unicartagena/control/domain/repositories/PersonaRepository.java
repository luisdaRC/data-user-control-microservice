package co.edu.unicartagena.control.domain.repositories;

import co.edu.unicartagena.control.domain.entities.Persona;

import java.util.List;

public interface PersonaRepository {
    Persona registrarPersona(Persona persona);
    List<Persona> findPersonaPorPropiedad(Integer propiedadHorizontal);
    List<Persona> saveAll(List<Persona> personas);
}
