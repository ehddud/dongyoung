package kr.or.ddit.basic;

public class T09ThreadDaemonTest {

	public static void main(String[] args) {

		AutoSaveThread autoSave = new AutoSaveThread();

		// 데몬스레드로 설정하기
		// start() 메서드를 호출하기 전에 설정해야한다.
		autoSave.setDaemon(true);
		autoSave.start();
		
		
		try {
			
			for(int i = 1; i <=10; i++) {
				System.out.println("작업 - " + i);
				Thread.sleep(1000);
			}
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("main 스레드 종료...");
		
	}

}


/*
 * 3초에 한번씩 자동저장 기능을 호출하는 스레드
 */
class AutoSaveThread extends Thread{
	
	public void save() {
		System.out.println("작업 내용을 저장합니다...");
	}
	
	@Override
	public void run() {
		while(true) {
			
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			save();	// 저장기능 호출
			
		}
	}
	
}
