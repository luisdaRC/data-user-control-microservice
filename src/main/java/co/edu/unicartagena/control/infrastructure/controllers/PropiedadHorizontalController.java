package co.edu.unicartagena.control.infrastructure.controllers;

import co.edu.unicartagena.control.application.commands.propiedad.ExistePHCommand;
import co.edu.unicartagena.control.application.commands.propiedad.RegistrarPHCommand;
import co.edu.unicartagena.control.application.commands.propiedad.UpdateCoeficienteCommand;
import co.edu.unicartagena.control.application.commands.propiedad.UpdatePersonasYBienesCommand;
import co.edu.unicartagena.control.application.dtos.CoeficienteDTO;
import co.edu.unicartagena.control.application.dtos.PersonaListDTO;
import co.edu.unicartagena.control.application.dtos.PropiedadHorizontalDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.extern.log4j.Log4j2;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Log4j2
@RestController
@RequestMapping("/propiedad-horizontal")
public class PropiedadHorizontalController {

    RegistrarPHCommand registrarPHCommand;
    ExistePHCommand existePHCommand;
    UpdatePersonasYBienesCommand updatePersonasYBienesCommand;
    UpdateCoeficienteCommand updateCoeficienteCommand;

    @Autowired
    public PropiedadHorizontalController(RegistrarPHCommand registrarPHCommand,
                                         ExistePHCommand existePHCommand,
                                         UpdatePersonasYBienesCommand updatePersonasYBienesCommand,
                                         UpdateCoeficienteCommand updateCoeficienteCommand) {

        this.registrarPHCommand = registrarPHCommand;
        this.existePHCommand = existePHCommand;
        this.updatePersonasYBienesCommand = updatePersonasYBienesCommand;
        this.updateCoeficienteCommand = updateCoeficienteCommand;
    }

    /**
     * No es necesario verificar si existe la propiedad dado que la validación está hecho en el front
     */
    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> registrarPropiedadHorizontal(@RequestBody List<PropiedadHorizontalDTO> propiedadHorizontalDTO) {
        System.out.println("Entra a registrar propiedad");
        return ResponseEntity.ok()
                .body(registrarPHCommand.ejecutar(propiedadHorizontalDTO.get(0)));
    }

    @GetMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> existePropiedadHorizontal(
            @RequestParam(name = "idPropiedadHorizontal") String idPropiedad) {

        log.debug("Entra a existePH");
        System.out.println("Entra a existePH: " + idPropiedad);

        Boolean exist = existePHCommand.ejecutar(idPropiedad);

        log.debug("Asigna boolean");

        Map<Object, Object> model = new HashMap<>();
        model.put("existePH", exist);
        System.out.println("Asigna y retorna boolean");
        return ResponseEntity.ok().body(model);
    }

    @PostMapping(value = "/updateCoeficiente", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> updateCoeficiente(@RequestBody CoeficienteDTO coeficienteDTO) {
        System.out.println("Entra a updateCoeficiente" + coeficienteDTO);

        try{
            return ResponseEntity.ok().body(updateCoeficienteCommand.ejecutar(coeficienteDTO));
        }catch(Exception e){
            return ResponseEntity.ok().body("Error: "+e.getMessage());
        }

    }

    /**
     * Caso 3: Que se tengan registros y hayan menos en la lista del core (eliminados).
     * (In this case, the records in database will be the same because any information
     * about the person deleted would be needed in the future, regarding to votes).
     */
    @PostMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> updatePersonasYBienes(@RequestBody List<PersonaListDTO> personaListDTO) {
        System.out.println("Entra a updatePersonas" + personaListDTO.get(0));
        return ResponseEntity.ok().body(updatePersonasYBienesCommand.ejecutar(personaListDTO));
    }

}
