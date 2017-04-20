package prag.jacksonXml;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;

public abstract class AbstractNetAppFPolicyRequest {

    // Constants
    protected static final String REQUEST_SEPARATOR = "\n\n";
    protected static final String XML_HEADER = "<?xml version=\"1.0\"?>";

    public String constructResponseHeader(String respType, int responseLength) throws Exception {
        try {
            XmlMapper xmlMapper = new XmlMapper();
            // TODO (Pragad) : If NetApp is happy with the below format, get rid of XML_HEADER
            // xmlMapper.configure(ToXmlGenerator.Feature.WRITE_XML_DECLARATION, true);
            String respHeader = xmlMapper.writeValueAsString(new NetAppFPolicyHeaderSchema(respType, Integer.toString(responseLength)));
            System.out.println("Constructed header: {}"+ respHeader);
            return XML_HEADER + respHeader;
        } catch (Exception e) {
            System.out.println("Failed to construct header response for {}"+ respType);
            throw new Exception("Failed to construct header response for " + respType, e);
        }
    }

    public abstract String handleRequest() throws Exception;
}
