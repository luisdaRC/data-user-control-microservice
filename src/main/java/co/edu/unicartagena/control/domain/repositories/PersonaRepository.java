package co.edu.unicartagena.control.domain.repositories;

import co.edu.unicartagena.control.domain.entities.Persona;

import java.util.List;
import java.util.Optional;

public interface PersonaRepository {
    Persona save(Persona persona);

    List<Persona> findByIdPropiedad(Integer idPropiedad);

    Optional<Integer> findBienPersonaByTipoAndNumDoc(String tipoDoc, String numDoc);
    //List<Persona> saveAll(List<Persona> personas);
}
