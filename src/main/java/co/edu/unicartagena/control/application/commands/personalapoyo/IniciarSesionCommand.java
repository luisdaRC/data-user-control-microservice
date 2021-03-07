package co.edu.unicartagena.control.application.commands.personalapoyo;

import co.edu.unicartagena.control.application.builders.PersonalApoyoBuilder;
import co.edu.unicartagena.control.application.commands.Command;
import co.edu.unicartagena.control.application.dtos.PersonalApoyoDTO;
import co.edu.unicartagena.control.application.dtos.UserRequestDTO;
import co.edu.unicartagena.control.domain.services.PersonalApoyoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class IniciarSesionCommand implements Command<PersonalApoyoDTO, UserRequestDTO> {

    private final PersonalApoyoService personalApoyoService;

    @Autowired
    public IniciarSesionCommand(PersonalApoyoService personalApoyoService) {
        this.personalApoyoService = personalApoyoService;
    }

    public PersonalApoyoDTO ejecutar(UserRequestDTO userRequestDTO) {
        System.out.println("Ejecutando el comando: IniciarSesion");
        String email = userRequestDTO.getEmail().toLowerCase();
        return PersonalApoyoBuilder.crearPersonalApoyoDTODesdeEntidad(
                personalApoyoService.findPersonalByEmail(email));
    }
}
