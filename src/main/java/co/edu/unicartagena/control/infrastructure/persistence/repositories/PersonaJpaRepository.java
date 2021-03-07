package co.edu.unicartagena.control.infrastructure.persistence.repositories;

import co.edu.unicartagena.control.domain.entities.Persona;
import co.edu.unicartagena.control.domain.repositories.PersonaRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonaJpaRepository extends JpaRepository<Persona, Integer>, PersonaRepository {

    @Query(value = "SELECT persona.* FROM persona " +
            "JOIN bienprivado ON persona.bienprivado_idbienprivado = bienprivado.idbienprivado " +
            "JOIN propiedadhorizontal ON bienprivado.propiedadhorizontal_idph = :idPropiedad " +
            "GROUP BY persona.idpersona", nativeQuery = true)
    List<Persona> findByIdPropiedad(@Param("idPropiedad") Integer idPropiedad);

}
