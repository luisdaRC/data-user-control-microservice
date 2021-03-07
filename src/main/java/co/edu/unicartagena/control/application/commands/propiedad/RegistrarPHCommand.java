package co.edu.unicartagena.control.application.commands.propiedad;

import co.edu.unicartagena.control.application.builders.PropiedadHorizontalBuilder;
import co.edu.unicartagena.control.application.commands.Command;
import co.edu.unicartagena.control.application.dtos.PropiedadHorizontalDTO;
import co.edu.unicartagena.control.domain.services.PHService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RegistrarPHCommand implements Command<PropiedadHorizontalDTO, PropiedadHorizontalDTO> {

    private final PHService phService;

    @Autowired
    public RegistrarPHCommand(PHService phService) {
        this.phService = phService;
    }

    public PropiedadHorizontalDTO ejecutar(PropiedadHorizontalDTO phDTO) {
        System.out.println("Ejecutando el comando: RegistrarPH con los datos: "+ phDTO);
        return PropiedadHorizontalBuilder.crearPHDTODesdeEntidad(
                phService.registrarPropiedad(PropiedadHorizontalBuilder
                        .crearPHDesdeDTO(phDTO)));
    }


}
