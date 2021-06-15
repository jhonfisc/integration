package com.mc.integration.service;

import com.mc.integration.configuration.PropertiesManager;
import com.mc.integration.dao.BpoDAO;
import com.mc.integration.dto.BpResponse;
import com.mc.integration.dto.Bpo;
import com.mc.integration.rest.ApiClient;
import com.mc.integration.utils.Validate;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.logging.Logger;

public class ReferenceService extends ApiClient {

    private BpoDAO bpoDAO;
    private Bpo bpo;
    static Logger logger = Logger.getLogger(ReferenceService.class.getName());

    private PropertiesManager propertiesManager;

    public ReferenceService(PropertiesManager propertiesManager) {
        super(propertiesManager.getSapUrlBp());
        this.propertiesManager = propertiesManager;
    }

    public boolean sendReference(String reference) {
        if (Validate.isValidReference(reference)) {
            this.bpoDAO = new BpoDAO(propertiesManager);
            this.bpo = this.bpoDAO.getData(reference);
            try {
                JSONObject res = sendData("01");
                boolean resInsert = this.bpoDAO.insertBp(reference, res.get("NumeroBP").toString());
                if( resInsert != false) {
                    System.out.println("BP creado numero " + res.get("NumeroBP").toString());
                    logger.fine("BP creado numero " + res.get("NumeroBP").toString());
                }
            } catch (JSONException ex) {
                logger.warning("Response invalid. NumeroBP not found");
            }
        }
        return true;
    }

    public boolean sendContact(String reference, String idContact) {
        if (Validate.isValidReference(reference) && Validate.isValidReference(idContact) ) {
            this.bpoDAO = new BpoDAO(propertiesManager);
            this.bpo = this.bpoDAO.getData(reference, idContact);
            try {
                JSONObject res = sendData("02");
                boolean resInsert = this.bpoDAO.insertBpContact(reference, res.get("NumeroBP").toString(), idContact);
                if( resInsert != false) {
                    System.out.println("BP creado para el contacto numero " + res.get("NumeroBP").toString());
                    logger.fine("BP creado para el contacto numero " + res.get("NumeroBP").toString());
                }
            } catch (JSONException ex) {
                logger.warning("Response invalid. NumeroBP not found");
            }
        }
        return true;
    }

    private JSONObject sendData(String actividad) {
        BpResponse bp = new BpResponse();
        try {
            bp.bpo = this.bpo;
            bp.tipoActividad = actividad;

            JSONObject json = createJson(bp);
            JSONObject res;
            if(propertiesManager.getSapHttpsUrl() == "true") {
                res = apiRestClientHttps(json.toString());
            } else {
                res = apiRestClientHttp(json.toString());
            }
            return res;
        } catch (Exception ex) {}
        return null;
    }

    private JSONObject createJson(BpResponse bp) throws JSONException {
        JSONObject data = new JSONObject();

        data.put("TipoActividad", bp.tipoActividad);

        data.put("NumeroBPPrincipal", bp.bpo.getNumeroBp());

        data.put("NumeroBPRelacion", "");

        data.put("TipoId", bp.bpo.getTipoId());

        data.put("Id", bp.bpo.getId());

        if (!bp.bpo.getNombre().isEmpty()) {
            data.put("Nombre", bp.bpo.getNombre());
        } else {
            data.put("Nombre", bp.bpo.getRazonSocial());
        }

        data.put("Apellido", bp.bpo.getApellido());

        data.put("Telefono", bp.bpo.getTelefono());

        data.put("Direccion", bp.bpo.getDireccion());

        data.put("Pais", bp.bpo.getPais());

        data.put("Region", bp.bpo.getDepartamento());

        data.put("Municipio", bp.bpo.getMunicipio());

        data.put("Email", bp.bpo.getCorreoElectronico());

        data.put("Sexo", bp.bpo.getSexo());

        data.put("TipoDeEntidad", bp.bpo.getEntidad());

        data.put("Segmento", bp.bpo.getSegmento());

        data.put("OficinaDeVentas", bp.bpo.getOficina());

        data.put("GrupoVendedores", bp.bpo.getGrupoVendedor());

        data.put("ClaseDeCliente", bp.bpo.getClaseCliente());

        data.put("TipoFacturacion", bp.bpo.getTipoFacturacion());

        data.put("TipoContacto", bp.bpo.getTipoContacto());

        JSONObject request = new JSONObject();
        request.put("Request", data);
        return request;
    }

}
