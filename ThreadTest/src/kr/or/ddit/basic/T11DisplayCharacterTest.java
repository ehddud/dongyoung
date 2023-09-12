package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class T11DisplayCharacterTest {

	static int chRank = 1;	// 현재 순위 정보
	
	public static void main(String[] args) {
		/*
		 * 3개(명)의 스레드가 각각 알파벳 대문자를 출력하는데, 
		 * 출력을 끝낸 순서대로 결과를 나타내는 프로그램 작성하기
		 */
		
		List<DisplayCharacter> disCharList = new ArrayList<DisplayCharacter>();
		disCharList.add(new DisplayCharacter("홍길동"));
		disCharList.add(new DisplayCharacter("손동영"));
		disCharList.add(new DisplayCharacter("김사라"));
		
		for(DisplayCharacter dc : disCharList) {
			dc.start();
		}
		
		for(DisplayCharacter dc : disCharList) {
			try {
				dc.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		Collections.sort(disCharList);	// 정렬하기
		
		System.out.println("=========== 경기 끝 ===========");
		System.out.println("=========== 등수 ===========");
		System.out.println("순위\t:\t이름");
		for(DisplayCharacter dp : disCharList) {
			System.out.println(dp.getRank() + "\t:\t" + dp.getName1());
		}
		
	}
	
}

// 알파벳 대문자를 출력하는 스레드
class DisplayCharacter extends Thread implements Comparable<DisplayCharacter>{

	private String name;
	
	private int rank;
	
	
	public DisplayCharacter(String name) {
		this.name = name;
	}

	public String getName1() {
		return name;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	@Override
	public int compareTo(DisplayCharacter d) {

		return Integer.compare(this.getRank(), d.getRank());
	}

	public void run() {

		for(char ch = 'A'; ch <= 'Z'; ch++) {
			System.out.println(name + "의 출력 문자 : " + ch);
			
			try {
				// 200 ~ 500 milliseconds만큼 일시정지(sleep())
				Thread.sleep((int)(Math.random() * 301 + 200));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		
		System.out.println(name + " 끝!! ");
		
		this.setRank(T11DisplayCharacterTest.chRank);
		T11DisplayCharacterTest.chRank++;
		
	}
	
	
	
}

