package com.mc.integration.dao;

import com.mc.integration.configuration.PropertiesManager;
import com.mc.integration.dto.Bpo;

import java.sql.*;
import static java.sql.DriverManager.getConnection;

public class BpoDAO {

    private String reference;
    private Bpo bpo;
    private PropertiesManager propertiesManager;
    private Statement stmt;

    public BpoDAO (PropertiesManager propertiesManager) {
        this.propertiesManager = propertiesManager;
        connect();
    }

    public Bpo getData(String reference) {
        this.reference = reference;
        this.bpo = new Bpo();
        this.bpo.setReference(reference);
        this.getAllData();
        return this.bpo;
    }

    public Bpo getData(String reference, String idContact) {
        this.reference = reference;
        this.bpo = new Bpo();
        this.bpo.setReference(reference);
        this.getAllContactData(idContact);
        return this.bpo;
    }

    private void connect() {
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            Connection con = null;
            con = getConnection(propertiesManager.getGenesisDbUrl(), propertiesManager.getGenesisDbUsername(), propertiesManager.getGenesisDbPassword());
            stmt = con.createStatement();
        } catch(ClassNotFoundException | SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void getAllData() {
        try {
            ResultSet res = stmt.executeQuery("SELECT * FROM VIEW_BP_SAP WHERE REFERENCIA = '"+this.reference+"'");
            while(res.next()) {
                this.bpo.setTipoId(res.getString("C9"));
                this.bpo.setId(res.getString("C19"));
                this.bpo.setSexo(res.getString("C1"));
                this.bpo.setEntidad(res.getString("C2"));
                this.bpo.setActividad(res.getString("C3"));
                this.bpo.setSegmento(res.getString("C4"));
                this.bpo.setOficina(res.getString("C5"));
                this.bpo.setGrupoVendedor(res.getString("C6"));
                this.bpo.setClaseCliente(res.getString("C7"));
                this.bpo.setTipoFacturacion(res.getString("C8"));
                this.bpo.setNombre(res.getString("C10"));
                this.bpo.setApellido(res.getString("C11"));
                this.bpo.setRazonSocial(res.getString("C12"));
                this.bpo.setTelefono(res.getString("C13"));
                this.bpo.setCorreoElectronico(res.getString("C14"));
                this.bpo.setPais(res.getString("C15"));
                this.bpo.setMunicipio(res.getString("C16"));
                this.bpo.setDepartamento(res.getString("C17"));
                this.bpo.setDireccion(res.getString("C18"));
            }
        }  catch(SQLException ex) {
            System.out.println(ex.getMessage());
        } catch(Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void getAllContactData(String idContact) {
        try {
            ResultSet res = stmt.executeQuery("SELECT * FROM VIEW_CONTACT_BP_SAP WHERE REFERENCIA = '"+this.reference+"' and ID_CONTACTO='"+idContact+"'");
            while(res.next()) {
                this.bpo.setNumeroBp(res.getString("C1"));
                this.bpo.setTipoId(res.getString("C2"));
                this.bpo.setId(res.getString("C3"));
                this.bpo.setNombre(res.getString("C4"));
                this.bpo.setApellido(res.getString("C5"));
                this.bpo.setTelefono(res.getString("C6"));
                this.bpo.setDireccion(res.getString("C7"));
                this.bpo.setCorreoElectronico(res.getString("C12"));
                this.bpo.setPais(res.getString("C8"));
                this.bpo.setMunicipio(res.getString("C10"));
                this.bpo.setDepartamento(res.getString("C9"));
                this.bpo.setTipoContacto(res.getString("C11"));
            }
        } catch(SQLException ex) {
            System.out.println(ex.getMessage());
        } catch(Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public boolean insertBp(String reference, String bpCode) {
        try {
            stmt.execute("UPDATE GHHOJVID SET HDVBPCODE = '" + bpCode + "' WHERE TRIM(HDVCODIGO) = '" + reference.trim() + "'");
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    public boolean insertBpContact(String reference, String bpCode, String idContact) {
        try {
            stmt.execute("UPDATE GHCONCLI SET CONBPSAP = '" + bpCode + "' WHERE TRIM(CONCLIREF) = '" + reference.trim() + "' AND TRIM(CONCLINIT) = '"+idContact.trim()+"'");
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
}
