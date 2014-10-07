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
			System.out.println("5�� DVD �뿩�� ���� ���α׷�");
			System.out.println("-----------------------------------");
			System.out.println("1. DVD �뿩/�ݳ�");
			System.out.println("2. DVD ��ȸ");
			System.out.println("3. �� ��ȸ");
			System.out.print("�޴� ����(����: 0):  ");
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
				System.out.println("�ý����� �����մϴ�.");
				System.exit(0);
				break;
			default : 
				System.out.println("�߸��� ����Դϴ�. ");
				System.out.println();
			}
		}

	}

}
