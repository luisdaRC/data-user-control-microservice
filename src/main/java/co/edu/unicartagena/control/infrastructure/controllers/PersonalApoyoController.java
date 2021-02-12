package co.edu.unicartagena.control.infrastructure.controllers;

import co.edu.unicartagena.control.application.commands.personalapoyo.*;
import co.edu.unicartagena.control.application.dtos.PersonalApoyoDTO;
import co.edu.unicartagena.control.application.dtos.UserRequestDTO;
import co.edu.unicartagena.control.infrastructure.configuration.EncodePassword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/personal-apoyo")
public class PersonalApoyoController {

    RegistrarPersonalCommand registrarPersonalCommand;
    ExisteRevisorEnPHCommand existeRevisorEnPHCommand;
    ExisteSecretaryEnPHCommand existeSecretaryEnPHCommand;
    PatchPersonalCommand patchPersonalCommand;
    IniciarSesionCommand iniciarSesionCommand;
    EncodePassword encode;

    @Autowired
    public PersonalApoyoController() {
    }

    @PostMapping(value = "/revisor", produces = MediaType.APPLICATION_JSON_VALUE)
    public PersonalApoyoDTO registrarRevisor(@RequestBody PersonalApoyoDTO personalApoyoDTO) {
        PersonalApoyoDTO personalApoyoDTO1 = encode.encodePassword(personalApoyoDTO);
        return registrarPersonalCommand.ejecutar(personalApoyoDTO1);
    }

    @PostMapping(value = "/secretary", produces = MediaType.APPLICATION_JSON_VALUE)
    public PersonalApoyoDTO registrarSecretario(@RequestBody PersonalApoyoDTO personalApoyoDTO) {
        PersonalApoyoDTO personalApoyoDTO1 = encode.encodePassword(personalApoyoDTO);
        return registrarPersonalCommand.ejecutar(personalApoyoDTO1);
    }

    @GetMapping(value = "/revisor", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> existeRevisorEnPH(
            @RequestParam(name = "idPropiedadHorizontal") String idPropiedad) {

        boolean exist = existeRevisorEnPHCommand.ejecutar(idPropiedad);

        Map<Object, Object> model = new HashMap<>();
        model.put("existeRevisor", exist);
        return ResponseEntity.ok().body(model);
    }

    @GetMapping(value = "/secretary", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> existeSecretaryEnPH(
            @RequestParam(name = "idPropiedadHorizontal") String idPropiedad) {

        boolean exist = existeSecretaryEnPHCommand.ejecutar(idPropiedad);

        Map<Object, Object> model = new HashMap<>();
        model.put("existeSecretary", exist);
        return ResponseEntity.ok().body(model);
    }

    @PatchMapping(value = "/patch", produces = MediaType.APPLICATION_JSON_VALUE)
    public PersonalApoyoDTO patchPersonal(@RequestBody PersonalApoyoDTO personalApoyoDTO) {
        PersonalApoyoDTO personalApoyoDTO1 = encode.encodePassword(personalApoyoDTO);
        return patchPersonalCommand.ejecutar(personalApoyoDTO1);
    }

    @PostMapping(value = "/signin", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> iniciarSesion(UserRequestDTO body) {
        UserRequestDTO user = encode.encodePassword(body);
        PersonalApoyoDTO personal = iniciarSesionCommand.ejecutar(user);

        // In personal varibale is contained the PersonalApoyoDTO obtained in DB.
        // Play with it (token and model.put according to core).
        return null;
    }
}
