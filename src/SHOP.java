import java.util.Scanner;

public class SHOP {
	Manager m = Manager.getInstance();
	Scanner scan = new Scanner(System.in);
	static int log = -1;

	SHOP() {
		m.im.init();
		String[] arr = { "새우깡", "고등어", "칸쵸", "소고기", "콜라", "새우" };
		int[] price = { 1000, 2000, 3600, 6500, 500, 1800 };
		String[] cate = { "과자", "생선", "과자", "육류", "음료수", "생선" };
		for (int i = 0; i < arr.length; i++) {
			m.initItem(arr[i], price[i], cate[i]);
		}
	}// init()

	void menu() {
		System.out.println("[1.가입] [2.탈퇴] [3.로그인] [4.로그아웃]");
		System.out.println("[100.관리자]  [0.종료]");
		System.out.println("입력 >>>");
	}

	void loginMenu() {
		System.out.println("[1.쇼핑] [2.장바구니 목록] [0.뒤로가기]");
		System.out.println("입력 >>>");
	}

	void manageMenu() {
		while (true) {
			System.out.println("[1.아이템 관리] [2.카테고리관리] [3.장바구니관리] [4.유저관리] [0.뒤로가기]");
			int choice = scan.nextInt();
			if (choice == 1) {
				manageItemMenu();
			} else if (choice == 2) {
				manageCateMenu();
			} else if (choice == 3) {
				manageCartMenu();
			} else if (choice == 4) {
				manageUserMenu();
			} else if (choice == 0) {
				break;
			}
		}
	}

	void manageItemMenu() {
		while (true) {
			System.out.println("[1. 전체 아이템] [2.아이템 추가] [3.아이템 삭제] [0.뒤로가기]");
			int choice = scan.nextInt();
			if (choice == 1) {
				m.showTotalItem();
			} else if (choice == 2) {
				m.addItem();
			} else if (choice == 3) {
				m.delItem();
			} else if (choice == 0) {
				break;
			}
		}
	}

	void manageCateMenu() {
		while (true) {
			System.out.println("[1. 전체 카테고리] [2.카테고리 추가] [3.카테고리 삭제] [0.뒤로가기]");
			int choice = scan.nextInt();
			if (choice == 1) {
				m.showTotalCate();
			} else if (choice == 2) {
				m.addCate();
			} else if (choice == 3) {
				m.delCate();
			} else if (choice == 0) {
				break;
			}
		}
	}

	void manageCartMenu() {
		while (true) {
			System.out.println("[1. 전체 장바구니 출력] [0.뒤로가기]");
			int choice = scan.nextInt();
			if (choice == 1) {
				m.showTotalCart();
			} else if (choice == 0) {
				break;
			}
		}
	}

	void manageUserMenu() {
		while (true) {
			System.out.println("[1. 전체 사용자] [2.사용자 추가] [3.사용자 삭제] [0.뒤로가기]");
			int choice = scan.nextInt();
			if (choice == 1) {
				m.showTotalUser();
			} else if (choice == 2) {
			} else if (choice == 3) {
			} else if (choice == 0) {
				break;
			}
		}
	}

	void run() {
		while (true) {
			menu();
			int choice = scan.nextInt();
			if (choice == 1) {
				m.signIn();
			} else if (choice == 2) {
				m.signOut();
			} else if (choice == 3) {
				m.logIn();
				if (log != -1) {
					while (true) {
						loginMenu();
						int choice2 = scan.nextInt();
						if (choice2 == 1) {
							m.shopping();
						} else if (choice2 == 2) {
//							m.showCartList(log);
							m.cartList(log);
						} else if (choice2 == 0) {
							break;
						}
					}
				}
			} else if (choice == 4) {
				m.logOut();
			} else if (choice == 100) {
				manageMenu();
			} else if (choice == 0) {
				break;
			}
		}
	}
}
