package co.edu.unicartagena.control.domain.repositories;

import co.edu.unicartagena.control.domain.entities.PropiedadHorizontal;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface PropiedadHorizontalRepository {

    Optional<PropiedadHorizontal> findPHById(Integer id);
    PropiedadHorizontal save(PropiedadHorizontal propiedadHorizontal);
    Integer saveRestrictions(Integer idPropiedad, String restricciones);
    Float findTotalCoeficiente(Integer idPropiedad);
    Integer findTotalPropietarios(Integer idPropiedad);
    Optional<Integer> findIdAsamblea(Integer idSecretario);
    Optional<List<Integer>> findAllAsistentesByIdAsamblea(Integer idAsamblea);
    Integer registrarAbandono(Integer idPersona, Integer idAsamblea, LocalDateTime horaSalida);
    Integer finalizarAsamblea(Integer idAsamblea, LocalDateTime fechaFin);
    Integer insertAsamblea(Integer idSecretario, LocalDateTime fechaInicio, LocalDateTime fechaFin);
}
