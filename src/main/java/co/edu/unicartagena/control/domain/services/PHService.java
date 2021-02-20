package co.edu.unicartagena.control.domain.services;

import co.edu.unicartagena.control.domain.entities.BienPrivado;
import co.edu.unicartagena.control.domain.entities.Persona;
import co.edu.unicartagena.control.domain.entities.PropiedadHorizontal;
import co.edu.unicartagena.control.domain.exceptions.BusinessException;
import co.edu.unicartagena.control.domain.repositories.BienPrivadoRepository;
import co.edu.unicartagena.control.domain.repositories.PersonaRepository;
import co.edu.unicartagena.control.domain.repositories.PropiedadHorizontalRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

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
        return propiedadHorizontalRepository.findPHById(Integer.parseInt(idPropiedad)).isPresent();
    }

    public PropiedadHorizontal obtenerPropiedad(String id){
        if(!existePropiedad(id)) {
            log.debug("La propiedad con id {} no existe",id);
            throw new BusinessException("La propiedad indicada no existe");
        }

        Optional<PropiedadHorizontal> ph = propiedadHorizontalRepository.findPHById(Integer.parseInt(id));
        return ph.get();
    }

    @Transactional
    public List<Persona> updateData(List<Persona> personas, List<BienPrivado> bienPrivados) {
        log.debug("Actualizando datos de la propiedad #: {}", bienPrivados.get(0).getIdPropiedad());

        Optional<List<BienPrivado>> existenBienes = bienPrivadoRepository.findByIdPropiedad(bienPrivados.get(0).getIdPropiedad().getId());

        // If there are no "bienes" in that property, then they all are stored in database
        if (!existenBienes.isPresent()){
            log.debug("Propiedad #: {} con datos de personas vacios, insertándolos.", bienPrivados.get(0).getIdPropiedad());
            /*bienPrivadoRepository.saveAll(bienPrivados);
            return personaRepository.saveAll(personas);*/
            List<Persona> listP = new ArrayList<Persona>();
            List<BienPrivado> listB = new ArrayList<BienPrivado>();

            for(Persona p:personas)
                listP.add(personaRepository.save(p));

            for(BienPrivado b:bienPrivados)
                listB.add(bienPrivadoRepository.save(b));

            return listP;

        } else if (existenBienes.get().size() == personas.size())
            return personas;

        // Otherwise, we proceed to compare the results of the database with the new list
        // from the core and insert the missing ones
        List<Persona> existenPersonas = personaRepository.findByIdPropiedad(bienPrivados.get(0).getIdPropiedad().getId());

        log.debug("Actualizando datos faltantes de personas en la propiedad #: {}", bienPrivados.get(0).getIdPropiedad());
        List<Persona> listP = new ArrayList<Persona>();
        List<BienPrivado> listB = new ArrayList<BienPrivado>();

        listB.addAll(deleteBienesDuplicados(existenBienes.get(), bienPrivados));
        listP.addAll(deletePersonasDuplicadas(existenPersonas, personas));

        for(Persona p:listP)
            personaRepository.save(p);

        for(BienPrivado b:listB)
            bienPrivadoRepository.save(b);

        return listP;

        /*bienPrivadoRepository.saveAll(deleteBienesDuplicados(existenBienes.get(), bienPrivados));
        return personaRepository.saveAll(deletePersonasDuplicadas(existenPersonas, personas));*/
    }

    public List<Persona> deletePersonasDuplicadas(List<Persona> personasEnDB, List<Persona> personasDeCore){
        Set<Persona> setPersonasEnDB = new HashSet<>();
        Set<Persona> setPersonasDeCore = new HashSet<>();

        //List to Set
        setPersonasEnDB.addAll(personasEnDB);
        setPersonasDeCore.addAll(personasDeCore);

        //Asymmetric difference to get the non present in DB
        setPersonasDeCore.removeAll(setPersonasEnDB);
        
        List<Persona> difference = new ArrayList<>();

        //Set to List
        difference.addAll(setPersonasDeCore);

        return difference;
    }

    public List<BienPrivado> deleteBienesDuplicados(List<BienPrivado> bienesEnDB, List<BienPrivado> bienesDeCore){
        Set<BienPrivado> setBienesEnDB = new HashSet<>();
        Set<BienPrivado> setBienesDeCore = new HashSet<>();

        //List to Set
        setBienesEnDB.addAll(bienesEnDB);
        setBienesDeCore.addAll(bienesDeCore);

        //Asymmetric difference to get the non present in DB
        setBienesDeCore.removeAll(setBienesEnDB);

        List<BienPrivado> difference = new ArrayList<>();

        //Set to List
        difference.addAll(setBienesDeCore);

        return difference;
    }

}
