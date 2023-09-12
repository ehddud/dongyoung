package kr.or.ddit.basic;

public class ex {

	public static void main(String[] args) {
		Child child = new Child();
		System.out.println(child.x);
		System.out.println(child.getX());
	}
	
	
}

class Parent{
	int x = 100;
	Parent(){
		this(500);
		System.out.println("부모생성자");
	}
	Parent(int x){
		this.x = x;
	}
	int getX() {
		return x;
	}
}

class Child extends Parent {
	int x = 4000;
	
	Child(){
		this(5000);
		System.out.println("자식생성자");
	}
	
	Child(int x){
		this.x = x;
	}

}

