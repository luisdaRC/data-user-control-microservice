package co.edu.unicartagena.control.application.commands.personalapoyo;

import co.edu.unicartagena.control.application.builders.PersonalApoyoBuilder;
import co.edu.unicartagena.control.application.commands.Command;
import co.edu.unicartagena.control.application.dtos.PersonalApoyoDTO;
import co.edu.unicartagena.control.domain.services.PersonalApoyoService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class PatchPersonalCommand implements Command<PersonalApoyoDTO,PersonalApoyoDTO> {

    private final PersonalApoyoService personalApoyoService;

    @Autowired
    public PatchPersonalCommand(PersonalApoyoService personalApoyoService){
        this.personalApoyoService = personalApoyoService;
    }

    public PersonalApoyoDTO ejecutar(PersonalApoyoDTO personalApoyoDTO){
        log.debug("Ejecutando el comando: RegistrarPersonal con los datos: {}", personalApoyoDTO);
        return PersonalApoyoBuilder.crearPersonalApoyoDTODesdeEntidad(
                personalApoyoService.patchPersonal(PersonalApoyoBuilder
                .crearPersonalApoyodesdeDTO(personalApoyoDTO)));
    }
}
