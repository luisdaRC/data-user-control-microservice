package co.edu.unicartagena.control.domain.repositories;

import co.edu.unicartagena.control.domain.entities.PropiedadHorizontal;

import java.util.Optional;

public interface PropiedadHorizontalRepository {

    Optional<PropiedadHorizontal> findById (Integer id);
    PropiedadHorizontal save(PropiedadHorizontal propiedadHorizontal);
}
