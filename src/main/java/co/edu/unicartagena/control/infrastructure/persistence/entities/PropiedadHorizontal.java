package co.edu.unicartagena.control.infrastructure.persistence.entities;

import javax.persistence.*;
import java.beans.BeanInfo;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "PropiedadHorizontal")
public class PropiedadHorizontal implements Serializable {

    @Id
    @Column(name = "idPropiedadHorizontal")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPropiedadHorizontal;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "tipo")
    private String tipo;

    @Column(name = "direccion")
    private String direccion;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPropiedad")
    private List<PersonalApoyo> personalApoyoList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPropiedad")
    private List<BienPrivado> bienPrivadoList;

    public Integer getIdPropiedadHorizontal() {
        return idPropiedadHorizontal;
    }

    public void setIdPropiedadHorizontal(Integer idPropiedadHorizontal) {
        this.idPropiedadHorizontal = idPropiedadHorizontal;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public List<PersonalApoyo> getPersonalApoyoList() {
        return personalApoyoList;
    }

    public void setPersonalApoyoList(List<PersonalApoyo> personalApoyoList) {
        this.personalApoyoList = personalApoyoList;
    }

    public List<BienPrivado> getBienPrivadoList() {
        return bienPrivadoList;
    }

    public void setBienPrivadoList(List<BienPrivado> bienPrivadoList) {
        this.bienPrivadoList = bienPrivadoList;
    }
}
