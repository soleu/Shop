
public class Item {
	String name;
	int price;

	Item() {
		name = "";
		price = 0;
	}

	Item(String cate) {
		name = cate;
	}

	Item(Item cate) {
		name = cate.name;
	}

	Item(String name, int price) {
		this.name = name;
		this.price = price;
	}

}
