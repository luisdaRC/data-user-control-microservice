package co.edu.unicartagena.control.infrastructure.persistence.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Persona")
public class Persona implements Serializable {

    @Id
    @Column(name = "idPersona")
    private Integer idPersona;

    @JoinColumn(name = "BienPrivado_idBienPrivado", referencedColumnName = "idBienPrivado")
    @ManyToOne(optional = true)
    private BienPrivado idBienPrivado;

    @Column(name = "nombres")
    private String nombres;

    @Column(name = "apellidos")
    private String apellidos;

    @Column(name = "tipoDocumento")
    private String tipoDocumento;

    @Column(name = "numeroDocumento")
    private String numeroDocumento;

    @Column(name = "rol")
    private String rol;


    public Integer getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Integer idPersona) {
        this.idPersona = idPersona;
    }

    public BienPrivado getidBienPrivado() {
        return idBienPrivado;
    }

    public void setidBienPrivado(BienPrivado idBienPrivado) {
        this.idBienPrivado = idBienPrivado;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

}
