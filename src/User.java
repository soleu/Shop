
public class User {
	String id;
	int cart[][] = new int[100][2];// 아이템 인덱스 저장
	int cartCount = 0;

	User() {
		id = "";
	}// initCart

	User(String id) {
		this.id = id;
	}

}
