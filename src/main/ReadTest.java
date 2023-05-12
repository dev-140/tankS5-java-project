package main;

import java.io.FileReader;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class ReadTest {
    public static void main(String[] args) {
        String filePath = "/Users/jaynardvillarisco/eclipse-workspace/tankS5/src/jsonData/transaction-data.json";
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

                    System.out.println("Ref: " + ref);
                    System.out.println("Email: " + email);
                    System.out.println("Address: " + address);
                    System.out.println("Card name: " + cardName);
                    System.out.println("Days: " + days);
                    System.out.println("Total: " + total);
                    System.out.println("Room name: " + roomName);
                    System.out.println("Card No.: " + cardNo);
                    System.out.println("Full name: " + fullName);
                    System.out.println("Tel No.: " + telNo);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
