import java.util.Scanner;

import dvd.*;
import customer.*;


public class ManageMain {
	public static void main(String[] args) throws ClassNotFoundException {

		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		int menu=1;

		dvd.Rent_DVD rd = new Rent_DVD();
		dvd.Full_Search_DVD fsd = new Full_Search_DVD();
		dvd.Search_DVD sd = new Search_DVD();
		dvd.Insert_DVD id = new Insert_DVD();
		dvd.Return_DVD rrd = new Return_DVD();
		dvd.Rent_State rs = new Rent_State(); 
		dvd.Search_History sh = new Search_History();
		
		customer.Search_Customer sc = new Search_Customer();
		customer.Full_Search_Customer fsc = new Full_Search_Customer();
		customer.Insert_Customer ic = new Insert_Customer();
		
		while(menu != 0){
			System.out.println("======================");
			System.out.println("5조 DVD 대여점 관리 프로그램");
			System.out.println("-----------------------------------");
			System.out.println("1. DVD 대여");//영문. 완료
			System.out.println("2. DVD 반납");//진현, 완료
			System.out.println("3. DVD 대여현황");//영준. 테스트 필요.
			System.out.println("4. DVD 대여 이력 조회/관리");//영준. 테스트 필요
			System.out.println("-----------------------------------");
			System.out.println("5. DVD 전체목록"); //영준, 완료
			System.out.println("6. DVD 정보 상세 조회");//영문, 완료
			System.out.println("-----------------------------------");
			System.out.println("7. 회원 전체목록");//영준. 완료
			System.out.println("8. 회원 정보 상세 조회");//택훈, 완료  조회 안되면 에러 여러개 뜸. 수정했고,  대여이력도 뜨게 함.  테스트 필요
			System.out.println("-----------------------------------");
			System.out.println("9. DVD 입력");//홍식. 완료
			System.out.println("10. 회원 입력"); //회원: 홍식+가족: 영준  완료.
			System.out.print("메뉴 선택(종료: 0):  ");
			menu=scan.nextInt();

			switch (menu) {
			case 1:
				rd.rent_DVD();
				System.out.println();
				break;
			case 2:
				rrd.return_dvd();
				System.out.println();
				break;
			case 3:
				rs.rent_state();
				System.out.println();
				break;
			case 4:
				sh.search_history();
				System.out.println();
				break;
			case 5:
				fsd.scdvd();
				System.out.println();
				break;
			case 6:
				sd.scdvd();
				System.out.println();
				break;
			case 7:
				fsc.search_Customer();
				System.out.println();
				break;
			case 8:
				sc.search_Customer();
				System.out.println();
				break;
			case 9:
				id.insert_dvd();
				System.out.println();
				break;
			case 10:
				ic.insert_Customer();
				System.out.println();
				break;
			case 0:
				System.out.println("시스템을 종료합니다.");
				System.exit(0);
				break;
			default : 
				System.out.println("다시 입력해 주십시요.");
				System.out.println();
				continue;
			}
		}

	}

}
