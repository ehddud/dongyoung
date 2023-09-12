package kr.or.ddit.basic;

@FunctionalInterface
public interface LambdaTestInterface1 {

	// Functional Interface는 추상메서드가 1개 존재해야 한다
	// 반환값이 없고 매개변수도 없는 추상메서드 선언
	public void test(); // 추상메서드
	
}
// public이 있는 클래스, 인터페이스는 자바 파일당 하나만 존재할 수 있다.
@FunctionalInterface
interface LambdaTestInterface2 {
	
	// Functional Interface는 추상메서드가 1개 존재해야 한다
	// 반환값이 없고 매개변수가 있는 추상메서드 선언
	public void test(int a); // 추상메서드
	
}

@FunctionalInterface
interface LambdaTestInterface3 {
	
	// Functional Interface는 추상메서드가 1개 존재해야 한다
	// 반환값도 있고 매개변수도 있는 추상메서드 선언
	public int test(int a, int b); // 추상메서드
}
