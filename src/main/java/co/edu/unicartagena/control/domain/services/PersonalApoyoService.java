package co.edu.unicartagena.control.domain.services;

import co.edu.unicartagena.control.domain.entities.PersonalApoyo;
import co.edu.unicartagena.control.domain.entities.PropiedadHorizontal;
import co.edu.unicartagena.control.domain.exceptions.BusinessException;
import co.edu.unicartagena.control.domain.repositories.PersonalApoyoRepository;
import co.edu.unicartagena.control.domain.repositories.PropiedadHorizontalRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Log4j2
@Service
@Transactional
public class PersonalApoyoService {

    private final PersonalApoyoRepository personalApoyoRepository;
    private final PropiedadHorizontalRepository propiedadHorizontalRepository;

    public PersonalApoyoService(PersonalApoyoRepository personalApoyoRepository,
                                PropiedadHorizontalRepository propiedadHorizontalRepository){
        this.personalApoyoRepository = personalApoyoRepository;
        this.propiedadHorizontalRepository = propiedadHorizontalRepository;
    }

    public PersonalApoyo registrarPersonal(PersonalApoyo personalApoyo){

        Optional<PropiedadHorizontal> existePropiedad = propiedadHorizontalRepository
                .findById(personalApoyo.getIdPropiedad());

        Optional<PersonalApoyo> existePersonal = personalApoyoRepository.findPersonalApoyoByIdPHAndRol(personalApoyo.getIdPropiedad(),
                personalApoyo.getRol(),personalApoyo.getEstado());

        if(existePropiedad.isEmpty()) {
            log.debug("No existe la propiedad con id {}",personalApoyo.getIdPropiedad());
            throw new BusinessException("No existe la propiedad referenciada");
        }

        if(existePersonal.isPresent()) {
            log.debug("El {} con número de identificación {} ya está registrado en el sistema",
                    personalApoyo.getRol(),personalApoyo.getNumeroDocumento());
            throw new BusinessException("El usuario ya existe ne l sistema");
        }

        log.debug("Se procede a registrar el {} en el sistema.",
                personalApoyo.getRol());
        return personalApoyoRepository.save(personalApoyo);
    }

    public Boolean existeRevisor(String idPropiedad){
        log.debug("Se procede a verificar existencia de revisor en la propiedad: {}",idPropiedad);
        return personalApoyoRepository.findPersonalApoyoByIdPHAndRol(Integer.parseInt(idPropiedad),"REVISOR", true).isPresent();
    }

    public Boolean existeSecretary(String idPropiedad){
        log.debug("Se procede a verificar existencia de secretario en la propiedad: {}",idPropiedad);
        return personalApoyoRepository.findPersonalApoyoByIdPHAndRol(Integer.parseInt(idPropiedad),"SECRETARIO", true).isPresent();
    }

    public PersonalApoyo patchPersonal(PersonalApoyo personalApoyo){

        Optional<PersonalApoyo> existePersonal = personalApoyoRepository.findPersonalApoyoByIdPHAndRol(personalApoyo.getIdPropiedad(),
                personalApoyo.getRol(),personalApoyo.getEstado());

        if(existePersonal.isPresent()) {
            log.debug("Se procede a actualizar el {} en el sistema.",
                    personalApoyo.getRol());
            return personalApoyoRepository.update(personalApoyo.getTipoDocumento(),personalApoyo.getNumeroDocumento(),
                    personalApoyo.getRol(),personalApoyo.getIdPropiedad(),personalApoyo.getEstado());
        }

        log.debug("El {} con número de identificación {} no está registrado en el sistema",
                personalApoyo.getRol(),personalApoyo.getNumeroDocumento());
        throw new BusinessException("El usuario no está registrado en el sistema");

    }
}
