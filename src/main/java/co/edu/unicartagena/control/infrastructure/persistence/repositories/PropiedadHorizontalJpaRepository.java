package co.edu.unicartagena.control.infrastructure.persistence.repositories;

import co.edu.unicartagena.control.domain.entities.PropiedadHorizontal;
import co.edu.unicartagena.control.domain.repositories.PropiedadHorizontalRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;


@Repository
public interface PropiedadHorizontalJpaRepository extends JpaRepository<PropiedadHorizontal, Integer>, PropiedadHorizontalRepository {

    @Query(value = "SELECT * FROM propiedadhorizontal WHERE idpropiedadhorizontal = :id", nativeQuery = true)
    Optional<PropiedadHorizontal> findPHById(@Param("id") Integer id);

    PropiedadHorizontal save(PropiedadHorizontal propiedadHorizontal);

    @Modifying
    @Query(value = "INSERT INTO restriccion VALUES (DEFAULT, :idPropiedad, :restricciones)", nativeQuery = true)
    @Transactional
    Integer saveRestrictions(@Param("idPropiedad") Integer idPropiedad, @Param("restricciones") String restricciones);

    @Query(value = "SELECT sum(coeficientecopropiedad) FROM bienprivado WHERE propiedadhorizontal_idph = :idPropiedad", nativeQuery = true)
    Float findTotalCoeficiente(@Param("idPropiedad") Integer idPropiedad);

    @Query(value = "SELECT count(*) FROM bienprivado WHERE propiedadhorizontal_idph = :idPropiedad", nativeQuery = true)
    Integer findTotalPropietarios(@Param("idPropiedad") Integer idPropiedad);

    @Modifying
    @Query(value = "INSERT INTO asamblea VALUES (DEFAULT, :idSecretario, :fechaInicio, :fechaFin)", nativeQuery = true)
    @Transactional
    Integer insertAsamblea(@Param("idSecretario") Integer idSecretario, @Param("fechaInicio") LocalDateTime fechaInicio, @Param("fechaFin") LocalDateTime fechaFin);

    @Query(value = "SELECT idasamblea FROM asamblea WHERE personalapoyo_idpa = :idSecretario AND fechainicio = fechafin", nativeQuery = true)
    Optional<Integer> findIdAsamblea(@Param("idSecretario") Integer idSecretario);

    @Modifying
    @Query(value = "UPDATE asistente SET horasalida = :horaSalida WHERE persona_idpersona = :idPersona " +
            "AND asamblea_idasamblea = :idAsamblea", nativeQuery = true)
    Integer registrarAbandono(@Param("idPersona") Integer idPersona,
                              @Param("idAsamblea") Integer idAsamblea,
                              @Param("horaSalida") LocalDateTime horaSalida);

    @Modifying
    @Query(value = "UPDATE asamblea SET fechafin = :fechaFin WHERE idasamblea = :idAsamblea", nativeQuery = true)
    Integer finalizarAsamblea(@Param("idAsamblea") Integer idAsamblea,
                              @Param("fechaFin") LocalDateTime fechaFin);

    @Query(value = "SELECT persona_idpersona FROM asistente WHERE horallegada = horasalida AND " +
            "asamblea_idasamblea = :idAsamblea", nativeQuery = true)
    Optional<List<Integer>> findAllAsistentesByIdAsamblea(@Param("idAsamblea") Integer idAsamblea);
}
