package prag.jacksonXml;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "Header")
public class NetAppFPolicyHeaderSchema {

    protected static final String XML_DATA_FORMAT = "XML";

    public NetAppFPolicyHeaderSchema() { }

    /* package */ NetAppFPolicyHeaderSchema(String notfType, String contentLen) {
        this.notfType = notfType;
        this.contentLen = contentLen;
        this.dataFormat = XML_DATA_FORMAT;
    }

    @JacksonXmlProperty(localName = "NotfType")
    public String notfType;

    @JacksonXmlProperty(localName = "ContentLen")
    public String contentLen;

    @JacksonXmlProperty(localName = "DataFormat")
    public String dataFormat;

    @Override
    public String toString() {
        return new StringBuilder().append(notfType).append(", ").append(contentLen).append(", ").append(dataFormat).toString();
    }
}
