package homework;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class T03Homework3 {

	private Scanner sc;
	private Map<Integer, RoomVO> roomBookMap;
	
	public static void main(String[] args) {
		

		new T03Homework3().init();
		
		
	}

	public T03Homework3() {
		sc = new Scanner(System.in);
		roomBookMap = new HashMap<Integer, RoomVO>();
	}
	
	
	public void init() {
		
		load();
		
		System.out.println("*********************************");
		System.out.println("호텔 문이 열렸습니다.");
		
		while(true) {
			
			displayMenu();
			
			System.out.print("메뉴 선택 >>");
			
			try {
				int select = sc.nextInt();
				switch(select) {
				case 1:
					checkIn();
					break;
				case 2:
					checkOut();
					break;
				case 3:
					roomState();
					break;
				case 4:
					System.out.println("*********************************");
					System.out.println("호텔 문을 닫았습니다.");
					System.out.println("*********************************");
					save();
					System.exit(1);
				default :
					System.out.println("잘못된 입력입니다. 다시 입력해 주세요.");
				}
			}catch(InputMismatchException e) {
				System.out.println("숫자만 입력해 주세요!");
				sc.nextLine();
			}
			
			
		}
		
	}
	
	public void displayMenu() {

		System.out.println("*********************************");
		System.out.println("어떤 업무를 하시겠습니까?");
		System.out.println("1.체크인 2.체크아웃 3.객실상태 4.업무종료");
		System.out.println("*********************************");
		
	}
	
	public void checkIn() {
		
		System.out.println("어느방에 체크인 하시겠습니까?");
		System.out.print("방번호 입력 >> ");
		int roomNum = sc.nextInt();
		
		if(roomBookMap.get(roomNum) != null) {
			System.out.println(roomNum+"번 방에는 이미 사람이 있습니다.");
			return;
		}
		sc.nextLine();
		
		System.out.println("체크인하실 분의 성함을 입력해주세요.");
		System.out.print("이름 입력 >>");
		String name = sc.nextLine();
		
		RoomVO rvo = new RoomVO(roomNum, name);
		
		roomBookMap.put(roomNum, rvo);
		
		System.out.println(roomNum+"번방 "+name+"님 체크인 완료!");
		
	}
	
	public void checkOut() {
		
		System.out.println("어느방을 체크아웃 하시겠습니까?");
		System.out.print("방번호 입력 >> ");
		int roomNum = sc.nextInt();
		
		if(roomBookMap.get(roomNum) == null) {
			System.out.println(roomNum + "번 방에는 체크인한 사람이 없습니다.");
			return;
		}

		System.out.println(roomNum+"번방 "+ roomBookMap.get(roomNum).getName() + "님이 체크아웃 되었습니다.");
		roomBookMap.remove(roomNum);
		
		
	}
	
	public void roomState() {

		System.out.println("*********************************");
		
		Set<Integer> keySet = roomBookMap.keySet();

		if(keySet.size() == 0) {
			System.out.println("체크인된 방이 없습니다.");
			return;
		}
		
		System.out.println("================================");		
		System.out.println("현재 투숙중인 방의 목록");
		System.out.println("방번호\t투숙객");
		System.out.println("--------------------------------");
		
		for(Integer key : keySet) {
			
			System.out.println(roomBookMap.get(key).getRoomNum() + "\t" + roomBookMap.get(key).getName());
			
		}
		
		return;

		
		
	}
	
	public void save() {
		
		ObjectOutputStream oos = null;
		FileOutputStream fos = null;
		
		try {
			fos = new FileOutputStream("d:/D_Other/roomVoObj.bin");
			oos = new ObjectOutputStream(fos);
			
			Set<Integer> keySet = roomBookMap.keySet();
			
			for(Integer key : keySet) {
				RoomVO vo = roomBookMap.get(key);
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
		
		
		
	}
	
	public void load() {
		
		ObjectInputStream ois = null;
		FileInputStream fis = null;
		
		try {
			fis = new FileInputStream("d:/D_Other/roomVoObj.bin");
			
			ois = new ObjectInputStream(fis);
			
			Object obj = null;
			
			while((obj = ois.readObject()) != null) {
				
				RoomVO vo = (RoomVO) obj;
				roomBookMap.put(vo.getRoomNum(), vo);
				
			}
			
		} catch (IOException e) {
			System.out.println("출력완료 !");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		
	}
	
}


class RoomVO implements Serializable, Comparable<RoomVO>{
	
	private int roomNum;
	private String name;
	
	public RoomVO(int roomNum, String name) {
		this.roomNum = roomNum;
		this.name = name;
	}

	public int getRoomNum() {
		return roomNum;
	}

	public void setRoomNum(int roomNum) {
		this.roomNum = roomNum;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int compareTo(RoomVO o) {
		return this.name.compareTo(o.getName());
	}
	
	
	
}