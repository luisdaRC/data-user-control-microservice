package co.edu.unicartagena.control.domain.repositories;

import co.edu.unicartagena.control.domain.entities.PersonalApoyo;

import java.util.Optional;

public interface PersonalApoyoRepository {

    //Search if in DB is any Personal active (estado==true)
    Optional<PersonalApoyo> findPersonalApoyoByIdPHAndRol (Integer idPropiedad, String rol, Boolean estado);

    //Search if exist anything with those parameters
    Optional<PersonalApoyo> findPersonalApoyoByIdPHAndRolAndTipoDocAndNumDoc (Integer idPropiedad, String rol, String tipoDoc, String numDoc);

    Optional<PersonalApoyo> findByEmail(String email);

    PersonalApoyo save (PersonalApoyo personalApoyo);

    //Search by idPersonal and update estado
    Integer changeEstado (Integer id, Boolean estado);

    //Search by tipo an num, and update the others
    Integer updateEstadoAndEmailAndPassByTipoAndNumDoc (Boolean estado, String  email, String pass, String tipoDoc, String numDoc);
}
