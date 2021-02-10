package co.edu.unicartagena.control.domain.services;

import co.edu.unicartagena.control.domain.entities.BienPrivado;
import co.edu.unicartagena.control.domain.entities.Persona;
import co.edu.unicartagena.control.domain.entities.PropiedadHorizontal;
import co.edu.unicartagena.control.domain.repositories.BienPrivadoRepository;
import co.edu.unicartagena.control.domain.repositories.PersonaRepository;
import co.edu.unicartagena.control.domain.repositories.PropiedadHorizontalRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Log4j2
@Service
@Transactional
public class PHService {

    private final PropiedadHorizontalRepository propiedadHorizontalRepository;
    private final PersonaRepository personaRepository;
    private final BienPrivadoRepository bienPrivadoRepository;

    public  PHService(PropiedadHorizontalRepository propiedadHorizontalRepository,
                      PersonaRepository personaRepository,
                      BienPrivadoRepository bienPrivadoRepository){
        this.propiedadHorizontalRepository = propiedadHorizontalRepository;
        this.personaRepository = personaRepository;
        this.bienPrivadoRepository = bienPrivadoRepository;
    }

    public PropiedadHorizontal registrarPropiedad(PropiedadHorizontal propiedadHorizontal){
        log.debug("Se procede a registrar Propiedad Horizontal {} en el sistema.",
                propiedadHorizontal.getNombre());
        return propiedadHorizontalRepository.save(propiedadHorizontal);
    }

    public Boolean existePropiedad(String idPropiedad){
        log.debug("Verificando existencia de Propiedad con id {} en PHService",idPropiedad);
        return propiedadHorizontalRepository.findById(Integer.parseInt(idPropiedad)).isPresent();
    }

    public List<Persona> updateData(List<Persona> personas, List<BienPrivado> bienPrivados) {
        log.debug("Actualizando datos de la propiedad #: {}", bienPrivados.get(0).getIdPropiedad());

        Optional<List<BienPrivado>> exist = bienPrivadoRepository.findByIdPropiedad(bienPrivados.get(0).getIdPropiedad());

        // If there are no "bienes" in that property, then they all are stored in database
        if (exist.isEmpty()){
            bienPrivadoRepository.saveAll(bienPrivados);
            return personaRepository.saveAll(personas);
        }
        // Otherwise, we proceed to compare the results of the database with the new list
        // from the core and insert the missing ones.
        // Look for ways of doing it with "Object Sets"
        return personas; //Quit this return, you know.
    }

}
