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
		customer.Insert_Family iff = new Insert_Family();
		
		while(menu != 0){
			System.out.println("======================");
			System.out.println("5조 DVD 대여점 관리 프로그램");
			System.out.println("-----------------------------------");
			System.out.println("1. DVD 대여");//영문. 완료
			System.out.println("2. DVD 반납");//진현, 완료
			System.out.println("3. DVD 대여현황");//영준. 완료
			System.out.println("4. DVD 대여 이력 조회/관리");//영준. 완료
			System.out.println("-----------------------------------");
			System.out.println("5. DVD 전체목록"); //영준, 완료
			System.out.println("6. DVD 정보 상세 조회");//영문, 완료
			System.out.println("-----------------------------------");
			System.out.println("7. 회원 전체목록");//영준. 완료
			System.out.println("8. 회원 정보 상세 조회");//택훈, 동일 전번 출력안됨.
			System.out.println("-----------------------------------");
			System.out.println("9. DVD 입력");//홍식. 완료
			System.out.println("10. 회원 추가"); //회원: 홍식+가족: 영준  완료.
			System.out.println("11.기존 회원 가족멤버 추가");//영준. 완료
			System.out.print("메뉴 선택(종료: 99):  ");
			menu=scan.nextInt();

			switch (menu) {
			case 1:// dvd 대여
				rd.rent_DVD();
				System.out.println();
				break;
			case 2://dvd 반납
				rrd.return_dvd();
				System.out.println();
				break;
			case 3://dvd 대여 현황 조회
				rs.rent_state();
				System.out.println();
				break;
			case 4:// dvd 대여 이력 조회/관리
				sh.search_history();
				System.out.println();
				break;
			case 5://dvd 전체 목록
				fsd.scdvd();
				System.out.println();
				break;
			case 6://dvd 상세 조회
				sd.scdvd();
				System.out.println();
				break;
			case 7://회원 전체 목록
				fsc.search_Customer();
				System.out.println();
				break;
			case 8://회원 정보 상세 조회
				sc.search_Customer();
				System.out.println();
				break;
			case 9://dvd 입력
				id.insert_dvd();
				System.out.println();
				break;
			case 10://회원 추가
				ic.insert_Customer();
				System.out.println();
				break;
			case 11://기존 회원 가족멤버 추가
				int input=0;
				System.out.println("가족을 추가할 id를 입력하세요");
				input=scan.nextInt();
				iff.insert_family(input);
				System.out.println();
				break;
			case 99:// 종료
				System.out.println("시스템을 종료합니다.");
				System.exit(0);
				break;
			default : //입력 오류
				System.out.println("다시 입력해 주십시요.");
				System.out.println();
				continue;
			}
		}

	}

}
