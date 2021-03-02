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

        System.out.println("Llega a registrarService");
        Optional<PropiedadHorizontal> existePropiedad = propiedadHorizontalRepository
                .findPHById(personalApoyo.getIdPropiedad());

        System.out.println("Qué tiene existePropiedad?: "+existePropiedad.toString());
        System.out.println("Qué tiene existePropiedad en bool?: "+existePropiedad.isPresent());

        if(!existePropiedad.isPresent()) {
            log.debug("No existe la propiedad con id {}",personalApoyo.getIdPropiedad());
            throw new BusinessException("No existe la propiedad referenciada");
        }

        Optional<PersonalApoyo> existePersonalActivo = personalApoyoRepository.findPersonalApoyoByIdPHAndRol(personalApoyo.getIdPropiedad(),
                personalApoyo.getRol(),personalApoyo.getEstado());

        System.out.println("Qué tiene existePersonalActivo?: "+existePersonalActivo.toString());
        System.out.println("Qué tiene existePersonalActivo en bool?: "+existePersonalActivo.isPresent());

        if(existePersonalActivo.isPresent()) {
            log.debug("El usuario de {} de la propiedad está activo en el sistema. Eliminelo para nuevo registro",
                    personalApoyo.getRol());

            throw new BusinessException("Un usuario con el rol deseado está activo en el sistema. Elimínelo");
        }

        Optional<PersonalApoyo> existeEmail = personalApoyoRepository.findByEmail(personalApoyo.getEmail());

        System.out.println("Qué tiene existeEmail?: "+existeEmail.toString());
        System.out.println("Qué tiene existeEmail en bool?: "+existeEmail.isPresent());

        if(existeEmail.isPresent()) {
            log.debug("Existe un usuario con el email ingresado");
            throw new BusinessException("Existe un usuario con el email ingresado");
        }

        Optional<PersonalApoyo> actualizarPersonal = personalApoyoRepository
                .findPersonalApoyoByIdPHAndRolAndTipoDocAndNumDoc(personalApoyo.getIdPropiedad(),personalApoyo.getRol(),
                        personalApoyo.getTipoDocumento(),personalApoyo.getNumeroDocumento());

        System.out.println("Qué tiene existePersonalActivo?: "+actualizarPersonal.toString());
        System.out.println("Qué tiene existePersonalActivo en bool?: "+actualizarPersonal.isPresent());

        if(actualizarPersonal.isPresent()){
            System.out.println("Actualizando datos de "+personalApoyo.getRol()+" en el sistema.");
            personalApoyoRepository.updateEstadoAndEmailAndPassByTipoAndNumDoc(personalApoyo.getEstado(),
                    personalApoyo.getEmail(),personalApoyo.getPass(),personalApoyo.getTipoDocumento(),personalApoyo.getNumeroDocumento());
            return actualizarPersonal.get();
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

        System.out.println("Verificando existencia de " +personalApoyo.getRol()+ " en el sistema");
        Optional<PersonalApoyo> existePersonal = personalApoyoRepository.findPersonalApoyoByIdPHAndRol(personalApoyo.getIdPropiedad(),
                personalApoyo.getRol(),true);

        if(existePersonal.isPresent()) {
            System.out.println("Se procede a actualizar el "+personalApoyo.getRol()+" en el sistema.");
            Integer response = personalApoyoRepository.changeEstado(existePersonal.get().getId(),personalApoyo.getEstado());
            return existePersonal.get();
        }

        log.debug("El {} con número de identificación {} no está registrado en el sistema",
                personalApoyo.getRol(),personalApoyo.getNumeroDocumento());
        throw new BusinessException("El usuario no está registrado en el sistema");

    }

    public PersonalApoyo findPersonalByEmailAndPass(String email, String encodedPass){
        log.debug("Verificando existencia de user con email {} en el sistema",email);
        Optional<PersonalApoyo> personal = personalApoyoRepository.findByEmail(email);

        if(!personal.isPresent()){
            log.debug("Correo inexistente");
            throw new BusinessException("Correo inexistente");
        }

        if(!personal.get().getPass().equals(encodedPass)){
            log.debug("Password incorrecto");
            throw new BusinessException("Password incorrecto");
        }

        return personal.get();
    }
}
