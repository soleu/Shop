import java.util.Scanner;
import java.util.Vector;

public class Manager {
	Scanner scan = new Scanner(System.in);
	Vector<User> user = new Vector<>();
	ItemManager im = ItemManager.getIM();

	private Manager() {
	}// Ŭ���� �����̺� ó��.new�� ���� ������ �� ����

	private static Manager instance = new Manager();// ������ Ŭ������ �ʱ�ȭ�ؼ� ���� �������� ����

	public static Manager getInstance() {
		return instance;
	}// �����ϰ� ������ �͸� �̿��� �� �ֵ�����

	void initItem(String name, int price, String cate) {
		// �������̸�, ����, ī�װ�
		im.initItems(name, price, cate);
	}// ī�װ� �� ������ �ʱ�ȭ

	int check(String id) {
		int userCount = user.size();
		int idx = -1;
		for (int i = 0; i < userCount; i++) {
			if (id.equals(user.get(i).id))
				idx = i;
		}
		return idx;
	}

	void logIn() {
		System.out.println("[�α���] id�� �Է��ϼ���");
		String id = scan.next();
		int idx = check(id);
		if (idx != -1) {
			SHOP.log = idx;
			System.out.println("[�޼���] " + user.get(SHOP.log).id + "��, �α���.");
		} // �����帧
		else {
			System.out.println("�ش��ϴ� ���̵� �����ϴ�.");
			return;
		}
		// �����帧
	}

	void logOut() {
		if (SHOP.log == -1) {
			System.out.println("[�޼���] �α����� �������ּ���");
			return;
		}
		SHOP.log = -1;
		System.out.println("[�޼���] �α׾ƿ� �Ǿ����ϴ�.");
	}

	void signIn() {
		System.out.println("[ȸ������] ���̵� �Է����ּ���");
		String id = scan.next();
		int idx = check(id);
		if (idx == -1) {
			user.add(new User(id));
			System.out.println("[�޼���] " + id + "�� ������ �����մϴ�.");
		}
	}

	void signOut() {
		if (SHOP.log == -1) {
			System.out.println("[�޼���] �α����� �������ּ���");
			return;
		}
		user.remove(SHOP.log);
		SHOP.log = -1;
		System.out.println("[�޼���] Ż��Ǿ����ϴ�.");
	}

	void showTotalCart() {
		int userCount = user.size();
		for (int i = 0; i < userCount; i++) {
			showCartList(i);
		}
	}

	void showTotalUser() {
		int userCount = user.size();
		for (int i = 0; i < userCount; i++) {
			System.out.println(user.get(i).id);
		}
	}

	void showTotalCate() {
		im.showCategory();
	}

	void showTotalItem() {
		int cateCount = im.item.size();
		int k = 0;
		for (int i = 0; i < cateCount; i++) {
			int itemCount = im.item.get(i).length;
			for (int j = 1; j < itemCount; j++) {
				System.out.println("[" + k + "][" + im.item.get(i)[j].name + "][" + im.item.get(i)[j].price + "]["
						+ im.item.get(i)[0].name + "]");
				k++;
			}
		}
	}

	void showCartList(int logNum) {
		User loginUser = new User();
		loginUser = user.get(logNum);
		System.out.println("[" + loginUser.id + "]");
		for (int i = 0; i < loginUser.cartCount; i++) {
			int cateNum = loginUser.cart[i][0];
			int itemNum = loginUser.cart[i][1];
			System.out.println("[" + i + "]" + im.item.get(cateNum)[itemNum].name);
		}
	}

	void shopping() {
		while (true) {
			im.showCategory();
			System.out.println("[ī�װ�]��ȣ�� �Է��ϼ���.[���� : -1]");
			int choice = scan.nextInt();
			if (choice == -1) {
				break;
			}
			while (true) {
				im.showCateItems(choice);
				System.out.println("[������]��ȣ�� �Է��ϼ���.[���� : -1]");
				int choice2 = scan.nextInt();
				if (choice2 == -1) {
					break;
				}
				addToCart(choice, choice2);
			}
		}
	}

