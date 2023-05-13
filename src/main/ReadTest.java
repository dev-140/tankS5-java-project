package main;

import java.io.FileReader;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class ReadTest {
    public static void main(String[] args) {
        String filePath = "C:\\Users\\Jerome Pascual\\Desktop\\project_clone\\tankS5-java-project\\src\\jsonData\\transaction-data.json";
        JSONParser parser = new JSONParser();

        try {
            JSONArray jsonArray = (JSONArray) parser.parse(new FileReader(filePath));

            for (Object obj : jsonArray) {
                JSONObject jsonObject = (JSONObject) obj;
                Long ref = (Long) jsonObject.get("Ref"); // Use Long instead of String
                if (ref == 1023) {
                    String email = (String) jsonObject.get("Email");
                    String address = (String) jsonObject.get("Address");
                    String cardName = (String) jsonObject.get("Card name");
                    Long days = (Long) jsonObject.get("Days"); // Use Long instead of String
                    Long total = (Long) jsonObject.get("Total"); // Use Long instead of String
                    String roomName = (String) jsonObject.get("Room name");
                    String cardNo = (String) jsonObject.get("Card No.");
                    String fullName = (String) jsonObject.get("Full name");
                    String telNo = (String) jsonObject.get("Tel No.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
