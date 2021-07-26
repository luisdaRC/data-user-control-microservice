package co.edu.unicartagena.control.application.commands.personalapoyo;

import co.edu.unicartagena.control.application.builders.PersonalApoyoBuilder;
import co.edu.unicartagena.control.application.commands.Command;
import co.edu.unicartagena.control.application.dtos.PersonalApoyoDTO;
import co.edu.unicartagena.control.domain.services.PersonalApoyoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RegistrarPersonalCommand implements Command<Integer, PersonalApoyoDTO> {

    private final PersonalApoyoService personalApoyoService;

    @Autowired
    public RegistrarPersonalCommand(PersonalApoyoService personalApoyoService) {
        this.personalApoyoService = personalApoyoService;
    }

    public Integer ejecutar(PersonalApoyoDTO personalApoyoDTO) {
        System.out.println("Ejecutando el comando: RegistrarPersonal con los datos: "+ personalApoyoDTO);
        return personalApoyoService.registrarPersonal(PersonalApoyoBuilder
                        .crearPersonalApoyodesdeDTO(personalApoyoDTO));
    }
}
