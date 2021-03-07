package co.edu.unicartagena.control.infrastructure.persistence.repositories;

import co.edu.unicartagena.control.domain.entities.PropiedadHorizontal;
import co.edu.unicartagena.control.domain.repositories.PropiedadHorizontalRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface PropiedadHorizontalJpaRepository extends JpaRepository<PropiedadHorizontal, Integer>, PropiedadHorizontalRepository {

    @Query(value = "SELECT * FROM propiedadhorizontal WHERE idpropiedadhorizontal = :id", nativeQuery = true)
    Optional<PropiedadHorizontal> findPHById(@Param("id") Integer id);

    PropiedadHorizontal save(PropiedadHorizontal propiedadHorizontal);
}
