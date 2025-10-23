package com.example.EcomerceUribe.modelos;


import com.example.EcomerceUribe.ayudas.Departamentos;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "clientes")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "address", nullable = false, length = 100)
    private String direccion;

    @Column(name = "rating", nullable = true)
    private Double calificacion;

    @Column(name = "paymentReference", nullable = false, unique = true, length = 50)
    private String referenciaPago;

    @Enumerated(EnumType.STRING)
    @Column(name = "department", nullable = false, length = 30)
    private Departamentos departamento;

    @Column(name = "city", nullable = false, length = 50)
    private String ciudad;

    // Relación opcional con Usuario
    @OneToOne
    @JoinColumn(name = "usuario_id")
    @JsonBackReference(value = "relacionusuariocliente")
    private Usuario usuario;

    public Cliente() {
    }

    public Cliente(Integer id, String direccion, Double calificacion, String referenciaPago, Departamentos departamento, String ciudad) {
        this.id = id;
        this.direccion = direccion;
        this.calificacion = calificacion;
        this.referenciaPago = referenciaPago;
        this.departamento = departamento;
        this.ciudad = ciudad;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Double getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(Double calificacion) {
        this.calificacion = calificacion;
    }

    public String getReferenciaPago() {
        return referenciaPago;
    }

    public void setReferenciaPago(String referenciaPago) {
        this.referenciaPago = referenciaPago;
    }

    public Departamentos getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamentos departamento) {
        this.departamento = departamento;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }
}
