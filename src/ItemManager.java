import java.util.Scanner;
import java.util.Vector;

public class ItemManager {
	// 싱글톤 처리
	private ItemManager() {
	};

	private static ItemManager im = new ItemManager();

	public static ItemManager getIM() {
		return im;
	}

	Vector<Item[]> item;// 이중으로 선언
	Scanner scan = new Scanner(System.in);

	void init() {
		item = new Vector<>();
	}

	boolean check(String cate) {
		int cateCount = item.size();
//		System.out.println(cate);
		for (int i = 0; i < cateCount; i++) {
			if (cate.equals(item.get(i)[0].name)) {
				return true;
			}
		}
		return false;
	}

	void initItems(String name, int price, String cate) {
		int cateCount = item.size();
		boolean run = false;

		run = check(cate);
		if (run == false) {
			Item temp[] = new Item[1];
			for (int i = 0; i < temp.length; i++) {
				temp[i] = new Item();
			}
			temp[0].name = cate;
			item.add(temp);
		}
		cateCount = item.size();
//		System.out.println(cateCount);
		for (int i = 0; i < cateCount; i++) {
			if (cate.equals(item.get(i)[0].name)) {
				int itemCount = item.get(i).length;// 1
				Item[] temp = new Item[itemCount + 1];// 2
				for (int j = 0; j < temp.length; j++) {
					temp[j] = new Item();
				}
				for (int j = 0; j < itemCount; j++) {
					temp[j].name = item.get(i)[j].name;
					temp[j].price = item.get(i)[j].price;
				}
				temp[itemCount].name = name;
				temp[itemCount].price = price;
				item.set(i, temp);
			}
		}
	}

	void showCategory() {
		int cateCount = item.size();
		for (int i = 0; i < cateCount; i++) {
			System.out.println("[" + i + "] " + item.get(i)[0].name);
		}
	}

	void showCateItems(int cateNum) {
		int itemCount = item.get(cateNum).length;
		for (int i = 1; i < itemCount; i++) {
			System.out.println("[" + (i) + "] " + item.get(cateNum)[i].name);
		}
	}

	void addCate() {
		System.out.println("[메세지] 추가할 카테고리 명 : ");
		String name = scan.next();
		Item temp[] = new Item[1];
		temp[0] = new Item();
		temp[0].name = name;
		item.add(temp);
	}

	void addItem() {
		System.out.println("[추가] 카테고리 선택 : ");
		int cateNum = scan.nextInt();
		System.out.println("[추가] 추가할 아이템 명 : ");
		String itemName = scan.next();
		int itemCount = item.get(cateNum).length;
		Item temp[] = new Item[itemCount + 1];
		for (int i = 0; i < temp.length; i++) {
			temp[i] = new Item();
		}
		for (int j = 0; j < itemCount; j++) {
			temp[j].name = item.get(cateNum)[j].name;
			temp[j].price = item.get(cateNum)[j].price;
		}
		temp[itemCount].name = itemName;
		item.set(cateNum, temp);
	}

}
