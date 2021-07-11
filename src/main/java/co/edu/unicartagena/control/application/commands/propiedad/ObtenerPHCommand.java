package co.edu.unicartagena.control.application.commands.propiedad;

import co.edu.unicartagena.control.application.builders.PropiedadHorizontalBuilder;
import co.edu.unicartagena.control.application.commands.Command;
import co.edu.unicartagena.control.application.dtos.PropiedadHorizontalDTO;
import co.edu.unicartagena.control.domain.services.PHService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ObtenerPHCommand implements Command<PropiedadHorizontalDTO, String> {

    private final PHService phService;

    @Autowired
    public ObtenerPHCommand(PHService phService) {
        this.phService = phService;
    }

    public PropiedadHorizontalDTO ejecutar(String idPropiedad) {
        System.out.println("Ejecutando el comando: ObtenerPH con id de PH: "+idPropiedad);
        return PropiedadHorizontalBuilder.crearPHDTODesdeEntidad(
                phService.obtenerPropiedad(idPropiedad));
    }

}