package co.edu.unicartagena.control.infrastructure.persistence.repositories;

import co.edu.unicartagena.control.domain.entities.BienPrivado;
import co.edu.unicartagena.control.domain.repositories.BienPrivadoRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BienPrivadoJpaRepository extends JpaRepository<BienPrivado,Integer>, BienPrivadoRepository {

    //@Query("select u from BienPrivado u where u.idPropiedad.idPropiedadHorizontal = :idPropiedad")
    @Query(value = "SELECT * FROM bienprivado WHERE propiedadhorizontal_idph = :idPropiedad", nativeQuery = true)
    Optional<List<BienPrivado>> findByIdPropiedad(@Param("idPropiedad") Integer idPropiedad);

    //List<BienPrivado> saveAll(List<BienPrivado> bienPrivados);
}
