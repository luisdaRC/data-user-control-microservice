package co.edu.unicartagena.control.application.commands.propiedad;

import co.edu.unicartagena.control.application.commands.Command;
import co.edu.unicartagena.control.domain.services.PHService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ExistePHCommand implements Command<Boolean, String> {

    private final PHService phService;

    @Autowired
    public ExistePHCommand(PHService phService) {
        this.phService = phService;
    }

    public Boolean ejecutar(String idPropiedad) {
        System.out.println("Ejecutando el comando: ExistePH con id de PH: "+ idPropiedad);
        return phService.existePropiedad(idPropiedad);
    }

}
