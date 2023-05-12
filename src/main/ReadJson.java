package main;
import java.io.FileReader;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ReadJson {
//	global vars
	static String roomFName, roomSPrice, roomIconImg, roomDesc, roomHeroIcon, roomOtherImageInfo, dummyheroIcon, feature1, feature2, feature3;
	static long roomPrice, refNo;

    public static void fetchData(String roomData) {
        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader("C:\\Users\\Jerome Pascual\\Desktop\\project_clone\\tankS5-java-project\\src\\jsonData\\data.json")) {
            Object obj = jsonParser.parse(reader);
            JSONArray roomList = (JSONArray) obj;

            for (int i = 0; i < roomList.size(); i++) {
                JSONObject room = (JSONObject) roomList.get(i);
                String roomName = (String) room.get("room");
                
                if (roomName.equals(roomData)) {
					JSONArray features = (JSONArray) room.get("feature");
					JSONObject images = (JSONObject) room.get("images");
					
					roomFName = (String) room.get("roomName");
					roomPrice = (long) room.get("price");
					roomSPrice = (String) room.get("priceS");
					roomIconImg = (String) images.get("smallImageIcon");
					roomHeroIcon = (String) images.get("heroIcon");
					roomOtherImageInfo = (String) images.get("otherImageInfo");
					dummyheroIcon = (String) images.get("dummyheroIcon");
					roomDesc = (String) room.get("desc");
					feature1 = (String) features.get(0);
					feature2 = (String) features.get(1);
					feature3 = (String) features.get(2);
                }
            }

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    public static void fetchRef() {

        // Specify the path to your JSON file
        String filePath = "C:\\Users\\Jerome Pascual\\Desktop\\project_clone\\tankS5-java-project\\src\\jsonData\\ref.json";

        // Create a JSON parser
        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader(filePath)) {

            // Parse the JSON data from the file
            Object obj = jsonParser.parse(reader);

            // Cast the parsed object to a JSON array
            JSONArray jsonArray = (JSONArray) obj;

            // Get the JSON object at the first (and only) index
            JSONObject jsonObject = (JSONObject) jsonArray.get(0);

            // Extract the value of the "ref" key as an integer
            int ref = ((Long) jsonObject.get("ref")).intValue();

            // Print the value of the "ref" key

            refNo = ref;

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }
}
