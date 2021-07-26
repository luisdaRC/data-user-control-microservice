package co.edu.unicartagena.control.domain.services;

import co.edu.unicartagena.control.domain.entities.PersonalApoyo;
import co.edu.unicartagena.control.domain.entities.PropiedadHorizontal;
import co.edu.unicartagena.control.domain.exceptions.BusinessException;
import co.edu.unicartagena.control.domain.repositories.PersonalApoyoRepository;
import co.edu.unicartagena.control.domain.repositories.PropiedadHorizontalRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class PersonalApoyoService {

    private final PersonalApoyoRepository personalApoyoRepository;
    private final PropiedadHorizontalRepository propiedadHorizontalRepository;

    public PersonalApoyoService(PersonalApoyoRepository personalApoyoRepository,
                                PropiedadHorizontalRepository propiedadHorizontalRepository) {
        this.personalApoyoRepository = personalApoyoRepository;
        this.propiedadHorizontalRepository = propiedadHorizontalRepository;
    }

    public Integer registrarPersonal(PersonalApoyo personalApoyo) {

        System.out.println("Llega a registrarService");
        Optional<PropiedadHorizontal> existePropiedad = propiedadHorizontalRepository
                .findPHById(personalApoyo.getIdPropiedad());

        System.out.println("Qué tiene existePropiedad?: " + existePropiedad.toString());
        System.out.println("Qué tiene existePropiedad en bool?: " + existePropiedad.isPresent());

        if (!existePropiedad.isPresent()) {
            return 1;//No existe la propiedad suinistrada
        }

        Float totalCoeficientes = propiedadHorizontalRepository.findTotalCoeficiente(personalApoyo.getIdPropiedad());
        Integer totalPropietarios = propiedadHorizontalRepository.findTotalPropietarios(personalApoyo.getIdPropiedad());

        if(totalCoeficientes.intValue() != totalPropietarios || totalCoeficientes != 100){
            return 5;//Los coeficientes de copropiedad no están debidamente registrados
        }

        Optional<PersonalApoyo> existePersonalActivo = personalApoyoRepository.findPersonalApoyoByIdPHAndRol(personalApoyo.getIdPropiedad(),
                personalApoyo.getRol(), personalApoyo.getEstado());

        System.out.println("Qué tiene existePersonalActivo?: " + existePersonalActivo.toString());
        System.out.println("Qué tiene existePersonalActivo en bool?: " + existePersonalActivo.isPresent());

        if (existePersonalActivo.isPresent()) {
            return 2;
        }

        Optional<PersonalApoyo> existeEmail = personalApoyoRepository.findByEmail(personalApoyo.getEmail());

        System.out.println("Qué tiene existeEmail?: " + existeEmail.toString());
        System.out.println("Qué tiene existeEmail en bool?: " + existeEmail.isPresent());

        if (existeEmail.isPresent()) {
            return 3;
        }

        Optional<PersonalApoyo> actualizarPersonal = personalApoyoRepository
                .findPersonalApoyoByIdPHAndRolAndTipoDocAndNumDoc(personalApoyo.getIdPropiedad(), personalApoyo.getRol(),
                        personalApoyo.getTipoDocumento(), personalApoyo.getNumeroDocumento());

        System.out.println("Qué tiene existePersonalActivo?: " + actualizarPersonal.toString());
        System.out.println("Qué tiene existePersonalActivo en bool?: " + actualizarPersonal.isPresent());

        if (actualizarPersonal.isPresent()) {
            System.out.println("Actualizando datos de " + personalApoyo.getRol() + " en el sistema.");
            personalApoyoRepository.updateEstadoAndEmailAndPassByTipoAndNumDoc(personalApoyo.getEstado(),
                    personalApoyo.getEmail(), personalApoyo.getPass(), personalApoyo.getTipoDocumento(), personalApoyo.getNumeroDocumento());
            return 4; //Guardado
        }
        personalApoyoRepository.save(personalApoyo);
        return 4;
    }

    public Boolean existeRevisor(String idPropiedad) {
        return personalApoyoRepository.findPersonalApoyoByIdPHAndRol(Integer.parseInt(idPropiedad), "REVISOR", true).isPresent();
    }

    public Boolean existeSecretary(String idPropiedad) {
        return personalApoyoRepository.findPersonalApoyoByIdPHAndRol(Integer.parseInt(idPropiedad), "SECRETARIO", true).isPresent();
    }

    public PersonalApoyo patchPersonal(PersonalApoyo personalApoyo) {

        System.out.println("Verificando existencia de " + personalApoyo.getRol() + " en el sistema");
        Optional<PersonalApoyo> existePersonal = personalApoyoRepository.findPersonalApoyoByIdPHAndRol(personalApoyo.getIdPropiedad(),
                personalApoyo.getRol(), true);

        if (existePersonal.isPresent()) {
            System.out.println("Se procede a actualizar el " + personalApoyo.getRol() + " en el sistema.");
            Integer response = personalApoyoRepository.changeEstado(existePersonal.get().getId(), personalApoyo.getEstado());
            return existePersonal.get();
        }

        throw new BusinessException("El usuario no está registrado en el sistema");

    }

    public PersonalApoyo findPersonalByEmail(String email) {
        Optional<PersonalApoyo> personal = personalApoyoRepository.findByEmail(email);

        if (!personal.isPresent()) {
            System.out.println("Correo inexistente");
            throw new BusinessException("Correo inexistente");
        }
/*
        if(!personal.get().getPass().equals(encodedPass)){
            log.debug("Password incorrecto");
            throw new BusinessException("Password incorrecto");
        }*/

        return personal.get();
    }
}
