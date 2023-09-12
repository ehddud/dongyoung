package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class T03LambdaTest {

	public static void main(String[] args) {
		
		List<String> strList = new ArrayList<String>();
		strList.add("손동영");
		strList.add("이상민");
		strList.add("지윤서");
		
		for(String str : strList) {
			System.out.println(str);
		}
		
		/////////////////////////////////////
		System.out.println("-----------------------------------------");
//		strList.forEach(new Consumer<String>() {
//			@Override
//			public void accept(String name) {
//				System.out.println(name);
//			}
//		});
		strList.forEach(name -> System.out.println(name + "님"));
		
		// 로또번호 출력
		System.out.println();
		IntStream stream = new Random().ints(1, 46).distinct().limit(6).sorted();
		// intStream을 int 배열로 
		int[] intArr = stream.toArray();
		
		System.out.println(Arrays.toString(intArr));
		

		
	}
	
}
