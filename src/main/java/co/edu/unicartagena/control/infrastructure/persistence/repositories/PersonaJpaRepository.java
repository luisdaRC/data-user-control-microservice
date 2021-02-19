package co.edu.unicartagena.control.infrastructure.persistence.repositories;

import co.edu.unicartagena.control.domain.entities.Persona;
import co.edu.unicartagena.control.domain.repositories.PersonaRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonaJpaRepository extends JpaRepository<Persona,Integer>, PersonaRepository {

    //@Query("select u from Persona u where u.idBienPrivado.idPropiedad.idPropiedadHorizontal = :idPropiedad")
    @Query(value = "SELECT * FROM persona WHERE bienprivado_idbienprivado = (SELECT idbienprivado FROM bienprivado WHERE propiedadhorizontal_idph = :idPropiedad)", nativeQuery = true)
    List<Persona> findByIdPropiedad(@Param("idPropiedad") Integer idPropiedad);

    //List<Persona> saveAll(List<Persona> personas);
}
