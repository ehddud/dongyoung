package homework;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JOptionPane;

public class T01Homework1 {
	
	static String myData;
	static boolean isInput = false;
	
	public static void main(String[] args) {
		
		String[] data = {"가위", "바위", "보"};
		int index = (int)(Math.random() * 3);
		
		String compData = data[index];
		
		String result = "";
			
		Thread myinput = new MyInput();
		Thread countdown = new CountDown();
		myinput.start();
		countdown.start();
		
		
		
		try {
			myinput.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(compData.equals(myData)) {
			result = "무승부!";
		}else if( 
					( compData.equals("가위") && myData.equals("보") ) ||
					( compData.equals("바위") && myData.equals("가위") ) ||
					( compData.equals("보") && myData.equals("바위") ) 
				){
			result = "당신이 패배하셨습니다.";
		}else if(
					( compData.equals("가위") && myData.equals("바위") ) ||
					( compData.equals("바위") && myData.equals("보") ) ||
					( compData.equals("보") && myData.equals("가위") ) 
				) {
			result = "당신이 승리하였습니다.";
		}
			
		System.out.println("경기 종료!");
		System.out.println("=======================================");
		System.out.println("컴퓨터의 입력값 : " + compData);
		System.out.println("당신의 입력값 : " + myData);
		System.out.println("경기 결과 : " + result);
		
	}
	
}


class MyInput extends Thread {
	
	@Override
	public void run() {
		while(true) {
			String myinput = JOptionPane.showInputDialog("값을 입력해주세요.");
			
			if(!myinput.equals("가위") && !myinput.equals("바위") && !myinput.equals("보")) {
				System.out.println("올바른 값을 입력해 주세요.");
			}else {
				T01Homework1.myData = myinput;
				T01Homework1.isInput = true;
				return;
			}
			
		}
	}
	

}


class CountDown extends Thread {

	@Override
	public void run() {

		for(int i = 5; i > 0 ; i--) {
			
			if(!T01Homework1.isInput) {
				
				System.out.println(i);
			
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}				
			}else {
				return;
			}
			
			
		}
		
		System.out.println("시간 초과로 당신이 패배하였습니다.");
		System.exit(1);
		
		
	}
	
	
	
}