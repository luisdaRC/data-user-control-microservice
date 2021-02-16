package co.edu.unicartagena.control.infrastructure.persistence.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "PersonalApoyo")
public class PersonalApoyo implements Serializable {

    @Id
    @Column(name = "idPersonalApoyo")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPersonalApoyo;

    @JoinColumn(name = "PropiedadHorizontal_idPH", referencedColumnName = "idPropiedadHorizontal")
    @ManyToOne(optional = true)
    private PropiedadHorizontal idPropiedad;

    @Column(name = "email")
    private String email;

    @Column(name = "pass")
    private String pass;

    @Column(name = "estado")
    private Boolean estado;

    @Column(name = "rol")
    private String rol;

    @Column(name = "nombres")
    private String nombres;

    @Column(name = "numeroDocumento")
    private String numeroDocumento;

    @Column(name = "tipoDocumento")
    private String tipoDocumento;


    public Integer getIdPersonalApoyo() {
        return idPersonalApoyo;
    }

    public void setIdPersonalApoyo(Integer idPersonalApoyo) {
        this.idPersonalApoyo = idPersonalApoyo;
    }

    public PropiedadHorizontal getIdPropiedad() {
        return idPropiedad;
    }

    public void setIdPropiedad(PropiedadHorizontal idPropiedad) {
        this.idPropiedad = idPropiedad;
    }

    public String getEmail() {
        return email;
    }

    public String getPass() {
        return pass;
    }

    public boolean isEstado() {
        return estado;
    }

    public String getRol() {
        return rol;
    }

    public String getNombres() {
        return nombres;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }
}
