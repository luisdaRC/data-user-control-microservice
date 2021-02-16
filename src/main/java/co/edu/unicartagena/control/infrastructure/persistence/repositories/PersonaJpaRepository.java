package co.edu.unicartagena.control.infrastructure.persistence.repositories;

import co.edu.unicartagena.control.infrastructure.persistence.entities.Persona;
import co.edu.unicartagena.control.domain.repositories.PersonaRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonaJpaRepository extends JpaRepository<Persona,Integer>, PersonaRepository {
}
