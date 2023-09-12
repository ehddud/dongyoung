package Homework;

import java.util.Scanner;

public class T02Homework2 {

	static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {

		init();

		
	}
	
	public static void init() {
		while(true) {
			System.out.println("태양계 행성 프로그램");
			System.out.println("1.반지름확인 2.면적확인 3.프로그램종료");
			System.out.print("메뉴를 선택하세요 >> "); 
			
			try {
				int choice = sc.nextInt();
				
				switch(choice) {
				case 1:
					checkRadius();
					break;
				case 2:
					checkArea();
					break;
				case 3:
					System.out.println("===프로그램을 종료합니다.===");
					System.exit(1);
				default : 
					System.out.println("===잘못 입력하셨습니다. 다시 입력해주세요.===");
				}				
			}catch(Exception e) {
				System.out.println("===숫자를 입력해 주세요.===");
				sc.nextLine();
			}
			
		}
	}
	
	private static void checkArea() {
		
		while(true) {
			showSolarSystem();
			System.out.println("반지름을 확인할 행성의 번호를 선택해주세요");
			System.out.print(">> ");
			
			try {
				int choice = sc.nextInt();
				
				switch(choice) {
				case 1:
					System.out.println(Planet.수성.name() + "의 면적 : " + getArea(Planet.수성) + "KM^2");
					return;
				case 2:
					System.out.println(Planet.금성.name() + "의 면적 : " + getArea(Planet.금성) + "KM^2");
					return;
				case 3:
					System.out.println(Planet.지구.name() + "의 면적 : " + getArea(Planet.지구) + "KM^2");
					return;
				case 4:
					System.out.println(Planet.화성.name() + "의 면적 : " + getArea(Planet.화성) + "KM^2");
					return;
				case 5:
					System.out.println(Planet.목성.name() + "의 면적 : " + getArea(Planet.목성) + "KM^2");
					return;
				case 6:
					System.out.println(Planet.토성.name() + "의 면적 : " + getArea(Planet.토성) + "KM^2");
					return;
				case 7:
					System.out.println(Planet.천왕성.name() + "의 면적 : " + getArea(Planet.천왕성) + "KM^2");
					return;
				case 8:
					System.out.println(Planet.해왕성.name() + "의 면적 : " + getArea(Planet.해왕성) + "KM^2");
					return;
				default : 
					System.out.println("===잘못 입력하셨습니다. 다시 입력해주세요.===");
				}
				
			}catch(Exception e) {
				System.out.println("===숫자를 입력해 주세요.===");
				sc.nextLine();
			}
		}
		
	}

	private static void checkRadius() {

		while(true) {			
			showSolarSystem();
			System.out.println("반지름을 확인할 행성의 번호를 선택해주세요");
			System.out.print(">> ");
			
			try {
				int choice = sc.nextInt();
				
				switch(choice) {
				case 1:
					System.out.println(Planet.수성.name() + "의 반지름 : " + Planet.수성.getRadius() + "KM");
					return;
				case 2:
					System.out.println(Planet.금성.name() + "의 반지름 : " + Planet.금성.getRadius() + "KM");
					return;
				case 3:
					System.out.println(Planet.지구.name() + "의 반지름 : " + Planet.지구.getRadius() + "KM");
					return;
				case 4:
					System.out.println(Planet.화성.name() + "의 반지름 : " + Planet.화성.getRadius() + "KM");
					return;
				case 5:
					System.out.println(Planet.목성.name() + "의 반지름 : " + Planet.목성.getRadius() + "KM");
					return;
				case 6:
					System.out.println(Planet.토성.name() + "의 반지름 : " + Planet.토성.getRadius() + "KM");
					return;
				case 7:
					System.out.println(Planet.천왕성.name() + "의 반지름 : " + Planet.천왕성.getRadius() + "KM");
					return;
				case 8:
					System.out.println(Planet.해왕성.name() + "의 반지름 : " + Planet.해왕성.getRadius() + "KM");
					return;
				default : 
					System.out.println("===잘못 입력하셨습니다. 다시 입력해주세요.===");
				}
				
			}catch(Exception e) {
				System.out.println("===숫자를 입력해 주세요.===");
				sc.nextLine();
			}
		}
		
		
	}

	private static void showSolarSystem() {

		System.out.println("1.수성");
		System.out.println("2.금성");
		System.out.println("3.지구");
		System.out.println("4.화성");
		System.out.println("5.목성");
		System.out.println("6.토성");
		System.out.println("7.천왕성");
		System.out.println("8.해왕성");
		
	}

	public enum Planet {
		수성(2439),
		금성(6052),
		지구(6371), 
		화성(3390), 
		목성(69911), 
		토성(58232), 
		천왕성(25362), 
		해왕성(24622);
		
		private int radius;
		
		Planet(int radius){
			this.radius = radius;
		}

		public int getRadius() {
			return radius;
		}

		
	}
	
	
	public static double getArea(Planet planet) {
		
		double radius = planet.getRadius();
		
		double area = Math.PI * Math.pow(radius, 2);
		
		return area;
		
		
	}
	
	
	
	
	
}
