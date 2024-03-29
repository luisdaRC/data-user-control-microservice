package co.edu.unicartagena.control.application.commands.propiedad;

import co.edu.unicartagena.control.application.builders.PropiedadHorizontalBuilder;
import co.edu.unicartagena.control.application.commands.Command;
import co.edu.unicartagena.control.application.dtos.PropiedadHorizontalDTO;
import co.edu.unicartagena.control.domain.services.PHService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class RegistrarPHCommand implements Command<PropiedadHorizontalDTO,PropiedadHorizontalDTO> {

    private final PHService phService;

    @Autowired
    public RegistrarPHCommand(PHService phService){ this.phService = phService; }

    public PropiedadHorizontalDTO ejecutar(PropiedadHorizontalDTO phDTO){
        log.debug("Ejecutando el comando: RegistrarPH con los datos: {}", phDTO);
        return PropiedadHorizontalBuilder.crearPHDTODesdeEntidad(
                phService.registrarPropiedad(PropiedadHorizontalBuilder
                        .crearPHDesdeDTO(phDTO)));
    }



}
