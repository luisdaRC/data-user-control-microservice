package co.edu.unicartagena.control.application.commands.propiedad;

import co.edu.unicartagena.control.application.commands.Command;
import co.edu.unicartagena.control.domain.services.PHService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class ExistePHCommand implements Command<Boolean,String> {

    private final PHService phService;

    @Autowired
    public ExistePHCommand(PHService phService){ this.phService = phService; }

    public Boolean ejecutar(String idPropiedad){
        System.out.println("Veamos si existe un id PH: "+idPropiedad);
        log.debug("Ejecutando el comando: ExistePH con id de PH: {}", idPropiedad);
        return phService.existePropiedad(idPropiedad);
    }

}
