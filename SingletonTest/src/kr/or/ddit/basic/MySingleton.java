package kr.or.ddit.basic;

public class MySingleton {

	/*
	 * 싱글톤 패턴
	 * => 객체(인스턴스)를 하나만 만들어지게 하는 프로그래밍 방법
	 * 
	 * - 싱글톤 클래스를 구성하는 방법
	 * 1.자기자신 class 참조변수 멤버변수로 선언한다.
	 *   (이 변수는 private static으로 선언한다.)
	 * 2.생성자를 private으로 한다.
	 *   (외부에서 생성자에 접근하지 못하게 하기 위함. 즉, 외부에서 new 연산자로 객체 생성을 못하게 하기 위함)
	 * 3.객체(인스턴스)는 내부에서 생성해서 이 생성된 객체를 반환하는 메서드를 만든다.
	 *   (이 메서드는 static으로 선언하고 메서드의 이름은 보통 getInstance()로 지정한다.)
	 *   => 객체를 생성하지 않고 메서드를 사용하기 위해서 static으로 선언
	 *  
	 */
	
	// 자기자신의 class 참조값을 저장하는 멤버변수 선언
	private static MySingleton single;
	// private으로 생성자를 만든다
	private MySingleton() {System.out.println("생성자 입니다.");};
	
	public static MySingleton getInstance() {
		if(single == null) {
			single = new MySingleton();
		}
		return single;
	}
	
	public void displayText() {
		System.out.println("안녕하세요. 싱글톤객체입니다.");
	}
	
	
	
}
