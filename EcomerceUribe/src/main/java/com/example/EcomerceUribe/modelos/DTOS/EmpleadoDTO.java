package com.example.EcomerceUribe.modelos.DTOS;

public class EmpleadoDTO {
    private Integer id;
    private String cargo;
    private Double salario;
    private String fechaIngreso;
    private String area;
    private Integer usuarioId;

    public EmpleadoDTO() {
    }

    public EmpleadoDTO(Integer id, String cargo, Double salario, String fechaIngreso, String area, Integer usuarioId) {
        this.id = id;
        this.cargo = cargo;
        this.salario = salario;
        this.fechaIngreso = fechaIngreso;
        this.area = area;
        this.usuarioId = usuarioId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public Double getSalario() {
        return salario;
    }

    public void setSalario(Double salario) {
        this.salario = salario;
    }

    public String getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(String fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Integer getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Integer usuarioId) {
        this.usuarioId = usuarioId;
    }
}
