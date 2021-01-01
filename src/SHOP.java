import java.util.Scanner;

public class SHOP {
	Manager m = Manager.getInstance();
	Scanner scan = new Scanner(System.in);
	static int log = -1;

	SHOP() {
		m.im.init();
		String[] arr = { "�����", "����", "ĭ��", "�Ұ��", "�ݶ�", "����" };
		int[] price = { 1000, 2000, 3600, 6500, 500, 1800 };
		String[] cate = { "����", "����", "����", "����", "�����", "����" };
		for (int i = 0; i < arr.length; i++) {
			m.initItem(arr[i], price[i], cate[i]);
		}
	}// init()

	void menu() {
		System.out.println("[1.����] [2.Ż��] [3.�α���] [4.�α׾ƿ�]");
		System.out.println("[100.������]  [0.����]");
		System.out.println("�Է� >>>");
	}

	void loginMenu() {
		System.out.println("[1.����] [2.��ٱ��� ���] [0.�ڷΰ���]");
		System.out.println("�Է� >>>");
	}

	void manageMenu() {
		while (true) {
			System.out.println("[1.������ ����] [2.ī�װ�����] [3.��ٱ��ϰ���] [4.��������] [0.�ڷΰ���]");
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
			System.out.println("[1. ��ü ������] [2.������ �߰�] [3.������ ����] [0.�ڷΰ���]");
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
			System.out.println("[1. ��ü ī�װ�] [2.ī�װ� �߰�] [3.ī�װ� ����] [0.�ڷΰ���]");
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
			System.out.println("[1. ��ü ��ٱ��� ���] [0.�ڷΰ���]");
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
			System.out.println("[1. ��ü �����] [2.����� �߰�] [3.����� ����] [0.�ڷΰ���]");
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
