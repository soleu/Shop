import java.util.Scanner;
import java.util.Vector;

public class Manager {
	Scanner scan = new Scanner(System.in);
	Vector<User> user = new Vector<>();
	ItemManager im = ItemManager.getIM();

	private Manager() {
	}// 클래스 프라이빗 처리.new를 통해 생성할 수 없음

	private static Manager instance = new Manager();// 단일한 클래스를 초기화해서 갖는 정적변수 선언

	public static Manager getInstance() {
		return instance;
	}// 단일하게 생성된 것만 이용할 수 있도록함

	void initItem(String name, int price, String cate) {
		// 아이템이름, 가격, 카테고리
		im.initItems(name, price, cate);
	}// 카테고리 및 아이템 초기화

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
		System.out.println("[로그인] id를 입력하세요");
		String id = scan.next();
		int idx = check(id);
		if (idx != -1) {
			SHOP.log = idx;
			System.out.println("[메세지] " + user.get(SHOP.log).id + "님, 로그인.");
		} // 정상흐름
		else {
			System.out.println("해당하는 아이디가 없습니다.");
			return;
		}
		// 예외흐름
	}

	void logOut() {
		if (SHOP.log == -1) {
			System.out.println("[메세지] 로그인을 먼저해주세요");
			return;
		}
		SHOP.log = -1;
		System.out.println("[메세지] 로그아웃 되었습니다.");
	}

	void signIn() {
		System.out.println("[회원가입] 아이디를 입력해주세요");
		String id = scan.next();
		int idx = check(id);
		if (idx == -1) {
			user.add(new User(id));
			System.out.println("[메세지] " + id + "님 가입을 축하합니다.");
		}
	}

	void signOut() {
		if (SHOP.log == -1) {
			System.out.println("[메세지] 로그인을 먼저해주세요");
			return;
		}
		user.remove(SHOP.log);
		SHOP.log = -1;
		System.out.println("[메세지] 탈퇴되었습니다.");
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
			System.out.println("[카테고리]번호를 입력하세요.[종료 : -1]");
			int choice = scan.nextInt();
			if (choice == -1) {
				break;
			}
			while (true) {
				im.showCateItems(choice);
				System.out.println("[아이템]번호를 입력하세요.[종료 : -1]");
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
		System.out.println("삭제할 번호 선택 : ");
		int num = scan.nextInt();
		int cartCount = user.get(log).cartCount;
		for (int i = num; i < cartCount - 1; i++) {
			user.get(log).cart[i][0] = user.get(log).cart[i + 1][0];
			user.get(log).cart[i][1] = user.get(log).cart[i + 1][1];
		}
		user.get(log).cartCount--;
	}

	void buyCartList(int log) {
		System.out.println("[메세지] 장바구니의 모든 품목을 구매합니다.");
		int totalMoney = 0;
		for (int i = 0; i < user.get(log).cartCount; i++) {
			int cateNum = user.get(log).cart[i][0];
			int itemNum = user.get(log).cart[i][1];
			totalMoney += im.item.get(cateNum)[itemNum].price;
		}
		System.out.println("총 금액은 " + totalMoney + "입니다.");
		System.out.println("[메세지] 장바구니를 초기화 합니다.");
		user.get(log).cart = new int[100][2];
		user.get(log).cartCount = 0;
	}

	void cartList(int log) {
		while (true) {
			System.out.println("[1.내 장바구니] [2.삭제] [3.구입] [0.뒤로가기]");
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
		System.out.println("[메세지] 삭제할 카테고리 번호 : ");
		int cateNum = scan.nextInt();
		int userCount = user.size();
		for (int i = 0; i < userCount; i++) {
			int cartCount = user.get(i).cartCount;
			for (int j = 0; j < cartCount; j++) {
				if (user.get(i).cart[j][0] == cateNum) {
					System.out.println("[메세지] 고객님의 장바구니에 해당 카테고리가 담겨있어, 삭제할 수 없습니다.");
					return;
				}
			}
		}
		im.item.remove(cateNum);
		System.out.println("[메세지] 해당 카테고리를 삭제했습니다.");
	}

	void addItem() {
		showTotalCate();
		im.addItem();
	}

	void delItem() {
		showTotalCate();
		System.out.println("[삭제] 카테고리 번호 : ");
		int cateNum = scan.nextInt();
		im.showCateItems(cateNum);
		System.out.println("[삭제] 삭제할 아이템 번호 : ");
		int itemNum = scan.nextInt();
		int itemCount = im.item.get(cateNum).length;
		int userCount = user.size();
		for (int i = 0; i < userCount; i++) {
			int cartCount = user.get(i).cartCount;
			for (int j = 0; j < cartCount; j++) {
				if (user.get(i).cart[cateNum][j] == itemNum) {
					System.out.println("[메세지] 고객님의 장바구니에 해당 아이템이 담겨있어, 삭제할 수 없습니다.");
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
