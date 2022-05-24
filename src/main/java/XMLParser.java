import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.util.ArrayList;
import java.util.List;

public class XMLParser {
    private final List<Employee> STAFF = new ArrayList<>();
    private final String TAG_ID = "id";
    private final String TAG_FIRST_NAME = "firstName";
    private final String TAG_LAST_NAME = "lastName";
    private final String TAG_COUNTRY = "country";
    private final String TAG_AGE = "age";
    private long id;
    private String firstName;
    private String lastName;
    private String country;
    private int age;


    public List<Employee> parseXML(String fileXMLName) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        Document doc = null;
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            doc = builder.parse(fileXMLName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assert doc != null;
        Node root = doc.getDocumentElement();
        NodeList nodeList = root.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (Node.ELEMENT_NODE != node.getNodeType()) continue;
            NodeList childNodeList = node.getChildNodes();
            for (int j = 0; j < childNodeList.getLength(); j++) {
                Node childNode = childNodeList.item(j);
                if (Node.ELEMENT_NODE != childNode.getNodeType()) continue;
                switch (childNode.getNodeName()) {
                    case TAG_ID: {
                        id = Long.parseLong(childNode.getTextContent());
                        break;
                    }
                    case TAG_FIRST_NAME: {
                        firstName = childNode.getTextContent();
                        break;
                    }
                    case TAG_LAST_NAME: {
                        lastName = childNode.getTextContent();
                        break;
                    }
                    case TAG_COUNTRY: {
                        country = childNode.getTextContent();
                        break;
                    }
                    case TAG_AGE: {
                        age = Integer.parseInt(childNode.getTextContent());
                        break;
                    }
                    default:
                        throw new IllegalStateException("Unexpected value: " + childNode.getNodeName());
                }
            }
            Employee employee = new Employee(id, firstName, lastName, country, age);
            STAFF.add(employee);
        }
        return STAFF;
    }
}
