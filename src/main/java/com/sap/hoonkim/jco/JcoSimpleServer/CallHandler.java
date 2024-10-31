package com.sap.hoonkim.jco.JcoSimpleServer;

import com.sap.conn.jco.AbapClassException;
import com.sap.conn.jco.AbapException;
import com.sap.conn.jco.JCoFunction;
import com.sap.conn.jco.server.JCoServerContext;
import com.sap.conn.jco.server.JCoServerFunctionHandler;

public class CallHandler implements JCoServerFunctionHandler {

	public static final String FUNCTION_NAME1 = "Z_JCO_CALL1";
	public static final String FUNCTION_NAME2 = "Z_JCO_CALL2";
	
	public void handleRequest(JCoServerContext ctx, JCoFunction function) throws AbapException, AbapClassException {

		if (function.getName().equals(FUNCTION_NAME1)) {
			String param = function.getImportParameterList().getString("PARAM");
			function.getExportParameterList().setValue("RTN", "Hello " + param);
		} else if (function.getName().equals(FUNCTION_NAME2)) {
			String param = function.getImportParameterList().getString("PARAM");
			function.getExportParameterList().setValue("RTN", "Good Bye " + param);
		}
	
	}

}
