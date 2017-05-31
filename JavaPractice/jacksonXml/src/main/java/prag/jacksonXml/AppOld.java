package prag.jacksonXml;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.util.List;

import java.util.ArrayList;


/*
@JacksonXmlRootElement(localName = "Header")
class Header {
    public Header() {

    }

    Header(String notfType, String contentLen, String dataFormat) {
        this.notfType = notfType;
        this.contentLen = contentLen;
        this.dataFormat = dataFormat;
    }

    @JacksonXmlProperty(localName = "NotfType")
    private String notfType;

    @JacksonXmlProperty(localName = "ContentLen")
    private String contentLen;

    @JacksonXmlProperty(localName = "DataFormat")
    private String dataFormat;

    @Override
    public String toString() {
        return "NotfType: " + notfType + "; ContentLen: " + contentLen + "; DataFormat: " + dataFormat;
    }

    public String getnotfType() {
        return notfType;
    }

    public String getcontentLen() {
        return contentLen;
    }

    public String getdataFormat() {
        return dataFormat;
    }

    public void setnotfType(String notfType) {
        this.notfType = notfType;
    }

    public void setcontentLen(String contentLen) {
        this.contentLen = contentLen;
    }

    public void setdataFormat(String dataFormat) {
        this.dataFormat = dataFormat;
    }
}

class ProtocolVersion {
    @JacksonXmlProperty(localName = "Vers")
    private String vers;

    public String getvers() {
        return vers;
    }

    public void setvers(String vers) {
        this.vers = vers;
    }
}

@JacksonXmlRootElement(localName = "Handshake")
class Handshake {
    @JacksonXmlProperty(localName = "VsUUID")
    private String vsUUID;

    @JacksonXmlProperty(localName = "PolicyName")
    private String policyName;

    @JacksonXmlProperty(localName = "SessionId")
    private String sessionId;

    @JacksonXmlProperty(localName = "ProtVersion")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<ProtocolVersion> protVersion;

    public Handshake() {

    }

    public Handshake(String vsUUID, String policyName, String sessionId, List<ProtocolVersion> protVersion) {
        this.vsUUID = vsUUID;
        this.policyName = policyName;
        this.sessionId = sessionId;
        this.protVersion = protVersion;
    }

    public String getvsUUID() {
        return vsUUID;
    }

    public String getpolicyName() {
        return policyName;
    }

    public String getsessionId() {
        return sessionId;
    }

    public List<ProtocolVersion> getprotVersion() {
        return protVersion;
    }

    public String getLastProtVersion() {
        return protVersion.get(protVersion.size()-1).getvers();
    }

    public void setvsUUID(String vsUUID) {
        this.vsUUID = vsUUID;
    }

    public void setpolicyName(String policyName) {
        this.policyName = policyName;
    }

    public void setsessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public void setprotVersion(List<ProtocolVersion> protVersion) {
        this.protVersion = protVersion;
    }

    @Override
    public String toString() {
        return vsUUID + ", " + policyName + ", " + sessionId + ", " + protVersion ;
    }
}

@JacksonXmlRootElement(localName = "HandshakeResp")
class HandshakeResp {
    @JacksonXmlProperty(localName = "VsUUID")
    private String vsUUID;

    @JacksonXmlProperty(localName = "PolicyName")
    private String policyName;

    @JacksonXmlProperty(localName = "SessionId")
    private String sessionId;

    @JacksonXmlProperty(localName = "ProtVersion")
    private String protVersion;

    public HandshakeResp() {

    }

    public HandshakeResp(String vsUUID, String policyName, String sessionId, String protVersion) {
        this.vsUUID = vsUUID;
        this.policyName = policyName;
        this.sessionId = sessionId;
        this.protVersion = protVersion;
    }

    public String getvsUUID() {
        return vsUUID;
    }

    public String getpolicyName() {
        return policyName;
    }

    public String getsessionId() {
        return sessionId;
    }

    public String getprotVersion() {
        return protVersion;
    }

    public void setvsUUID(String vsUUID) {
        this.vsUUID = vsUUID;
    }

    public void setpolicyName(String policyName) {
        this.policyName = policyName;
    }

    public void setsessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public void setprotVersion(String protVersion) {
        this.protVersion = protVersion;
    }

    @Override
    public String toString() {
        return vsUUID + ", " + policyName + ", " + sessionId + ", " + protVersion ;
    }
}

public class AppOld {

    // Constants
    private static final int KDFPOLICY_CHANGE_NOTIFY_PORT = 54222;
    private static final int PREFIX_LEN = 6;
    private static final int ASCII_DOUBLE_QUOTE = 34;
    private static final String XML_DATA_FORMAT = "XML";
    private static final String SEPARATOR = "\n\n";
    private static final String XML_HEADER = "<?xml version=\"1.0\"?>";

    // Class Variables
    private String request;
    private String resp;
    private String reqType;
    private ServerSocket welcomeSocket = null;
    private Socket connectionSocket = null;
    private String inBuffStr = null;

    public AppOld() {
        try {
            startFPolicyListener();
        } catch (Exception e) {
            System.out.println("Failed to start FPolicy listener for change notify"+ e);
        }
    }

    public void startFPolicyListener() throws Exception {
        welcomeSocket = new ServerSocket(KDFPOLICY_CHANGE_NOTIFY_PORT);
        // Listen for requests from NetApp
        System.out.println("PRAGAD 0: Listening");
        while (true) {
            connectionSocket = welcomeSocket.accept();
            System.out.println("Socket Details: " + connectionSocket);
            // ReadBytes
            readBytes(connectionSocket);
            // TODO (Pragad): Check if this should be done on a separate thread
            handleRequests(null);
            // TODO (Pragad): Pass the request got from NetApp to backend
        }
    }

    public void readBytes(Socket connectionSocket) throws IOException {
        byte[] inBuffPrefix = new byte[PREFIX_LEN];
        connectionSocket.getInputStream().read(inBuffPrefix, 0, PREFIX_LEN);
        System.out.println("In Buff Prefix 1: " + inBuffPrefix.toString());
        if (inBuffPrefix[0] != ASCII_DOUBLE_QUOTE) {
            System.out.println("Not leading double quote");
        }

        // Get the first six bytes of data
        int numBytes1 = ByteBuffer.wrap(inBuffPrefix, 1, 4).order(ByteOrder.BIG_ENDIAN).getInt();
        System.out.println("Num Bytes1 : " + numBytes1);

        byte[] inBuffReq = new byte[numBytes1];
        connectionSocket.getInputStream().read(inBuffReq);

        inBuffStr = new String( inBuffReq, StandardCharsets.UTF_8);
        System.out.println("Input Req: " + inBuffStr);
    }

    public void handleRequests(InputStreamReader reqFromNetApp) throws IOException {
        parseRequest(reqFromNetApp);
        processRequest();
        buildResponse();
        sendResponse();
    }

    public void parseRequest(InputStreamReader reqFromNetApp) throws JsonParseException, JsonMappingException, IOException {
        //req = reqFromNetApp.readLine().substring(27);
        String line;
        List<String> reqLines = (Arrays.asList(inBuffStr.split("\n\n")));
        System.out.println("Req Lines: " + reqLines);

        String line1 = reqLines.get(0);
        String line2 = reqLines.get(1);
        request = line2;
        System.out.println("Input Req 1: " + reqLines.get(0));
        System.out.println("Input Req 2: " + line2);

        // Received: "�"<?xml version="1.0"?><Header><NotfType>NEGO_REQ</NotfType><ContentLen>279</ContentLen><DataFormat>XML</DataFormat></Header>
        //String XML1 = "<?xml version=\"1.0\"?><Header><NotfType>NEGO_REQ</NotfType><ContentLen>279</ContentLen><DataFormat>XML</DataFormat></Header>";
        XmlMapper xmlMapper = new XmlMapper();
        Header header = xmlMapper.readValue(line1, Header.class);
        reqType = header.getnotfType();
    }

    public void processRequest() throws IOException {
        switch (reqType) {
        case "NEGO_REQ":
            handleHandshakeRequest();
            break;
        default:
            break;
        }
    }

    public void handleHandshakeRequest() throws IOException {
        //System.out.println("PRAGAD G1. Handle Negotiate Request");
        String negResp = constructHandshakeResponse();
        resp = constructResponseHeader("NEGO_RESP", negResp.length()) + SEPARATOR + negResp;
        //System.out.println("PRAGAD G2. Resp: " + resp);
    }

    public String constructHandshakeResponse() throws IOException {
        XmlMapper xmlMapper0 = new XmlMapper();
        //System.out.println("PRAGAD . H0: " + request);
        Handshake handshake = xmlMapper0.readValue(request, Handshake.class);
        //System.out.println("PRAGAD . H1: " + handshake.toString());


        XmlMapper xmlMapper = new XmlMapper();
        String respHandshake = xmlMapper.writeValueAsString(new HandshakeResp(handshake.getvsUUID(), handshake.getpolicyName(), handshake.getsessionId(), handshake.getLastProtVersion()));
        //System.out.println("PRAGAD H2."+ respHandshake);

        return XML_HEADER + respHandshake;
    }

    public void handleSetAttributesRequest() {

    }

    public void handleDeleteDirectoryRequest() {

    }

    public void handleDeleteFileRequest() {

    }

    public void handleRenameDirectoryRequest() {

    }

    public void handleRenameFileRequest() {

    }

    public String constructResponseHeader(String respType, int responseLength) throws JsonProcessingException {
        XmlMapper xmlMapper = new XmlMapper();
        String respHeader = xmlMapper.writeValueAsString(new Header(respType, Integer.toString(responseLength), XML_DATA_FORMAT));
        //System.out.println("PRAGAD I1."+ respHeader);
        return XML_HEADER + respHeader;
    }

    public void buildResponse() {

    }

    public void sendResponse() throws IOException {
        @SuppressWarnings("resource")
        DataOutputStream outStream = new DataOutputStream(connectionSocket.getOutputStream());


        // Create final response with enclosing "Length" + response
        // Swap endianess for the length
        byte[] b = resp.getBytes(StandardCharsets.UTF_8);
        byte[] finalResp = new byte[PREFIX_LEN + b.length];
        finalResp[0] = finalResp[PREFIX_LEN - 1] = ASCII_DOUBLE_QUOTE;
        System.arraycopy(b, 0, finalResp, PREFIX_LEN, b.length);

        ByteBuffer bb = ByteBuffer.allocate(4);
        //bb.putInt(SwapEndianness(resp.length()));
        System.arraycopy(bb.array(), 0, finalResp, 1, 4);

        System.out.println("Response Len: " + resp.length());
        System.out.println("Response Len Big: " + SwapEndianness(resp.length()));
        System.out.println("Initial Response:"+ resp);
        System.out.println("Final Response:"+ new String(finalResp, StandardCharsets.UTF_8));
        //outStream.writeBytes(finalResp);
        outStream.write(finalResp, 0, finalResp.length);
        outStream.flush();
        outStream.close();
        System.out.println("Done");
    }

    public static int SwapEndianness(int num)
    {
        return ((num & 0x000000ff) << 24) + ((num & 0x0000ff00) << 8) + ((num & 0x00ff0000) >> 8) + ((num & 0xff000000) >> 24);
    }

    public static void main(String[] args) throws Exception {
        System.out.println("Starting main");
        new AppOld();
    }
}
*/

