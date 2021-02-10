package co.edu.unicartagena.control.infrastructure.persistence.repositories;

import co.edu.unicartagena.control.domain.entities.Persona;
import co.edu.unicartagena.control.domain.repositories.PersonaRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonaJpaRepository extends JpaRepository<Persona,Integer>, PersonaRepository {
}
