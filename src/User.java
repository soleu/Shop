
public class User {
	String id;
	int cart[][] = new int[100][2];// ������ �ε��� ����
	int cartCount = 0;

	User() {
		id = "";
	}// initCart

	User(String id) {
		this.id = id;
	}

}
