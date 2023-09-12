package kr.or.ddit.basic;

public class T13ThreadStopTest {

	public static void main(String[] args) {
	
		ThreadStopEx1 th1 = new ThreadStopEx1();
		ThreadStopEx2 th2 = new ThreadStopEx2();
		ThreadStopEx3 th3 = new ThreadStopEx3();
		ThreadStopEx4 th4 = new ThreadStopEx4();
		
		th1.start();
		th2.start();
		th3.start();
		th4.start();
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		/*
		 * Thread의 stop() 메서드를 호출하면 스레드가 바로 종료된다.
		 * 하지만 이렇게 종료를 하면 사용하던 자원을 정리하지 못하고 프로그램이 종료되어서
		 * 나중에 실행되는 프로그램에 영향을 줄 수 있다.
		 * 그래서 현재는 stop() 메서드는 비추천(Deprecated)된 상태이다
		 */
		// th1.stop();
		
		th1.setStopped(true);
		
		th2.interrupt(); // interrupt 걸기
		
		th3.interrupt();
		
		th4.interrupt();
		
		
	}
	
}

class ThreadStopEx1 extends Thread {
	
	private boolean isStopped;

	public void setStopped(boolean isStopped) {
		this.isStopped = isStopped;
	}
	

	@Override
	public void run() {
		
		while(!isStopped) {
			System.out.println("스레드 처리 중...");
		}
		
		// 스레드가 종료되기 전에 실행해야 할 것
		System.out.println(this.getName() + " 자원 정리 중..");
		System.out.println(this.getName() + " 실행 종료.");	
		
		
		
		
	}
	
}

//interrupt() 메서드를 이용하여 스레드를 멈추게 하는 방법
class ThreadStopEx2 extends Thread{
	
	@Override
	public void run() {
		// 방법 1 => sleep() 메서드나 join() 메서드 등을 사용했을 때 (일시정지 상태가 되었을 때)
		// 		   interrupt() 메서드를 호출하면 interruptedException 예외가 발생
		try {
			
			while(true) {
				System.out.println("스레드 처리 중...");
				Thread.sleep(1);
			}
			
		} catch (InterruptedException e) {
			System.out.println(this.getName() + " 자원 정리 중..");
			System.out.println(this.getName() + " 실행 종료.");			
		}
		
	}
	
}

//interrupt() 메서드를 이용하여 스레드를 멈추게 하는 방법
class ThreadStopEx3 extends Thread{
	
	@Override
	public void run() {
		// 방법 2 => interrupt() 메서드가 호출되었는지 검사하기
		while(true) {
			System.out.println("스레드 처리 중...");
			
			// 검사방법 1 => 스레드의 인스턴스용 메서드를 이용하는 방법
			// thread객체.isInterrupted() -> thread객체의 interrupted 상태를 반환
			if(this.isInterrupted()) {
				System.out.println(this.getName() + " 인스턴스용 메서드 호출됨");
				break;
			}
		} 
		System.out.println(this.getName() + " 자원 정리 중..");
		System.out.println(this.getName() + " 실행 종료.");		
		
	}
	
}

//interrupt() 메서드를 이용하여 스레드를 멈추게 하는 방법
class ThreadStopEx4 extends Thread{
	
	@Override
	public void run() {
		// 방법 2 => interrupt() 메서드가 호출되었는지 검사하기
		while(true) {
			System.out.println("스레드 처리 중...");
			
			// 검사방법 2 => 스레드의 정적메서드를  이용하는 방법
			// Thread.interrrupted() -> 현재 스레드의 interrupted 상태를 알려주고,
			// 즉시  interrupted 값을 false로 초기화
			if(Thread.interrupted()) {
				System.out.println(this.getName() + " 정적 메서드 호출됨 => " + Thread.interrupted());
				break;
			}
		} 
		System.out.println(this.getName() + " 자원 정리 중..");
		System.out.println(this.getName() + " 실행 종료.");		
		
	}
	
}