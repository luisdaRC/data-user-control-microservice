package co.edu.unicartagena.control.infrastructure.configuration;
import co.edu.unicartagena.control.application.dtos.PersonalApoyoDTO;
import co.edu.unicartagena.control.application.dtos.UserRequestDTO;
import co.edu.unicartagena.control.application.dtos.DataPersonalDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class EncodePassword {


    PasswordEncoder passwordEncoder;

    @Autowired
    public EncodePassword(PasswordEncoder passwordEncoder){
        this.passwordEncoder = passwordEncoder;
    }

    public PersonalApoyoDTO encodePassword(PersonalApoyoDTO personalApoyoDTO){
        System.out.println("Llega a encode");
        DataPersonalDTO dataPersonalDTO =
                new DataPersonalDTO(personalApoyoDTO.getDataPersonal().getEmail(),
                passwordEncoder.encode(personalApoyoDTO.getDataPersonal().getPassword()),
                personalApoyoDTO.getDataPersonal().getNombres(),
                personalApoyoDTO.getDataPersonal().getNumeroDocumento(),
                personalApoyoDTO.getDataPersonal().getTipoDocumento()
                );

        System.out.println("Asigna objeto: "+dataPersonalDTO);

        return PersonalApoyoDTO.builder()
                .idPropiedadHorizontal(personalApoyoDTO.getIdPropiedadHorizontal())
                .estado(personalApoyoDTO.getEstado())
                .rol(personalApoyoDTO.getRol())
                .dataPersonal(dataPersonalDTO)
                .build();
    }

    public UserRequestDTO encodePassword(UserRequestDTO userRequestDTO){
        return UserRequestDTO.builder()
                .email(userRequestDTO.getEmail())
                .pass(passwordEncoder.encode(userRequestDTO.getPass()))
                .build();
    }
}
