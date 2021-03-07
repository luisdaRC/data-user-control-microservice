package co.edu.unicartagena.control.application.commands.personalapoyo;

import co.edu.unicartagena.control.application.commands.Command;
import co.edu.unicartagena.control.domain.services.PersonalApoyoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ExisteRevisorEnPHCommand implements Command<Boolean, String> {

    private final PersonalApoyoService personalApoyoService;

    @Autowired
    public ExisteRevisorEnPHCommand(PersonalApoyoService personalApoyoService) {
        this.personalApoyoService = personalApoyoService;
    }

    public Boolean ejecutar(String idPropiedad) {
        System.out.println("Ejecutando el comando: ExisteRevisorEnPH con id de PH: "+ idPropiedad);
        return personalApoyoService.existeRevisor(idPropiedad);
    }

}
