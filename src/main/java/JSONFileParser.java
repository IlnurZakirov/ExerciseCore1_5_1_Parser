import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JSONFileParser {
    private final List<Employee> STAFF = new ArrayList<>();

    public String readString(String fileName) {
        StringBuilder stringBuilder = new StringBuilder();
        String line = null;
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (
                IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    public List<Employee> jsonToList(String json) {
        JSONArray obj = null;
        try {
            JSONParser parser = new JSONParser();
            obj = (JSONArray) parser.parse(json);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        assert obj != null;
        for (Object element : obj) {
            Employee employee = gson.fromJson(element.toString(), Employee.class);
            STAFF.add(employee);
        }
        return STAFF;
    }
}
