package co.edu.unicartagena.control.infrastructure.persistence.repositories;

import co.edu.unicartagena.control.domain.entities.PersonalApoyo;
import co.edu.unicartagena.control.domain.repositories.PersonalApoyoRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonalApoyoJpaRepository extends JpaRepository<PersonalApoyo,Integer>, PersonalApoyoRepository {

    //Usar en esta clase queries personalizados, como se muestran en la doc de spring:
    //https://docs.spring.io/spring-data/jpa/docs/1.11.1.RELEASE/reference/html/#jpa.query-methods.at-query
}
