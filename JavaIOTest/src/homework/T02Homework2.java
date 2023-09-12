package homework;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.InputMismatchException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class T02Homework2 {

	// member field 선언
	private Scanner sc;
	private Map<String, PhoneVO> phoneBookMap;
	static int No;
	
	public static void main(String[] args) {
		
		new T02Homework2().init();
		
	}
	
	public T02Homework2() {
		sc = new Scanner(System.in);
		phoneBookMap = new LinkedHashMap<String, PhoneVO>();
	}
	
	public void init() {
		
		load();
		
		System.out.println("==================================");
		System.out.println("전화번호 관리 프로그램 (파일로 저장됨)");
		System.out.println("==================================");
		
		try {			
			while(true) {
				displayMenu();
				int menuNum = sc.nextInt();
				
				switch (menuNum) {
				case 1:
					insert();
					break;
				case 2:
					update();
					break;
				case 3:
					delete();
					break;
				case 4:
					select();
					break;
				case 5:
					selectAll();
					break;
				case 0:
					exit();
					break;
					
				default:
					System.out.println("==================================");
					System.out.println("올바른 값을 입력해 주세요.");
					System.out.println("==================================");
				}
				
			}
		} catch (InputMismatchException e) {
			System.out.println("숫자를 입력해 주세요.");
			sc.nextLine();
			init();
		}
		
	}
	
	public void displayMenu() {
		System.out.println("메뉴를 선택하세요.");
		System.out.println("1.전화번호 등록");
		System.out.println("2.전화번호 수정");
		System.out.println("3.전화번호 삭제");
		System.out.println("4.전화번호 검색");
		System.out.println("5.전화번호 전체출력");
		System.out.println("0.프로그램 종료");
	}
	
	public void insert() {
		System.out.println();
		System.out.println("새롭게 등록할 전화번호 정보를 입력하세요.");
		System.out.print("이름 : ");
		String name = sc.next();
		
		if(phoneBookMap.get(name) != null) {
			System.out.println("==================================");
			System.out.println("이미 등록된 사람입니다.");
			System.out.println("초기화면으로 돌아갑니다.");
			System.out.println("==================================");
			return;
		}
		
		System.out.print("전화번호 : ");
		String tel = sc.next();
		System.out.print("주소 : ");
		String addr = sc.next();
		
		PhoneVO vo = new PhoneVO(name, tel, addr);
		vo.setNo(T02Homework2.No + 1);
		T02Homework2.No++;
		
		phoneBookMap.put(name, vo);

		System.out.println("==================================");
		System.out.println(name + "님 등록 완료!");
		System.out.println("==================================");
	}
	
	public void update() {
		
		System.out.println();
		System.out.println("수정할 전화번호 정보를 입력하세요.");
		System.out.print("이름 : ");
		String name = sc.next();
		if(phoneBookMap.get(name) == null) {
			System.out.println("==================================");
			System.out.println(name + "님은 등록되지 않은 회원입니다.");
			System.out.println("초기화면으로 돌아갑니다.");
			System.out.println("==================================");
			return;
		}
		
		PhoneVO bfvo = phoneBookMap.get(name);
		int no = bfvo.getNo();
		
		System.out.print("수정할 전화번호 : ");
		String tel = sc.next();
		
		
		System.out.print("수정할 주소 : ");
		String addr = sc.next();
		
		PhoneVO vo = new PhoneVO(name, tel, addr);
		vo.setNo(no);
		
		

		phoneBookMap.put(name, vo);
		
		System.out.println("==================================");
		System.out.println(name + "님의 정보가 수정되었습니다.");
		System.out.println("==================================");
		
	}

	public void delete() {
		System.out.println();
		System.out.println("삭제할 전화번호 정보를 입력하세요");
		System.out.print("이름 : ");
		String name = sc.next();
		if(phoneBookMap.remove(name) == null) {
			System.out.println(name + "님은 등록되지 않은 회원입니다.");
			System.out.println("초기화면으로 돌아갑니다.");
			return;
		}else {
			System.out.println(name + "씨의 전화번호 정보를 삭제하였습니다.");
		}
		
	}
	
	public void select() {
		System.out.println();
		System.out.println("검색할 전화번호 정보를 입력하세요");
		System.out.print("이름 : ");
		String name = sc.next();
		
		if(phoneBookMap.get(name) == null) {
			System.out.println(name + "님은 등록되지 않은 회원입니다.");
			System.out.println("초기화면으로 돌아갑니다.");
			return;
		}else {
			PhoneVO vo = phoneBookMap.get(name);
			System.out.println(name + "님의 전화번호 정보");
			System.out.println("번호\t이름\t전화번호\t주소\t");
			System.out.println(vo.getNo()+"\t"+vo.getName()+"\t"+vo.getTel()+"\t"+vo.getAddr());
		}
	}
	
	public void selectAll() {

		Set<String> keySet = phoneBookMap.keySet();
		
		if(keySet.size() == 0) {
			System.out.println("전화번호에 등록된 사람이 없습니다.");
			System.out.println("초기화면으로 돌아갑니다.");
			return;
		}
		
		System.out.println("번호\t이름\t전화번호\t주소");
		
		for(String set : keySet) {
			PhoneVO vo = phoneBookMap.get(set);
			System.out.println(vo.getNo()+"\t"+vo.getName()+"\t"+vo.getTel()+"\t"+vo.getAddr());
		}
		
	}
	
	public void exit()  {
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		
		try {			
			fos = new FileOutputStream("d:/D_Other/phoneVoObj.bin");
			oos = new ObjectOutputStream(fos);
			
			Set<String> keySet = phoneBookMap.keySet();
			
			for(String key : keySet) {
				PhoneVO vo = phoneBookMap.get(key);
				oos.writeObject(vo);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				oos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		System.out.println("프로그램을 종료합니다.");
		System.exit(1);
	}
	
	public void load() {
		ObjectInputStream ois = null;
		FileInputStream fis = null;
		
		try {
			
			fis = new FileInputStream("d:/D_Other/phoneVoObj.bin");
			ois = new ObjectInputStream(fis);
			
			Object obj = null;
			
			while((obj = ois.readObject()) != null) {
				
				PhoneVO vo = (PhoneVO) obj;
				
				phoneBookMap.put(vo.getName(), vo);
				
			}
			
			
		} catch (IOException e) {
			System.out.println("출력 완료!!");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				if(ois != null) {
					ois.close();					
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		Set<String> keySet = phoneBookMap.keySet();
		for(String key : keySet) {
			if(No < phoneBookMap.get(key).getNo()) {
				No = phoneBookMap.get(key).getNo();
			}
		}
		
	}

	
}


class PhoneVO implements Serializable{
	
	private String name;
	private String tel;
	private String addr;
	private int no;
	
	public PhoneVO(String name, String tel, String addr) {
		super();
		this.name = name;
		this.tel = tel;
		this.addr = addr;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	@Override
	public String toString() {
		return "PhoneVO [name=" + name + ", tel=" + tel + ", addr=" + addr + "]";
	}
	
	
	
}