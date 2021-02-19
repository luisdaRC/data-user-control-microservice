package co.edu.unicartagena.control.infrastructure.persistence.repositories;

import co.edu.unicartagena.control.domain.entities.PersonalApoyo;
import co.edu.unicartagena.control.domain.repositories.PersonalApoyoRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonalApoyoJpaRepository extends JpaRepository<PersonalApoyo,Integer>, PersonalApoyoRepository {


    @Query(value = "SELECT * FROM personalapoyo WHERE propiedadhorizontal_idph = :idPropiedad and rol = :rol and estado = :estado", nativeQuery = true)
    Optional<PersonalApoyo> findPersonalApoyoByIdPHAndRol (@Param("idPropiedad") Integer idPropiedad,
                                                           @Param("rol") String rol,
                                                           @Param("estado") Boolean estado);


    @Query(value = "SELECT * FROM personalapoyo WHERE propiedadhorizontal_idph = :idPropiedad and" +
            " rol = :rol and tipodocumento = :tipoDoc and numerodocumento = :numDoc", nativeQuery = true)
    Optional<PersonalApoyo> findPersonalApoyoByIdPHAndRolAndTipoDocAndNumDoc (@Param("idPropiedad") Integer idPropiedad,
                                                                              @Param("rol") String rol,
                                                                              @Param("tipoDoc") String tipoDoc,
                                                                              @Param("numDoc") String numDoc);


    @Modifying
    @Query(value = "UPDATE personalapoyo SET estado = :estado WHERE propiedadhorizontal_idph = :idPropiedad " +
            "and tipodocumento = :tipoDoc and numerodocumento = :numDoc and rol = :rol", nativeQuery = true)
    PersonalApoyo changeEstado (@Param("tipoDoc") String tipoDoc, @Param("numDoc") String numDoc, @Param("rol") String rol,
                                @Param("idPropiedad") Integer idPropiedad, @Param("estado") Boolean estado);


    @Modifying
    @Query(value = "UPDATE personalapoyo SET email = :email, pass = :pass, estado = :estado " +
            "WHERE tipodocumento = :tipoDoc and numerodocumento = :numDoc", nativeQuery = true)
    PersonalApoyo updateEstadoAndEmailAndPassByTipoAndNumDoc (@Param("estado") Boolean estado,
                                                         @Param("email") String  email,
                                                         @Param("pass") String pass,
                                                         @Param("tipoDoc") String tipoDoc,
                                                         @Param("numDoc") String numDoc);

}