	void addToCart(int num, int num2) {// num=cateNum,num2=itemNum
		int cartCount = user.get(SHOP.log).cartCount;
		user.get(SHOP.log).cart[cartCount][0] = num;
		user.get(SHOP.log).cart[cartCount][1] = num2;
		user.get(SHOP.log).cartCount++;
	}

	void delCartList(int log) {
		showCartList(log);
		System.out.println("������ ��ȣ ���� : ");
		int num = scan.nextInt();
		int cartCount = user.get(log).cartCount;
		for (int i = num; i < cartCount - 1; i++) {
			user.get(log).cart[i][0] = user.get(log).cart[i + 1][0];
			user.get(log).cart[i][1] = user.get(log).cart[i + 1][1];
		}
		user.get(log).cartCount--;
	}

	void buyCartList(int log) {
		System.out.println("[�޼���] ��ٱ����� ��� ǰ���� �����մϴ�.");
		int totalMoney = 0;
		for (int i = 0; i < user.get(log).cartCount; i++) {
			int cateNum = user.get(log).cart[i][0];
			int itemNum = user.get(log).cart[i][1];
			totalMoney += im.item.get(cateNum)[itemNum].price;
		}
		System.out.println("�� �ݾ��� " + totalMoney + "�Դϴ�.");
		System.out.println("[�޼���] ��ٱ��ϸ� �ʱ�ȭ �մϴ�.");
		user.get(log).cart = new int[100][2];
		user.get(log).cartCount = 0;
	}

	void cartList(int log) {
		while (true) {
			System.out.println("[1.�� ��ٱ���] [2.����] [3.����] [0.�ڷΰ���]");
			int choice = scan.nextInt();
			if (choice == 1) {
				showCartList(log);
			} else if (choice == 2) {
				delCartList(log);
			} else if (choice == 3) {
				buyCartList(log);
			} else if (choice == 0) {
				break;
			}
		}
	}

	void addCate() {
		im.addCate();
	}

	void delCate() {
		showTotalCate();
		System.out.println("[�޼���] ������ ī�װ� ��ȣ : ");
		int cateNum = scan.nextInt();
		int userCount = user.size();
		for (int i = 0; i < userCount; i++) {
			int cartCount = user.get(i).cartCount;
			for (int j = 0; j < cartCount; j++) {
				if (user.get(i).cart[j][0] == cateNum) {
					System.out.println("[�޼���] ������ ��ٱ��Ͽ� �ش� ī�װ��� ����־�, ������ �� �����ϴ�.");
					return;
				}
			}
		}
		im.item.remove(cateNum);
		System.out.println("[�޼���] �ش� ī�װ��� �����߽��ϴ�.");
	}

	void addItem() {
		showTotalCate();
		im.addItem();
	}

	void delItem() {
		showTotalCate();
		System.out.println("[����] ī�װ� ��ȣ : ");
		int cateNum = scan.nextInt();
		im.showCateItems(cateNum);
		System.out.println("[����] ������ ������ ��ȣ : ");
		int itemNum = scan.nextInt();
		int itemCount = im.item.get(cateNum).length;
		int userCount = user.size();
		for (int i = 0; i < userCount; i++) {
			int cartCount = user.get(i).cartCount;
			for (int j = 0; j < cartCount; j++) {
				if (user.get(i).cart[cateNum][j] == itemNum) {
					System.out.println("[�޼���] ������ ��ٱ��Ͽ� �ش� �������� ����־�, ������ �� �����ϴ�.");
					return;
				}
			}
		}
		Item temp[] = new Item[im.item.get(cateNum).length - 1];
		int j = 0;
		for (int i = 0; i < temp.length; i++) {
			temp[i] = new Item();
		}
		System.out.println(itemCount);
		for (int i = 0; i < itemCount; i++) {// 0,1,2
			if (i != itemNum) {// 1
				temp[j].name = im.item.get(cateNum)[i].name;
				temp[j].price = im.item.get(cateNum)[i].price;
				j++;
			}
		}
		im.item.set(cateNum, temp);
	}
}
