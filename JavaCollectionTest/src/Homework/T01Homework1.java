package Homework;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Queue;

public class T01Homework1 {

	public static void main(String[] args) {

		
		List<Student> stuList = new ArrayList<Student>();
		
		stuList.add(new Student("109", "김길동", 50, 10, 100));
		stuList.add(new Student("120", "차무식", 55, 20, 50));
		stuList.add(new Student("101", "강동원", 60, 60, 60));
		stuList.add(new Student("150", "유해진", 80, 80, 80));
		stuList.add(new Student("1", "곽도원", 20, 90, 80));
		stuList.add(new Student("30", "차승원", 10, 90, 60));
		stuList.add(new Student("45", "손동영", 60, 60, 40));
		stuList.add(new Student("790", "원빈", 40, 90, 100));
		
		T01Homework1.setRanking(stuList);
		
		Collections.shuffle(stuList);
		
		System.out.println("정렬 전 : ");
		for(Student stu : stuList) {
			System.out.println(stu);
		}
		
		Collections.sort(stuList); 
		
		System.out.println("학번 오름차순 정렬 후 : ");
		for(Student stu : stuList) {
			System.out.println(stu);
		}
		
		Collections.sort(stuList, new sumDesc());
		
		System.out.println("총점 내림차순 정렬 후 (총점이 같으면 학번의 내림차순으로 정렬되도록 한다.): ");
		for(Student stu : stuList) {
			System.out.println(stu);
		}
		
		
		
	}
	
	public static void setRanking(List<Student> stuList) {


		for(int i = 0; i < stuList.size(); i++) {
			int rank = 1;
			for(int j = 0 ; j < stuList.size(); j++) {
				if(stuList.get(i).getSum() < stuList.get(j).getSum()) {
					rank++;
				}
			}
			
			stuList.get(i).setRank(rank);
			
		}
		
		
		
	}
	
}


class Student implements Comparable<Student>{
	
	private String stuNo;
	private String name;
	private int kor;
	private int eng;
	private int math;
	private int sum;
	private int rank;
	
	public Student(String stuNo, String name, int kor, int eng, int math) {
		
		this.stuNo = stuNo;
		this.name = name;
		this.kor = kor;
		this.eng = eng;
		this.math = math;
		this.sum = kor+eng+math;
		
	}
	
	


	@Override
	public String toString() {
		return "Student [stuNo=" + stuNo + ", name=" + name + ", kor=" + kor + ", eng=" + eng + ", math=" + math
				+ ", sum=" + sum + ", rank=" + rank + "]";
	}




	public String getStuNo() {
		return stuNo;
	}

	public void setStuNo(String stuNo) {
		this.stuNo = stuNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getKor() {
		return kor;
	}

	public void setKor(int kor) {
		this.kor = kor;
	}

	public int getEng() {
		return eng;
	}

	public int getSum() {
		return sum;
	}

	public void setSum(int sum) {
		this.sum = sum;
	}

	public void setEng(int eng) {
		this.eng = eng;
	}

	public int getMath() {
		return math;
	}

	public void setMath(int math) {
		this.math = math;
	}
	
	public int getRank() {
		return rank;
	}
	
	public void setRank(int rank) {
		this.rank = rank;
	}

	// stuList 자체를 sort 가능하게 만든 것
	@Override
	public int compareTo(Student o) {
		Integer stuNo1 = Integer.valueOf(this.getStuNo());
		Integer stuNo2 = Integer.valueOf(o.getStuNo());

		return stuNo1.compareTo(stuNo2);
		
		
	}
	
}

class stuNoAsc implements Comparator<Student>{
	
	@Override
	public int compare(Student o1, Student o2) {

		String stuNo1 =  o1.getStuNo();
		String stuNo2 = o2.getStuNo();
		
		if(stuNo1.compareTo(stuNo2) > 0 ) {
			return 1;
		}else if(stuNo1.compareTo(stuNo2) == 0) {
			return 0;
		}else {
			return -1;
		}
		
		
	}
	
	
	
}


// 총점 내림차순 정렬 후 (총점이 같으면 학번의 내림차순으로 정렬되도록 한다.): 
class sumDesc implements Comparator<Student>{

	@Override
	public int compare(Student o1, Student o2) {

		
		if(o1.getSum() == o2.getSum()) {
			return Integer.valueOf(o1.getStuNo()).compareTo(Integer.valueOf(o2.getStuNo())) * -1;
		}else {
			return Integer.compare(o1.getSum(), o2.getSum()) * -1;
		}

		
	}
	
	
	
	
	
}
