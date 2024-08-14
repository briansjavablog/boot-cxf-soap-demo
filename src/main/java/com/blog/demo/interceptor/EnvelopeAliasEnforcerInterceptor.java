package com.blog.demo.interceptor;

import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.binding.soap.interceptor.AbstractSoapInterceptor;
import org.apache.cxf.phase.Phase;

import javax.xml.namespace.QName;
import java.util.HashMap;
import java.util.Map;

/**
 * Interceptor to enforce legacy behavior on Namespace alias as demanded by CAPS.
 */
public class EnvelopeAliasEnforcerInterceptor extends AbstractSoapInterceptor {

    /**
     */
    public EnvelopeAliasEnforcerInterceptor() {
        super(Phase.PREPARE_SEND);
        System.out.println("com.blog.demo.interceptor.EnvelopeAliasEnforcerInterceptor created");
    }

    /**
     * If operation is of type dizOla reply with soapenv alias for the standard soap envelope, otherwise use soap alias
     * @param message message to be intercepted
     */
    public void handleMessage(SoapMessage message) {
        System.out.println("com.blog.demo.interceptor.EnvelopeAliasEnforcerInterceptor handleMessage");
        String nameSpaceAlias = getCustomAliasName(message);
        Map<String, String> hmap = new HashMap<>();
        hmap.put(nameSpaceAlias, "http://schemas.xmlsoap.org/soap/envelope/");
        message.put("soap.env.ns.map", hmap);
    }

    private String getCustomAliasName(SoapMessage message) {
        Object qname = message.get("jakarta.xml.ws.wsdl.operation");
        if(qname instanceof QName qnameCast && qnameCast.getLocalPart().equals("GetAccountDetails")) {
            return "soapenv";
        }
        return "soap";
    }
}