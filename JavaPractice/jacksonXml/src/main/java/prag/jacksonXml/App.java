package prag.jacksonXml;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.io.IOException;

/**
 * Hello world!
 *
 */

@JacksonXmlRootElement(localName = "Person")
class Person {
    public Person() {

    }

    Person(String name, String age, String city) {
        this.name = name;
        this.age = age;
        this.city = city;
    }

    @JacksonXmlProperty(localName = "name")
    private String name;

    @JacksonXmlProperty(localName = "age")
    private String age;

    @JacksonXmlProperty(localName = "city")
    private String city;

    @Override
    public String toString() {
        return "name: " + name + "; age: " + age + "; city: " + city;
    }

    public String getname() {
        return name;
    }

    public String getage() {
        return age;
    }

    public String getcity() {
        return city;
    }

    public void setname(String name) {
        this.name = name;
    }

    public void setage(String age) {
        this.age = age;
    }

    public void setcity(String city) {
        this.city = city;
    }
}

public class App
{
    public static void main( String[] args )
    {
        try {
            System.out.println("Jackson XML");
            String XML1 = "<?xml version=\"1.0\"?><Person><name>NEGO_REQ</name><age>279</age><city>XML</city></Person>";
            XmlMapper xmlMapper = new XmlMapper();
            Person person = xmlMapper.readValue(XML1, Person.class);
            System.out.println(person.toString());

            XmlMapper xmlMapper2 = new XmlMapper();
            Person h = new Person("setattr","55","JSON");
            System.out.println(h.getname() + ", " + h.getage() + ", " + h.getcity());
            String respPerson = xmlMapper2.writeValueAsString(h);
            System.out.println("PRAGAD H1."+ respPerson);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
