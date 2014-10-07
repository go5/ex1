import java.util.Scanner;

import dvd.*;
import customer.*;


public class ManagementMain {
	public static void main(String[] args) {
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		int menu=0;

		dvd.Manage_DVD md = new Manage_DVD();
		dvd.Search_DVD sd = new Search_DVD();
		customer.Search_Customer sc = new Search_Customer();

		while(menu != 0){
			System.out.println("5조 DVD 대여점 관리 프로그램");
			System.out.println("-----------------------------------");
			System.out.println("1. DVD 대여/반납");
			System.out.println("2. DVD 조회");
			System.out.println("3. 고객 조회");
			System.out.print("메뉴 선택(종료: 0):  ");
			menu=scan.nextInt();

			switch (menu) {
			case 1:
				md.manage_DVD();
				System.out.println();
				break;
			case 2:
				sd.search_DVD();
				System.out.println();
				break;
			case 3:
				sc.search_Customer();
				System.out.println();
				break;
			case 0:
				System.out.println("시스템을 종료합니다.");
				System.exit(0);
				break;
			default : 
				System.out.println("시스템을 종료합니다.");
				System.out.println();
			}
		}

	}

}
