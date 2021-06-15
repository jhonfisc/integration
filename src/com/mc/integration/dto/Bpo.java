package com.mc.integration.dto;

public class Bpo {

    private String reference;
    private String tipoId;
    private String id;
    private String sexo;
    private String entidad;
    private String actividad;
    private String segmento;
    private String oficina;
    private String grupoVendedor;
    private String claseCliente;
    private String tipoFacturacion;
    private String nombre;
    private String apellido;
    private String razonSocial;
    private String telefono;
    private String correoElectronico;
    private String municipio;
    private String departamento;
    private String pais;
    private String direccion;
    private String tipoContacto;
    private String numeroBp;

    public String getNumeroBp() {
        return numeroBp;
    }

    public void setNumeroBp(String numeroBp) {
        this.numeroBp = numeroBp;
    }

    public String getTipoContacto() {
        return tipoContacto;
    }

    public void setTipoContacto(String tipoContacto) {
        this.tipoContacto = tipoContacto.trim();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id.trim();
    }
    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono.trim();
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico.trim();
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio.trim();
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento.trim();
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais.trim();
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion.trim();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre.trim();
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido.trim();
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial.trim();
    }

    public String getTipoFacturacion() {
        return tipoFacturacion;
    }

    public void setTipoFacturacion(String tipoFacturacion) {
        this.tipoFacturacion = tipoFacturacion.trim();
    }

    public String getClaseCliente() {
        return claseCliente;
    }

    public void setClaseCliente(String claseCliente) {
        this.claseCliente = claseCliente.trim();
    }

    public String getGrupoVendedor() {
        return grupoVendedor;
    }

    public void setGrupoVendedor(String grupoVendedor) {
        this.grupoVendedor = grupoVendedor.trim();
    }

    public String getOficina() {
        return oficina;
    }

    public void setOficina(String oficina) {
        this.oficina = oficina.trim();
    }

    public String getSegmento() {
        return segmento;
    }

    public void setSegmento(String segmento) {
        this.segmento = segmento.trim();
    }

    public String getActividad() {
        return actividad;
    }

    public void setActividad(String actividad) {
        this.actividad = actividad.trim();
    }

    public String getEntidad() {
        return entidad;
    }

    public void setEntidad(String entidad) {
        this.entidad = entidad.trim();
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo.trim();
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference.trim();
    }

    public String getTipoId() {
        return tipoId;
    }

    public void setTipoId(String tipoId) {
        this.tipoId = tipoId.trim();
    }
}
