package co.edu.unicartagena.control.infrastructure.persistence.repositories;

import co.edu.unicartagena.control.domain.entities.PropiedadHorizontal;
import co.edu.unicartagena.control.domain.repositories.PropiedadHorizontalRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

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
}
