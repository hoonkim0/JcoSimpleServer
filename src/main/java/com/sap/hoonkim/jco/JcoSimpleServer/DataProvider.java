package com.sap.hoonkim.jco.JcoSimpleServer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import com.sap.conn.jco.ext.DataProviderException;
import com.sap.conn.jco.ext.DestinationDataEventListener;
import com.sap.conn.jco.ext.DestinationDataProvider;
import com.sap.conn.jco.ext.Environment;
import com.sap.conn.jco.ext.ServerDataEventListener;
import com.sap.conn.jco.ext.ServerDataProvider;

public class DataProvider implements DestinationDataProvider, ServerDataProvider {

    private Properties properties;
	final static String resourceFilePath = System.getProperty("user.home") + File.separator + "JCO.properties";
	
	String getProgId () {
		return properties.getProperty("jco.server.repository_destination");
	}
    DataProvider () {
    	FileInputStream propertiesInputStream;
		try {
			propertiesInputStream = new FileInputStream(resourceFilePath);
			properties = new Properties();
			properties.load(propertiesInputStream);
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
			System.exit(1);
		} catch (IOException e) {
			System.out.println(e.getMessage());
			System.exit(1);
		}
		
		Environment.registerDestinationDataProvider(this);
		Environment.registerServerDataProvider(this);
		
    }
	@Override
	public Properties getServerProperties(String arg0) throws DataProviderException {
		return properties;
	}

	@Override
	public void setServerDataEventListener(ServerDataEventListener arg0) {
	}

	@Override
	public Properties getDestinationProperties(String arg0) throws DataProviderException {
		return properties;
	}

	@Override
	public void setDestinationDataEventListener(DestinationDataEventListener arg0) {	
	}

	@Override
	public boolean supportsEvents() {
		return false;
	}
	

}
