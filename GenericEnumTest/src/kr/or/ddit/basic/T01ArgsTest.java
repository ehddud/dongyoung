package kr.or.ddit.basic;

public class T01ArgsTest {

	/*
	 * 가변형 인수 => 메서드의 매개변수의 개수가 실행될 때마다 다를 때 사용한다.
	 * - 가변형 인수는 메서드 안에서는 배열로 처리된다.
	 * - 가변형 인수는 한가지 자료형만 사용할 수 있다.
	 */
	public static void main(String[] args) {
		T01ArgsTest arg = new T01ArgsTest();
		
		int[] nums = {100,200,300};
		System.out.println(arg.sumArr(nums));
		System.out.println(arg.sumArr(new int[] {1,2,3,4,5}));
		System.out.println();
		
		System.out.println(arg.sumArgs(100,200,300));
		System.out.println(arg.sumArgs(1,2,3,4,5));
		System.out.println();
		
		System.out.println(arg.sumArgs2("홍길동", 1,2,3,4,5,6,7,8));
		
	}
	

	/*
	 * 배열을 이용한 메서드
	 */
	public int sumArr(int[] data) {
		int sum = 0;
		
		for(int i = 0; i < data.length; i++) {
			sum += data[i];
		}
		
		return sum;
	}
	
	/*
	 * 가변형 인수를 이용한 메서드
	 */
	 // 가변형 인수 문법 => 매개변수의 개수가 정해지지 않았을 때 매개변수의 데이터타입에 ...을 붙인다
	 // int[] data 이런식으로 배열로 표현하지 않아도 배열처럼 data[2]이런식으로 index를 지정할 수 있다
	public int sumArgs(int... data) {
		int sum = 0;
		
		for(int i = 0; i < data.length; i++) {
			sum += data[i];
		}
		
		return sum;
	}
	
	/*
	 * 가변형 인수와 일반적인 인수를 같이 사용할 경우에는
	 * 가변형 일수를 제일 뒤쪽에 배치해야 한다.
	 */
	public String sumArgs2(String name, int... data) {
		int sum = 0;
		
		for(int i = 0; i < data.length; i++) {
			sum += data[i];
		}
		
		return name + "씨의 점수 : " + sum;
	}
	
}
