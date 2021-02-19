package co.edu.unicartagena.control.infrastructure.persistence.repositories;

import co.edu.unicartagena.control.domain.entities.PropiedadHorizontal;
import co.edu.unicartagena.control.domain.repositories.PropiedadHorizontalRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface PropiedadHorizontalJpaRepository extends JpaRepository<PropiedadHorizontal,Integer>, PropiedadHorizontalRepository {

    //Usar en esta clase queries personalizados, como se muestran en la doc de spring:
    //https://docs.spring.io/spring-data/jpa/docs/1.11.1.RELEASE/reference/html/#jpa.query-methods.at-query
    //@Query("select u from PropiedadHorizontal u where u.idPropiedadHorizontal = :id")
    @Query(value = "SELECT * FROM propiedadhorizontal WHERE idpropiedadhorizontal = :id", nativeQuery = true)
    Optional<PropiedadHorizontal> findPHById (@Param("id") Integer id);
}
