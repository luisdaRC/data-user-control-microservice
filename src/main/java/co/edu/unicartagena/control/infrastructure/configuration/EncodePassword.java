package co.edu.unicartagena.control.infrastructure.configuration;
import co.edu.unicartagena.control.application.dtos.PersonalApoyoDTO;
import co.edu.unicartagena.control.application.dtos.UserRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

public class EncodePassword {

    @Autowired
    PasswordEncoder passwordEncoder;

    public EncodePassword(){}

    public PersonalApoyoDTO encodePassword(PersonalApoyoDTO personalApoyoDTO){
        return PersonalApoyoDTO.builder()
                .idPropiedad(personalApoyoDTO.getIdPropiedad())
                .email(personalApoyoDTO.getEmail())
                .pass(passwordEncoder.encode(personalApoyoDTO.getPass()))
                .estado(personalApoyoDTO.getEstado())
                .rol(personalApoyoDTO.getRol())
                .nombres(personalApoyoDTO.getNombres())
                .numeroDocumento(personalApoyoDTO.getNumeroDocumento())
                .tipoDocumento(personalApoyoDTO.getTipoDocumento())
                .build();
    }

    public UserRequestDTO encodePassword(UserRequestDTO userRequestDTO){
        return UserRequestDTO.builder()
                .email(userRequestDTO.getEmail())
                .pass(passwordEncoder.encode(userRequestDTO.getPass()))
                .build();
    }
}
