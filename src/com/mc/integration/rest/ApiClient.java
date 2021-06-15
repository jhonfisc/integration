package com.mc.integration.rest;

import org.json.JSONObject;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

public class ApiClient {

    private String API_URL;
    static Logger logger = Logger.getLogger("Api Client for SAP API Rest");

    public ApiClient(String url) {
        this.API_URL = url;
        try {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");
            LocalDateTime now = LocalDateTime.now();
            boolean append = true;
            FileHandler handler = new FileHandler("apiRest_" + dtf.format(now) + ".log", append);
            logger.addHandler(handler);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    protected JSONObject apiRestClientHttps(String json) throws IOException {
        URL obj = new URL(API_URL);
        try {
            HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
            String encoding = getAuth();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json; utf-8");
            con.setRequestProperty("Accept", "application/json");
            con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
            con.setDoOutput(true);
            con.setRequestProperty("Authorization", "Basic " + encoding);
            try (OutputStream os = con.getOutputStream()) {
                byte[] input = json.getBytes("utf-8");
                os.write(input, 0, input.length);
            }
            try(BufferedReader br = new BufferedReader(
                    new InputStreamReader(con.getInputStream(), "utf-8"))) {
                StringBuilder response = new StringBuilder();
                String responseLine = null;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
                JSONObject parser = new JSONObject(response.toString());
                JSONObject res = new JSONObject(parser.get("Response").toString());
                if(res.get("ResultadoOperacion").toString().equals("E")) {
                    logger.warning("Codigo Error SAP: "+res.get("CodigoError").toString()+". Descripcion: "+res.get("MensajeError").toString()+". Request: "+json);
                    return null;
                }
                return res;
            }
        }catch (Exception ex) {
            System.out.println("exception "+ex.getMessage());
            return null;
        }
    }

    protected JSONObject apiRestClientHttp(String json) throws IOException {
        URL obj = new URL(API_URL);
        try {
            System.out.println(json);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            String encoding = getAuth();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json; utf-8");
            con.setRequestProperty("Accept", "application/json");
            con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
            con.setDoOutput(true);
            con.setRequestProperty("Authorization", "Basic " + encoding);
            try (OutputStream os = con.getOutputStream()) {
                byte[] input = json.getBytes("utf-8");
                os.write(input);
            }
            try(BufferedReader br = new BufferedReader(
                    new InputStreamReader(con.getInputStream(), "utf-8"))) {
                StringBuilder response = new StringBuilder();
                String responseLine = null;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
                JSONObject parser = new JSONObject(response.toString());
                JSONObject res = new JSONObject(parser.get("Response").toString());
                if(res.get("ResultadoOperacion").toString().equals("E")) {
                    logger.warning("Codigo Error SAP: "+res.get("CodigoError").toString()+". Descripcion: "+res.get("MensajeError").toString()+". Request: "+json);
                    return null;
                }
                return res;
            }
        }catch (Exception ex) {
            System.out.println("exception "+ex.getMessage());
            return null;
        }
    }

    private String getAuth() throws UnsupportedEncodingException {
        return Base64.getEncoder().encodeToString(("PO_MCDES:Cr34BPS4H").getBytes("UTF-8"));
    }
}
