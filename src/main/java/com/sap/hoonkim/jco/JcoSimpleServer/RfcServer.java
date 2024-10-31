package com.sap.hoonkim.jco.JcoSimpleServer;

import com.sap.conn.jco.JCoException;
import com.sap.conn.jco.server.DefaultServerHandlerFactory;
import com.sap.conn.jco.server.JCoServer;
import com.sap.conn.jco.server.JCoServerContextInfo;
import com.sap.conn.jco.server.JCoServerErrorListener;
import com.sap.conn.jco.server.JCoServerExceptionListener;
import com.sap.conn.jco.server.JCoServerFactory;
import com.sap.conn.jco.server.JCoServerFunctionHandler;
import com.sap.conn.jco.server.JCoServerState;
import com.sap.conn.jco.server.JCoServerStateChangedListener;

public class RfcServer implements JCoServerErrorListener, JCoServerExceptionListener, JCoServerStateChangedListener {

	private JCoServer server;
	DataProvider dp;
	
	RfcServer () {
		dp = new DataProvider();
	}
	@Override
	public void serverStateChangeOccurred(JCoServer svr, JCoServerState state1, JCoServerState state2) {
		System.out.println ("Server State Changed : " + state1.toString() + " --> " + state2.toString());
	}

	@Override
	public void serverExceptionOccurred(JCoServer svr, String arg1, JCoServerContextInfo ctxInfo, Exception exception) {
		System.out.println (exception.toString());
	}

	@Override
	public void serverErrorOccurred(JCoServer svr, String arg1, JCoServerContextInfo ctxInfo, Error err) {
		System.out.println (err.toString());
	}
	
	public void startService() {
		
		try {
			server = JCoServerFactory.getServer(dp.getProgId());
		} catch(JCoException e) {
			System.out.println(e.getMessage());
			System.exit(1);
		}

        server.addServerErrorListener(this);
        server.addServerExceptionListener(this);
        server.addServerStateChangedListener(this);

        DefaultServerHandlerFactory.FunctionHandlerFactory factory = new DefaultServerHandlerFactory.FunctionHandlerFactory();
        
        JCoServerFunctionHandler callHandler = new CallHandler();
        factory.registerHandler(CallHandler.FUNCTION_NAME1, callHandler);
        factory.registerHandler(CallHandler.FUNCTION_NAME2, callHandler);
        
        server.setCallHandlerFactory(factory);
        
        server.start();
	}
	
	
}