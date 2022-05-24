import org.json.simple.parser.ParseException;

import java.util.List;

public class Main {
    public static void main(String[] args) throws ParseException {
        String[] columnMapping = {"id", "firstName", "lastName", "country", "age"};
        String fileCSVName = "data.csv";
        String fileXMLName = "data.xml";
        String newFileName = "data.json";
        String newFileName1 = "data1.json";

        ListToJsonWriter listToJsonWriter = new ListToJsonWriter();

        CSVParser csvParser = new CSVParser();
        List<Employee> list = csvParser.parseCSV(columnMapping, fileCSVName);
        String json = listToJsonWriter.listToJson(list);
        listToJsonWriter.writeString(json, newFileName);

        XMLParser xmlParser = new XMLParser();
        List<Employee> list1 = xmlParser.parseXML(fileXMLName);
        String json1 = listToJsonWriter.listToJson(list1);
        listToJsonWriter.writeString(json1, newFileName1);

        JSONFileParser jsonFileParser = new JSONFileParser();
        String json2 = jsonFileParser.readString(newFileName);
        List<Employee> list2 = jsonFileParser.jsonToList(json2);
        list2.forEach(System.out::println);

    }
}