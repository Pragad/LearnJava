import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

class Credentials {

    @JacksonXmlProperty(isAttribute = true)
    public String priv;

    @JacksonXmlProperty(isAttribute = true)
    public String type;

    @JacksonXmlProperty(localName = "user")
    public String user;

    @JacksonXmlProperty(localName = "client_token")
    public String clientToken;

    @JacksonXmlProperty(localName = "client_secret")
    public String clientSecret;

    //getters, setters, toString
}

class Header {
    private String NotfType;
    private String ContentLen;
	private String DataFormat;
}

// Received: "�"<?xml version="1.0"?><Header><NotfType>NEGO_REQ</NotfType><ContentLen>279</ContentLen><DataFormat>XML</DataFormat></Header>
public class JacksonXml {
    public static void main (String[] args) {

        XmlMapper mapper = new XmlMapper();
        xmlMapper.readValue("<Header><NotfType>NEGO_REQ</NotfType><ContentLen>279</ContentLen><DataFormat>XML</DataFormat></Header>", Header.class);

        String XML1 = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><open><creds priv=\"write\" type=\"internal\"><user>Username1</user><client_token>abcplaudzrbcy37c</client_token><client_secret>0cxDE3LE0000=</client_secret></creds><creds priv=\"read\" type=\"internal\"><user>Username1</user><client_token>123plaudzrbcy37c</client_token><client_secret>0cxDE3LE1234=</client_secret></creds><creds priv=\"none\" type=\"internal\"><user>Username1</user><client_token>000plaudzrbcy37c</client_token><client_secret>0cxDE3LEabcd=</client_secret></creds></open>";
        XmlMapper mapper = new XmlMapper();
        OpenCredentials openCredentials = mapper.readValue(XML1, OpenCredentials.class);
        System.out.println(openCredentials.toString());
    }
}

