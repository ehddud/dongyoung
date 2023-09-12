package homework;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class T03Homework3 {

	static int ranking = 1;
	static boolean isEnd ;
	public static void main(String[] args) {
		
		List<Horse2> horseList = new ArrayList<Horse2>();
		horseList.add(new Horse2("1번마"));
		horseList.add(new Horse2("2번마"));
		horseList.add(new Horse2("3번마"));
		horseList.add(new Horse2("4번마"));
		horseList.add(new Horse2("5번마"));
		horseList.add(new Horse2("6번마"));
		horseList.add(new Horse2("7번마"));
		horseList.add(new Horse2("8번마"));
		horseList.add(new Horse2("9번마"));
		horseList.add(new Horse2("10번마"));
		
		for(Horse2 hr : horseList) {
			
			hr.start();
			
		}
		
		Thread th = new PrintThread(horseList);
		th.setDaemon(true);
		th.start();
		
		for(Horse2 hr : horseList) {
			
			try {
				hr.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		Collections.sort(horseList);
		
		System.out.println("========= 경기 종료 =========");
		System.out.println("이름 \t 등수");
		for(Horse2 hr : horseList) {
			System.out.println(hr.getName2() + " \t " + hr.getRank()+"등");
		}
		
		
	}
	
}

class Horse2 extends Thread implements Comparable<Horse2>{
	
	private String name;
	private int rank;
	
	private String stat;
	
	
	public String getStat() {
		return stat;
	}


	public void setStat(String stat) {
		this.stat = stat;
	}


	public Horse2(String name) {
		this.name = name;
	}


	public String getName2() {
		return name;
	}


	public void setName2(String name) {
		this.name = name;
	}
	


	public int getRank() {
		return rank;
	}


	public void setRank(int rank) {
		this.rank = rank;
	}


	@Override
	public void run() {
		
		for(int i = 0; i < 50; i++) {
			stat = "";
			
			for(int j = 0; j < i ; j++) {
				stat += "-";
			}
			
			stat += ">";
			
			for(int k = 49; k > i ; k--) {
				stat += "-";
			}
			
			
			this.setStat(stat);
			
			try {
				Thread.sleep((int)(Math.random() * 500));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		
		
		this.setRank(T03Homework3.ranking);
		T03Homework3.ranking++;
		
		System.out.println(this.getName2() + " 경주 끝!");
		
	}
	
	
	@Override
	public int compareTo(Horse2 horse) {
		
		return Integer.compare(this.rank, horse.rank);
		
	}

	
}

class PrintThread extends Thread{

	List<Horse2> list;
	
	PrintThread(List<Horse2> list){
		this.list = list;
	}
	
	@Override
	public void run() {
		
		
		while(true) {
			for(int i = 0; i < list.size(); i++) {
				System.out.println(list.get(i).getName2());
				System.out.println(list.get(i).getStat());	
			}
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				return;
			}
			
			
		}
		
	}
	
	
	
}
