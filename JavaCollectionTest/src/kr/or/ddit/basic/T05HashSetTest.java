package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class T05HashSetTest {

	public static void main(String[] args) {
		Set hs1 = new HashSet();
		
		// Set에 데이터를 추가할 때에도 add() 메서드를 사용한다.
		
		hs1.add("DD");
		hs1.add("AA");
		hs1.add(2);
		hs1.add("CC");
		hs1.add(1);
		hs1.add(3);
		hs1.add("BB");
		
		System.out.println("Set 데이터 : " + hs1);
		System.out.println();
		
		// Set은 데이터의 중복을 허용하지 않는다. 그래서 이미 존재하는 데이터를 추가하면 false를 반환한다.
		boolean isAdded = hs1.add("FF");
		System.out.println("중복되지 않을 때 : " + isAdded);
		System.out.println("Set 데이터 : " + hs1);
		System.out.println();
		
		isAdded = hs1.add("DD");
		System.out.println("중복될 때 : " + isAdded);
		System.out.println("Set 데이터 : " + hs1);
		System.out.println();

		// Set의 데이터를 수정하려면 수정하는 명령이 따로 없기 때문에
		// 해당 데이터를 삭제한 후 새로운 데이터를 추가해 주어야 한다.
		
		// 삭제하는 메서드
		// 1) clear() => 전체 데이터 삭제
		// 2) remove(삭제할 데이터) => 해당 데이터 삭제
		// 예) 'FF'를 'EE'로 수정하기
		hs1.remove("FF");
		System.out.println("FF 삭제 후 데이터 : " + hs1);
		System.out.println();
		
		hs1.add("EE");
		System.out.println("Set 데이터 : " + hs1);
		System.out.println("EE 추가 후 데이터 크기 : " + hs1.size());
		System.out.println();
		
		//hs1.clear(); // 전체 자료 삭제
		//System.out.println("clear() 후 데이터 : " + hs1.size());
		
		System.out.println("for 문으로 돌린 Set");
		for(Object obj : hs1) {
			System.out.println(obj);
		}
		
		System.out.println("===========================");
		
		// Set은 데이터의 인덱스(순번) 개념이 없기 때문에 데이터를 하나씩 가져오기 위해
		// Iterator 객체를 이용한다.
		Iterator it = hs1.iterator();
		
		// 데이터 개수만큼 반복하기
		// hasNext() 메서드 -> 포인터 다음 위치에 데이터가 있으면 true, 없으면 false를 반환한다.
		
		System.out.println("Iterator로 돌린 Set");
		while(it.hasNext()) { // 다음 데이터가 있는지 검사
			// next() 메서드 -> 다음 위치의 데이터를 반환한다.
			System.out.println(it.next());
		}
		
		
		// 1~100 사이의 중복되지 않는 정수 5개 만들기
		Set<Integer> intRnd = new HashSet<Integer>();
		
		while(intRnd.size() < 5) {
			int num = (int) (Math.random() * 100 + 1);
			intRnd.add(num);
		}
		
		System.out.println("만들어진 난수들 : " + intRnd);
		
		
		// Collection 유형의 객체들을 서로 다른 자료 구조로 쉽게 변경해서 사용할 수 있다.
		// 다른 종류의 객체를 생성할 때 생성자에 변경할 객체를 넣어주면 된다.
		List<Integer> intRndList = new ArrayList<Integer>(intRnd); // intRnd를 List<Integer>로 변환
		System.out.println("List의 자료 출력...");

		for(Integer num : intRndList) {
			System.out.println(num + " ");
		}
		
		
		
		
	}
	
}