@JacksonXmlRootElement(localName = "PersonUpper")
class PersonUpper {
    @JacksonXmlProperty(localName = "Name")
    private String Name;

    @JacksonXmlProperty(localName = "Age")
    private String Age;

    @JacksonXmlProperty(localName = "City")
    private String City;

    public PersonUpper() { }

    PersonUpper(String Name, String Age, String City) {
        this.Name = Name;
        this.Age = Age;
        this.City = City;
    }

    @Override
    public String toString() {
        return "name: " + Name + "; age: " + Age + "; cities: " + City;
    }

    public String getName() {
        return Name;
    }

    public String getAge() {
        return Age;
    }

    public String getCity() {
        return City;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public void setAge(String Age) {
        this.Age = Age;
    }

    public void setCity(String City) {
        this.City = City;
    }
}

class City {
    @JacksonXmlProperty(localName = "cityName")
    String cityName;

    public City() { }

    public City(String cityName) {
        this.cityName = cityName;
    }

    public String getcityName() {
        return cityName;
    }

    public void setcity(String cityName) {
        this.cityName = cityName;
    }
}

@JacksonXmlRootElement(localName = "Person")
class Person {
    @JacksonXmlProperty(localName = "name")
    private String name;

    @JacksonXmlProperty(localName = "age")
    private String age;

