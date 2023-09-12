package Homework;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class T02Homework2 {

	static Scanner sc = new Scanner(System.in);

	
	public static void main(String[] args) {
		
		init();

	}
	
	
	public static void init() {

		
		while(true) {
			
			System.out.println("===================================");
			
			
			System.out.println("1. Lotto 구입");
			System.out.println("2. 프로그램 종료");
			
			System.out.println("===================================");
			
			System.out.print("메뉴 선택 : ");
			
			try {
				int num = sc.nextInt();	
				switch(num) {
				case 1:
					buyLotto();
					break;
				case 2:
					System.out.println("Lotto 프로그램을 종료합니다.");
					System.exit(1);
				default :
					System.out.println("잘못 입력하셨습니다.");
					sc.nextLine();
				}
			}catch(Exception e) {
				System.out.println("숫자를 입력해 주세요.");
				sc.nextLine();
			}
			
		}
	}
	
	public static void buyLotto() {
		
		List<Set<Integer>> list = new ArrayList<Set<Integer>>();
		
		System.out.println("Lotto 구입 시작");
		System.out.println("(1000원에 로또번호 하나입니다.)");
		System.out.print("금액 입력 : ");
		
		int amount = sc.nextInt();

		int count = amount / 1000;
		if(count == 0) {
			System.out.println("아쉽지만 이 금액으로는 로또를 구매하실 수 없습니다.");
		}else {			
			System.out.println("구매한 로또 개수 : " + count + "개");
			int charge = amount % 1000;
			System.out.println("거스름돈 : " + charge + "원");
			
			
			for(int i = 0; i < count; i++) {
				
				list.add(makeLottoNum());
				
			}
			
			System.out.println("행운의 로또번호는 아래와 같습니다.");
			
			for(int i = 0; i < list.size(); i++) {
				System.out.println("로또번호 " + (i+1) + " : "+ list.get(i));
			}
		}
		
		
		
	}
	
	public static Set<Integer> makeLottoNum(){
		Set<Integer> set1 = new TreeSet<Integer>();
		
		while(set1.size() < 6) {
			int randNum = (int)(Math.random() * 45) + 1;
			set1.add(randNum);
		}
		return set1;
		
	}
	
	
}
