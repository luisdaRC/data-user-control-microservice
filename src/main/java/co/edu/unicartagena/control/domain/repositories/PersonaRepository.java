package co.edu.unicartagena.control.domain.repositories;

import co.edu.unicartagena.control.domain.entities.Persona;

import java.util.List;

public interface PersonaRepository {
    Persona save(Persona persona);
    List<Persona> findByIdPropiedad(Integer idPropiedad);
    List<Persona> saveAll(List<Persona> personas);
}
