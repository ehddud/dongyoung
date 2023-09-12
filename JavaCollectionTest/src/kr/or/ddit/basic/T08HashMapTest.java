package kr.or.ddit.basic;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class T08HashMapTest {

	public static void main(String[] args) {
		
		Map<String, String> map = new HashMap<String, String>();
		
		// 데이터 추가하기 => put(key값, value값)
		map.put("name", "홍길동");
		map.put("addr", "대전");
		map.put("tel", "010-1111-1111");
		
		System.out.println("map => " + map);
		
		// 데이터 수정하기 => 데이터를 추가할 때, key값이 같으면 나중에 입력한 value가
		// 				  해당 key의 value로 덮어쓰기된다. put(key값, value값)
		map.put("addr", "서울");
		System.out.println("map => " + map);
		
		// 데이터 삭제하기 => remove(삭제할 데이터의 키값)
		map.remove("name");
		System.out.println("map => " + map);

		// 데이터 읽기
		System.out.println("addr = " + map.get("addr"));
		System.out.println("===================================================");
		
		// key값들을 읽어와 데이터를 출력하는 방법
		
		// 방법1 => keySet() 이용하기
		// 		=> Map의 key값들만 읽어와 Set타입의 객체로 반환한다.
		Set<String> keySet = map.keySet();
		
		System.out.println("Iterator를 이용한 방법");
		// keySet은 Set타입이므로 Iterator 사용 가능
		Iterator<String> it = keySet.iterator();
		
		while(it.hasNext()) {
			String key = it.next();
			System.out.println(key + " : " + map.get(key));
		}
		System.out.println("---------------------------------------------------");
		
		// 방법2 => Set타입의 데이터를 '향상된 for문'으로 처리하면 Iterator를 사용하지 않아도 된다.
		System.out.println("향상된 for문을 이용하는 방법");
		
		for(String key : keySet) {
			System.out.println(key + " : " + map.get(key));
		}
		System.out.println("---------------------------------------------------");

		System.out.println("value값들만 읽어와 출력하기");
		// 방법3 => value값들만 읽어와 출력하기 : map.values() 이용
		for(String value : map.values()) {
			System.out.println(value);
		}
		System.out.println("---------------------------------------------------");
		
		// 방법4 => Map관련 클래스에는 Map.Entry타입의 내부 클래스를 가지고 있다.
		//		     이 내부 클래스는 key와 value값을 멤버변수로 갖는다.
		//		   Map객체는 이 Map.Entry타입의 객체들을 Set타입의 객체로 저장하여 관리한다.
		//		   map.entrySet()
		
		// map.entrySet() 이용하여 가져오기
		// map의 entry값들을 Set객체에 담아 리턴
		System.out.println("entrySet()을 이용한 방법");
		Set<Map.Entry<String, String>> entrySet = map.entrySet();
		
		
		System.out.println("Iterator를 이용한 방법 ");
		// 가져온 Entry객체들을 순서대로 접근하기 위한 Iterator객체 가져오기
		Iterator<Map.Entry<String, String>> entryIt = entrySet.iterator();
		
		while(entryIt.hasNext()) {
			Map.Entry<String, String> entry = entryIt.next();
			System.out.println("key값 : " + entry.getKey());
			System.out.println("value값 : " + entry.getValue());
			System.out.println();
		}
		

		System.out.println("향상된 for문을 이용한 방법 ");
		for(Map.Entry<String, String> entry : entrySet) {
			System.out.println("key : " + entry.getKey());
			System.out.println("values : " + entry.getValue());
			System.out.println();
		}
		
		
		
	}
	
	
}
