package main;

public class ReadTest {
	
	public static void setData(String data) {
		ReadJson.fetchData(data);
		System.out.println("room name is: " + ReadJson.roomFName);
		System.out.println("room price: " + ReadJson.roomSPrice);
		System.out.println("Desc: " + ReadJson.roomDesc);
		System.out.println("Images: ");
		System.out.println("Icon: " + ReadJson.roomIconImg);
		System.out.println("Hero: " + ReadJson.roomHeroIcon);
		System.out.println("Other images: " + ReadJson.roomOtherImageInfo);
	}

	public static void main(String[] args) {
		setData("tripleRoom");
	}

}
