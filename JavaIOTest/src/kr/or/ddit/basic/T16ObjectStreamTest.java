package kr.or.ddit.basic;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/*
 * 객체 입출력 보조 스트림 (직렬화와 역직렬화)
 */

public class T16ObjectStreamTest {

	public static void main(String[] args) {
		
		// Member 인스턴스 생성
		Member mem1 = new Member("홍길동", 20, "대전");
		Member mem2 = new Member("일지매", 30, "경기");
		Member mem3 = new Member("이몽룡", 35, "강원");
		Member mem4 = new Member("성춘향", 5, "부산");
		
		ObjectOutputStream oos = null;
		
		try {
			// 객체를 직렬화하여 파일에 저장
			oos = new ObjectOutputStream(
					new BufferedOutputStream(
						new FileOutputStream("d:/D_Other/memObj.bin")
					));
			
			// 쓰기 작업 (객체를 파일에 저장)
			oos.writeObject(mem1);
			oos.writeObject(mem2);
			oos.writeObject(mem3);
			oos.writeObject(mem4);
			
			System.out.println("객체 쓰기 작업 완료...");
			
		} catch (IOException  e) {
			e.printStackTrace();
		}finally {
			try {
				oos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		//////////////////////////////////////
		
		ObjectInputStream ois = null;
		
		try {
			// 파일에 저장된 객체를 역직렬화하여 읽어옴
			ois = new ObjectInputStream(
					new BufferedInputStream(
							new FileInputStream("d:/D_Other/memObj.bin")
						)
					);

			Object obj = null;
			
			// 역직렬화
			// readObject() 메서드는 더이상 읽어올 객체가 없을 때 EOFException을 발생
			// END OF FILE EXCEPTION
			while((obj = ois.readObject()) != null) {
				
				// 읽어온 데이터(object)를 원래의 객체형으로 변환 후 사용한다.
				Member mem = (Member) obj;
				System.out.println("이름 : " + mem.getName());
				System.out.println("나이 : " + mem.getAge());
				System.out.println("주소 : " + mem.getAddr());
				System.out.println("-----------------------------");
				
			}
			
			
		} catch (IOException e) {
			//e.printStackTrace();
			System.out.println("출력 완료!!");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}finally {
			try {
				ois.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
}

/*
 * 회원정보를 담기위한 VO(value object)
 */
class Member implements Serializable{
	// 자바는 Serializable 인터페이스를 구현한 객체만 직렬화 할 수 있도록 제한하고 있음
	
	// transient => 직렬화가 되지 않을 멤버변수에 지정한다.
	//				 직렬화가 되지 않는 멤버변수는 기본값으로 저장된다.
	//				 (참조변수 : null, 숫자형변수 : 0)
	//				 (*static 필드도 직렬화가 되지 않는다. 
	//				 => IO 대상은 인스턴스이므로 static 필드는 직렬화 대상이 아님)
	
	transient private String name;
	private int age;
	private String addr;
	
	public Member(String name, int age, String addr) {
		super();
		this.name = name;
		this.age = age;
		this.addr = addr;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	
	
	
}
