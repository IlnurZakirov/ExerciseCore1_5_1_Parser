import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.lang.reflect.Type;
import java.util.List;

public class ListToJsonWriter {

    public String listToJson(List<Employee> list) {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        Type listType = new TypeToken<List<Employee>>() {
        }.getType();
        return gson.toJson(list, listType);
    }

    public void writeString(String json, String newFileName) throws ParseException {
        JSONParser parser = new JSONParser();
        JSONArray obj = (JSONArray) parser.parse(json);
        try (BufferedWriter file = new BufferedWriter(new FileWriter(newFileName))) {
            file.write(obj.toJSONString());
            file.flush();
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }
}
