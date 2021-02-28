package co.edu.unicartagena.control.application.commands.propiedad;

import co.edu.unicartagena.control.application.builders.BienPrivadoBuilder;
import co.edu.unicartagena.control.application.builders.PersonaBuilder;
import co.edu.unicartagena.control.application.commands.Command;
import co.edu.unicartagena.control.application.dtos.PersonaListDTO;
import co.edu.unicartagena.control.domain.entities.BienPrivado;
import co.edu.unicartagena.control.domain.entities.Persona;
import co.edu.unicartagena.control.domain.services.PHService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Log4j2
@Component
public class UpdatePersonasYBienesCommand implements Command<List<Persona>,List<PersonaListDTO>> {

    private final PHService phService;
    List<Persona> personas = new ArrayList<Persona>();
    List<BienPrivado> bienPrivados = new ArrayList<BienPrivado>();

    @Autowired
    public UpdatePersonasYBienesCommand(PHService phService) {this.phService = phService;}

    public List<Persona> ejecutar(List <PersonaListDTO> personaListDTOS){
        log.debug("Ejecutando el comando: UpdatePersonasYBienes");
        Set<Integer> ids = new HashSet<Integer>();
        Map<Integer,Boolean> validos = new HashMap<Integer, Boolean>();

        // ids added to set to avoid repeated. Filter data fist by PROPIETARIO and after by RESIDENTE.
        for (PersonaListDTO personaListDTO : personaListDTOS) ids.add(personaListDTO.getId());

        for (Integer id:ids) validos.put(id,false);

        // Cycles done to delete multiple rows with the same idPersona, obtained in core query
        for(Map.Entry mapPropietarios:validos.entrySet()) {
            Integer key = (Integer) mapPropietarios.getKey();
            Boolean value = (Boolean) mapPropietarios.getValue();

            for(PersonaListDTO p:personaListDTOS){

                if (!value && p.getRol().equals("PROPIETARIO") && key.equals(p.getId())) {
                    personas.add(PersonaBuilder.crearPersonaDesdePersonaList(p));
                    bienPrivados.add(BienPrivadoBuilder.crearBienPrivadoDesdeDTO(p.getBienPrivado()));
                    validos.put(key,true);
                    break;
                }
            }
        }

        for(Map.Entry mapResidentes:validos.entrySet()) {
            for(PersonaListDTO p:personaListDTOS){
                Integer key = (Integer) mapResidentes.getKey();
                Boolean value = (Boolean) mapResidentes.getValue();

                if (!value && p.getRol().equals("RESIDENTE") && key.equals(p.getId())) {
                    personas.add(PersonaBuilder.crearPersonaDesdePersonaList(p));
                    bienPrivados.add(BienPrivadoBuilder.crearBienPrivadoDesdeDTO(p.getBienPrivado()));
                    validos.put(key,true);
                    break;
                }
            }
        }

        return phService.updateData(personas, bienPrivados);
    }
}
