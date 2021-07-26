package co.edu.unicartagena.control.domain.repositories;

import co.edu.unicartagena.control.domain.entities.PropiedadHorizontal;

import java.util.Optional;

public interface PropiedadHorizontalRepository {

    Optional<PropiedadHorizontal> findPHById(Integer id);
    PropiedadHorizontal save(PropiedadHorizontal propiedadHorizontal);
    Integer saveRestrictions(Integer idPropiedad, String restricciones);
    Float findTotalCoeficiente(Integer idPropiedad);
    Integer findTotalPropietarios(Integer idPropiedad);
}
