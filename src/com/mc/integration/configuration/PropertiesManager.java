package com.mc.integration.configuration;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesManager {
    final String sapUrlBp;
    final String sapUrlCs;
    final String sapUrlNov;
    final String sapHttpsUrl;

    public String getSapUrlBp() {
        return sapUrlBp;
    }

    public String getSapUrlCs() {
        return sapUrlCs;
    }

    public String getSapUrlNov() {
        return sapUrlNov;
    }

    public String getGenesisDbUrl() {
        return genesisDbUrl;
    }

    public String getGenesisDbUsername() {
        return genesisDbUsername;
    }

    public String getGenesisDbPassword() {
        return genesisDbPassword;
    }

    final String genesisDbUrl;
    final String genesisDbUsername;
    final String genesisDbPassword;

    public String getSapHttpsUrl() {
        return sapHttpsUrl;
    }

    public PropertiesManager() throws IOException {
        Properties prop = load();
        sapUrlBp = prop.getProperty("sap.bp.url");
        sapUrlCs = prop.getProperty("sap.cs.url");
        sapUrlNov = prop.getProperty("sap.nov.url");
        genesisDbUrl = prop.getProperty("genesis.db.url");
        genesisDbUsername = prop.getProperty("genesis.db.username");
        genesisDbPassword = prop.getProperty("genesis.db.password");
        sapHttpsUrl = prop.getProperty("sap.https.url");
    }

    private Properties load() throws IOException {
        Properties properties = new Properties();
        File f = new File("application.properties");
        if (f.exists() && f.canRead()) {
            try(FileInputStream ins = new FileInputStream(f)) {
                properties.load(ins);
            }
        }
        File f2 = new File("external.properties");
        if (f2.exists() && f2.canRead()) {
            try(FileInputStream ins2 = new FileInputStream(f)) {
                properties.load(ins2);
            }
        }
        return properties;
    }
}
