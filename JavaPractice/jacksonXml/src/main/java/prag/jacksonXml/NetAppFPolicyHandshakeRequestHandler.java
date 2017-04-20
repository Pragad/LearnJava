package prag.jacksonXml;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;

import java.io.IOException;

import prag.jacksonXml.AbstractNetAppFPolicyRequest;

public class NetAppFPolicyHandshakeRequestHandler extends AbstractNetAppFPolicyRequest {

    // Constants
    public static final String NEGO_REQ = "NEGO_REQ";
    public static final String NEGO_RESP = "NEGO_RESP";

    // Class Variables
    public String request;
    public String response;

    public NetAppFPolicyHandshakeRequestHandler(String req) {
        this.request = req;
    }

    @Override
    public String handleRequest() throws Exception {
        processRequest();
        System.out.println("Generated response: {}"+ response);
        return response;
    }

    public void processRequest() throws Exception {
        String negotiateResp = constructHandshakeResponse();
        response = constructResponseHeader(NEGO_RESP, negotiateResp.length()) + REQUEST_SEPARATOR + negotiateResp;
    }

    public String constructHandshakeResponse() throws Exception {
        try {
            XmlMapper xmlMapperReq = new XmlMapper();
            NetAppFPolicyHandshakeSchema.HandshakeReq handshakeReq = xmlMapperReq.readValue(request, NetAppFPolicyHandshakeSchema.HandshakeReq.class);
            XmlMapper xmlMapperResp = new XmlMapper();
            // TODO (Pragad) : If NetApp is happy with the below format, get rid of XML_HEADER
            // xmlMapperResp.configure(ToXmlGenerator.Feature.WRITE_XML_DECLARATION, true);
            String respHandshake = xmlMapperResp.writeValueAsString(new NetAppFPolicyHandshakeSchema.HandshakeResp(handshakeReq.vsUUID,
                                   handshakeReq.policyName, handshakeReq.sessionId, handshakeReq.getLatestProtVersion()));
            System.out.println("Constructed handshake response: {}"+ respHandshake);
            return XML_HEADER + respHandshake;
        } catch (IOException e) {
            System.out.println("Failed  to construct handshake response"+ e);
            throw new Exception("Failed to construct handshake response", e);
        }
    }
}
