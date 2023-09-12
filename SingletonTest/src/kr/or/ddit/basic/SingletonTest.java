package kr.or.ddit.basic;

public class SingletonTest {

	public static void main(String[] args) {
		// 생성자함수가 private이어서 외부에서 접근 불가능
		// MySingleton test1 = new MySingleton();
		
		// getInstance() 메서드를 이용하여 객체 생성
		MySingleton test2 = MySingleton.getInstance();
		MySingleton test3 = MySingleton.getInstance();
		
		System.out.println("test2 => " + test2);
		System.out.println("test3 => " + test3);
		
		test2.displayText();
		test3.displayText();
		
		
	}
}
