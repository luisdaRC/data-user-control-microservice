package co.edu.unicartagena.control.application.commands.propiedad;

import co.edu.unicartagena.control.application.builders.PropiedadHorizontalBuilder;
import co.edu.unicartagena.control.application.commands.Command;
import co.edu.unicartagena.control.application.dtos.PropiedadHorizontalDTO;
import co.edu.unicartagena.control.application.dtos.RestriccionDTO;
import co.edu.unicartagena.control.domain.services.PHService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RegistrarRestriccionCommand implements Command<Integer, RestriccionDTO> {

    private final PHService phService;

    @Autowired
    public RegistrarRestriccionCommand(PHService phService) {
        this.phService = phService;
    }

    public Integer ejecutar(RestriccionDTO restriccionDTO) {
        System.out.println("Ejecutando el comando: RegistrarRestriccion con los datos: "+ restriccionDTO);
        return phService.registrarRestriccion(restriccionDTO.getIdPropiedadHorizontal(),
                restriccionDTO.getAdmin(), restriccionDTO.getConsejo(),
                restriccionDTO.getPresupuesto(), restriccionDTO.getProposicionGeneral(),
                restriccionDTO.getComiteConvivencia(), restriccionDTO.getRevisor());
    }
}
