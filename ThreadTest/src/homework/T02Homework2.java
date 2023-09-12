package homework;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class T02Homework2 {

	static int horseRank = 1;

	public static void main(String[] args) {


		List<Horse> horseList = new ArrayList<Horse>();
		horseList.add(new Horse("1번마"));
		horseList.add(new Horse("2번마"));
		horseList.add(new Horse("3번마"));
		horseList.add(new Horse("4번마"));
		horseList.add(new Horse("5번마"));
		horseList.add(new Horse("6번마"));
		horseList.add(new Horse("7번마"));
		horseList.add(new Horse("8번마"));
		horseList.add(new Horse("9번마"));
		horseList.add(new Horse("10번마"));
		
		for(Horse hr : horseList) {
			hr.start();
		}
		
		for(Horse hr : horseList) {
			try {
				hr.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		Collections.sort(horseList);
		
		System.out.println("경기 종료!");
		System.out.println("============================================");
		System.out.println();
		System.out.println("경기 결과 : ");
		
		for(int i = 0; i < horseList.size(); i++) {

			System.out.println(horseList.get(i).getName1() +  " : " + horseList.get(i).getRank() + "등");
			
		}
		
		
	}
}


class Horse extends Thread implements Comparable<Horse> {

	private String name;
	private int rank;
	
	Horse(String name){
		this.name = name;
	}
	


	public int getRank() {
		return rank;
	}



	public void setRank(int rank) {
		this.rank = rank;
	}



	public String getName1() {
		return name;
	}



	public void setName1(String name) {
		this.name = name;
	}



	@Override
	public void run() {
		for(int i =0; i<50; i++) {
			System.out.println("\n" + name+ " : ");
			for(int j = 0; j <i; j++) {
				System.out.print("-");
			}
			
			System.out.print(">");
			
			for(int k = 49 ; k > i; k--) {
				System.out.print("-");
			}
			
			System.out.println();
			System.out.println();
			System.out.println();
			
			try {
				Thread.sleep((int)(Math.random() *500));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		setRank(T02Homework2.horseRank);
		
		System.out.println("============= " + name+ " " + getRank() +"등으로 완주 완료. =============");
		
		T02Homework2.horseRank++;
	}







	@Override
	public int compareTo(Horse horse) {

		return Integer.compare(this.rank, horse.rank);
		
	}
	
	
	
}