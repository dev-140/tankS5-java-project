package main;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.*;

public class WriteJson {
	@SuppressWarnings("unchecked")
    // write transaction data
	public static void setData(String roomData, String fName, String email, String address, String tel, String cName, String cNo, Long days, Long ref, Long total, String dateRange, String receiptDate, String roomNo) {
		// Set the path to the JSON file
        String filePath = "C:\\Users\\Jerome Pascual\\Desktop\\project_clone\\tankS5-java-project\\src\\jsonData\\transaction-data.json";
        

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
        newData.put("RDate", receiptDate);
        newData.put("dateRange", dateRange);
        newData.put("RoomNo", roomNo);
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

    // update ref json
    public static void addRef() {
        // Read in the JSON data from the file
        JSONParser parser = new JSONParser();
        try (FileReader reader = new FileReader("C:\\Users\\Jerome Pascual\\Desktop\\project_clone\\tankS5-java-project\\src\\jsonData\\ref.json")) {
            Object obj = parser.parse(reader);
            JSONArray jsonArray = (JSONArray) obj;
            JSONObject jsonObject = (JSONObject) jsonArray.get(0);

            // Get the current value of the "ref" field
            long ref = (long) jsonObject.get("ref");

            // Add 1 to the "ref" field
            jsonObject.put("ref", ref + 1);

            // Write the modified data back out to the file
            try (FileWriter writer = new FileWriter("C:\\Users\\Jerome Pascual\\Desktop\\project_clone\\tankS5-java-project\\src\\jsonData\\ref.json")) {
                writer.write(jsonArray.toJSONString());
            }

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    // update available room
    public static void updateAvailableRoomCount(String roomName) {
        String filePath = "C:\\Users\\Jerome Pascual\\Desktop\\project_clone\\tankS5-java-project\\src\\jsonData\\data.json";
        int roomsToDecrement = 1;
        
        JSONParser parser = new JSONParser();
        
        try (FileReader reader = new FileReader(filePath)) {
            // Parse the JSON file into a JSONArray
            JSONArray roomData = (JSONArray) parser.parse(reader);
            
            // Find the room with the matching roomName
            for (Object roomObj : roomData) {
                JSONObject room = (JSONObject) roomObj;
                String currRoomName = (String) room.get("room");
                
                if (roomName.equals(currRoomName)) {
                    // Update the availableRoom value
                    int currAvailableRooms = ((Number) room.get("availableRoom")).intValue();
                    int newAvailableRooms = currAvailableRooms - roomsToDecrement;
                    room.put("availableRoom", newAvailableRooms);
                    
                    // Write the updated JSON data back to the file
                    try (FileWriter writer = new FileWriter(filePath)) {
                        writer.write(roomData.toJSONString());
                        System.out.println("Successfully updated availableRoom for " + roomName + ".");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    
                    return;
                }
            }
            
            // If no matching room was found, print an error message
            System.out.println("Could not find room with name: " + roomName);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    public static void updateAvailableRoomCountAdd(String roomName) {
        String filePath = "C:\\Users\\Jerome Pascual\\Desktop\\project_clone\\tankS5-java-project\\src\\jsonData\\data.json";
        
        JSONParser parser = new JSONParser();
        
        try (FileReader reader = new FileReader(filePath)) {
            // Parse the JSON file into a JSONArray
            JSONArray roomData = (JSONArray) parser.parse(reader);
            
            // Find the room with the matching roomName
            for (Object roomObj : roomData) {
                JSONObject room = (JSONObject) roomObj;
                String currRoomName = (String) room.get("room");
                
                if (roomName.equals(currRoomName)) {
                    // Update the availableRoom value
                    int currAvailableRooms = ((Number) room.get("availableRoom")).intValue();
                    int newAvailableRooms = currAvailableRooms + 1;
                    room.put("availableRoom", newAvailableRooms);
                    
                    // Write the updated JSON data back to the file
                    try (FileWriter writer = new FileWriter(filePath)) {
                        writer.write(roomData.toJSONString());
                        System.out.println("Successfully updated availableRoom for " + roomName + ".");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    
                    return;
                }
            }
            
            // If no matching room was found, print an error message
            System.out.println("Could not find room with name: " + roomName);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    // delete transaction data
    public static void deleteTransaction(String filePath, int refNum) {
        JSONParser parser = new JSONParser();
        JSONArray transactionArray;
        try {
            // Read the JSON file and parse it into a JSONArray
            FileReader fileReader = new FileReader(filePath);
            Object obj = parser.parse(fileReader);
            transactionArray = (JSONArray) obj;
    
            // Find the object with the matching ref number and remove it from the array
            for (int i = 0; i < transactionArray.size(); i++) {
                JSONObject transaction = (JSONObject) transactionArray.get(i);
                long ref = (long) transaction.get("Ref");
                if (ref == refNum) {
                    transactionArray.remove(i);
                    break;
                }
            }
    
            // Write the updated JSONArray back to the file
            FileWriter fileWriter = new FileWriter(filePath);
            fileWriter.write(transactionArray.toJSONString());
            fileWriter.flush();
            fileWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void deleteRefData(int refNo) {
        deleteTransaction("C:\\Users\\Jerome Pascual\\Desktop\\project_clone\\tankS5-java-project\\src\\jsonData\\transaction-data.json", refNo);
    }
}