    @JacksonXmlProperty(localName = "city")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<City> cities;

    public Person() { }

    Person(String name, String age, List<City> cities) {
        this.name = name;
        this.age = age;
        this.cities = cities;
    }

    @Override
    public String toString() {
        String cityStr = "";
        for (City c : cities) {
            cityStr = cityStr + c.cityName + ", ";
        }
        return "name: " + name + "; age: " + age + "; cities: " + cityStr;
    }

    public String getname() {
        return name;
    }

    public String getage() {
        return age;
    }

    public List<City> getcities() {
        return cities;
    }

    public void setname(String name) {
        this.name = name;
    }

    public void setage(String age) {
        this.age = age;
    }

    public void setcities(List<City> cities) {
        this.cities = cities;
    }
}

public class AppOld
{
    public static void main( String[] args )
    {
        try {
            System.out.println("Jackson XML");
            String XML1 = "<?xml version=\"1.0\"?><Person><name>NEGO_REQ</name><age>279</age><city><cityName>XML</cityName><cityName>JSON</cityName></city></Person>";
            XmlMapper xmlMapper = new XmlMapper();
            Person person = xmlMapper.readValue(XML1, Person.class);
            System.out.println(person.toString());

            XmlMapper xmlMapper2 = new XmlMapper();
            xmlMapper2.enable(SerializationFeature.INDENT_OUTPUT);
            Person p = new Person();
            City c1 = new City("sfo");
            City c2 = new City("sjc");
            City c3 = new City("sea");
            List<City> cityList = new ArrayList<>();
            cityList.add(c1);
            cityList.add(c2);
            cityList.add(c3);
            p.setname("setattr");
            p.setage("55");
            p.setcities(cityList);
            String respPerson = xmlMapper2.writeValueAsString(p);
            System.out.println(respPerson);

            XmlMapper xmlMapper3 = new XmlMapper();
            PersonUpper p2 = new PersonUpper();
            p2.setName("abc");
            p2.setAge("25");
            p2.setCity("den");
            String respPersonUpper = xmlMapper3.writeValueAsString(p2);
            System.out.println(respPersonUpper);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
