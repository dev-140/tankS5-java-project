package main;

public class Functions {
	public static void checkoutFunction(String roomType) {
		ReadJson.fetchData(roomType);
		WriteJson.setData(ReadJson.roomFName, ReadJson.roomSPrice, ReadJson.roomDesc, ReadJson.roomIconImg);
	}
}
