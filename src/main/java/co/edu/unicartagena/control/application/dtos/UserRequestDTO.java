package co.edu.unicartagena.control.application.dtos;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
public class UserRequestDTO {

    private final String email;
    private final String pass;
}
