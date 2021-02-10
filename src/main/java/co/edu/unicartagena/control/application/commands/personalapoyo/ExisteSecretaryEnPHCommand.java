package co.edu.unicartagena.control.application.commands.personalapoyo;

import co.edu.unicartagena.control.application.commands.Command;
import co.edu.unicartagena.control.domain.services.PersonalApoyoService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class ExisteSecretaryEnPHCommand implements Command<Boolean,String> {

    private final PersonalApoyoService personalApoyoService;

    @Autowired
    public ExisteSecretaryEnPHCommand(PersonalApoyoService personalApoyoService) {
        this.personalApoyoService = personalApoyoService;
    }

    public Boolean ejecutar(String idPropiedad){
        log.debug("Ejecutando el comando: ExisteSecretaryEnPH con id de PH: {}", idPropiedad);
        return personalApoyoService.existeSecretary(idPropiedad);
    }
}
