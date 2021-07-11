package co.edu.unicartagena.control.application.commands.propiedad;

import co.edu.unicartagena.control.application.builders.PropiedadHorizontalBuilder;
import co.edu.unicartagena.control.application.commands.Command;
import co.edu.unicartagena.control.application.dtos.CoeficienteDTO;
import co.edu.unicartagena.control.application.dtos.PropiedadHorizontalDTO;
import co.edu.unicartagena.control.domain.services.PHService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UpdateCoeficienteCommand implements Command<Integer, CoeficienteDTO> {

    private final PHService phService;

    @Autowired
    public UpdateCoeficienteCommand(PHService phService) {
        this.phService = phService;
    }

    public Integer ejecutar(CoeficienteDTO coeficienteDTO) {
        System.out.println("Ejecutando el comando: UpdateCoeficiente con los datos: "+ coeficienteDTO);
        return phService.updateCoeficiente(coeficienteDTO.getCoeficiente(),
                coeficienteDTO.getTipoDocumento(), coeficienteDTO.getNumeroDocumento());
    }



}
