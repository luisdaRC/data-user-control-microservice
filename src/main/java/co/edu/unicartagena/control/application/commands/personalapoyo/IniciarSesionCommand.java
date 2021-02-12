package co.edu.unicartagena.control.application.commands.personalapoyo;

import co.edu.unicartagena.control.application.builders.PersonalApoyoBuilder;
import co.edu.unicartagena.control.application.commands.Command;
import co.edu.unicartagena.control.application.dtos.PersonalApoyoDTO;
import co.edu.unicartagena.control.application.dtos.UserRequestDTO;
import co.edu.unicartagena.control.domain.services.PersonalApoyoService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class IniciarSesionCommand implements Command<PersonalApoyoDTO, UserRequestDTO> {

    private final PersonalApoyoService personalApoyoService;

    @Autowired
    public IniciarSesionCommand(PersonalApoyoService personalApoyoService){
        this.personalApoyoService = personalApoyoService;
    }

    public PersonalApoyoDTO ejecutar(UserRequestDTO userRequestDTO){
        log.debug("Ejecutando el comando: IniciarSesion");
        String email = userRequestDTO.getEmail().toLowerCase();
        return PersonalApoyoBuilder.crearPersonalApoyoDTODesdeEntidad(
                personalApoyoService.findPersonalByEmailAndPass(email, userRequestDTO.getPass()));
    }
}
