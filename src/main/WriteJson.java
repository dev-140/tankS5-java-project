package main;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.*;

public class WriteJson {
	@SuppressWarnings("unchecked")
	public static void setData(String roomData, String fName, String email, String address, String tel, String cName, String cNo, Long days, Long ref, Long total) {
		// Set the path to the JSON file
        String filePath = "/Users/jaynardvillarisco/eclipse-workspace/tankS5/src/jsonData/transaction-data.json";
        

        // Read the existing JSON data from the file
        JSONArray finalData = readJsonData(filePath);

        // Add a new object to the JSON array
        JSONObject newData = new JSONObject();
        newData.put("Room name", roomData);
        newData.put("Full name", fName);
        newData.put("Email", email);
        newData.put("Address", address);
        newData.put("Tel No.", tel);
        newData.put("Card name", cName);
        newData.put("Card No.", cNo);
        newData.put("Days", days);
        newData.put("Ref", ref);
        newData.put("Total", total);
        finalData.add(newData);

        // Write the updated JSON data back to the file
        writeJsonData(filePath, finalData);
    }

    private static JSONArray readJsonData(String filePath) {
        JSONParser parser = new JSONParser();
        JSONArray data = new JSONArray();

        try {
            FileReader fileReader = new FileReader(filePath);
            data = (JSONArray) parser.parse(fileReader);
            fileReader.close();
        } catch (IOException | ParseException e) {
            // File does not exist or cannot be parsed
        }

        return data;
    }

    private static void writeJsonData(String filePath, JSONArray data) {
        try {
            FileWriter fileWriter = new FileWriter(filePath);
            fileWriter.write(data.toJSONString());
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            // File cannot be written to
        }
	}

    public static void addRef() {

        // Read in the JSON data from the file
        JSONParser parser = new JSONParser();
        try (FileReader reader = new FileReader("/Users/jaynardvillarisco/eclipse-workspace/tankS5/src/jsonData/ref.json")) {
            Object obj = parser.parse(reader);
            JSONArray jsonArray = (JSONArray) obj;
            JSONObject jsonObject = (JSONObject) jsonArray.get(0);

            // Get the current value of the "ref" field
            long ref = (long) jsonObject.get("ref");

            // Add 1 to the "ref" field
            jsonObject.put("ref", ref + 1);

            // Write the modified data back out to the file
            try (FileWriter writer = new FileWriter("/Users/jaynardvillarisco/eclipse-workspace/tankS5/src/jsonData/ref.json")) {
                writer.write(jsonArray.toJSONString());
            }

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }
}