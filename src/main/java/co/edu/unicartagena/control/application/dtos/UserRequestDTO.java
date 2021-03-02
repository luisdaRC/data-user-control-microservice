package co.edu.unicartagena.control.application.dtos;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
public class UserRequestDTO {

    private String email;
    private String password;
}
