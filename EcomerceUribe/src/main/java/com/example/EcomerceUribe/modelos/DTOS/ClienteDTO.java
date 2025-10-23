package com.example.EcomerceUribe.modelos.DTOS;

public class ClienteDTO {
    private Integer id;
    private String direccion;
    private String ciudad;
    private String referenciaPago;
    private Double calificacion;
    private String departamento;
    private Integer usuarioId;

    public ClienteDTO() {
    }

    public ClienteDTO(Integer id, String direccion, String ciudad, String referenciaPago, Double calificacion, String departamento, Integer usuarioId) {
        this.id = id;
        this.direccion = direccion;
        this.ciudad = ciudad;
        this.referenciaPago = referenciaPago;
        this.calificacion = calificacion;
        this.departamento = departamento;
        this.usuarioId = usuarioId;
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

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getReferenciaPago() {
        return referenciaPago;
    }

    public void setReferenciaPago(String referenciaPago) {
        this.referenciaPago = referenciaPago;
    }

    public Double getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(Double calificacion) {
        this.calificacion = calificacion;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public Integer getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Integer usuarioId) {
        this.usuarioId = usuarioId;
    }
}
