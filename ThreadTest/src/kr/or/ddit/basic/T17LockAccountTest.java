package kr.or.ddit.basic;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 은행의 입출금을 스레드로 처리하는 예제
 * (Lock 객체를 이용한 동기화 처리)
 */
public class T17LockAccountTest {

	/*
	 * Lock 기능을 제공하는 클래스
	 * - ReentrantLock : Read 및 Write 구분없이 사용하기 위한 Lock클래스로 동기화 처리를 위해 사용된다. 
	 * 		Synchronized를 이용한 동기화 처리보다 부가적인 기능이 제공된다. 
	 * 		ex) fairness 설정 등. => 가장 오래 기다린 스레드가 가장 먼저 Lock 획득함
	 * 
	 * - ReentrantReadWriteLock : Read 및 Write 락을 구분하여 사용가능함.
	 * 		Read작업은 여러 스레드가 동시에 작업 가능하지만, Write작업은 하나의 스레드만 작업 가능(exclusive)
	 * 		=> Write보다 Read 위주의 작업이 많이 발생하는 경우에 사용하면 
	 * 			처리량(Throughput)이 좋아진다.
	 */
	
	public static void main(String[] args) {

		ReentrantLock lock = new ReentrantLock(true);

		LockAccount lAcc = new LockAccount(lock);
		lAcc.deposit(10000);
		
		Thread th1 = new BankThread2(lAcc);
		Thread th2 = new BankThread2(lAcc);
		
		th1.start();
		th2.start();
		
	}
	
	
}

// 입출금을 담당하는 스레드
class LockAccount {
	
	private int balance;
	
	// lock 객체 생성 -> 되도록이면 private final로 만든다.
	private final Lock lock;

	public LockAccount(Lock lock) {
		this.lock = lock;
	}
	
	public int getBalance() {
		return balance;
	}
	
	// 입금하는 메서드
	public void deposit(int money) {
		
		// Lock 객체의 lock() 메서드가 동기화 시작이고, unlock() 메서드가 동기화의 끝을 나타낸다.
		// lock() 메서드로 동기화를 설정한 곳에서는 반드시 unlock()으로 해제해 주어야 한다.
		lock.lock();  // lock 설정
		balance += money;
		lock.unlock(); // lock 해제
		// lock()과 unlock() 사이가 동기화 처리됨
	}
	
	// 출금하는 메서드
	public boolean withdraw(int money) {
		
		boolean result = false;
		
		// try~catch 블럭을 사용할 경우에는 
		// unlock()메서드 호출은 finally 블럭에서 하도록 한다.
		// 그렇지 않으면 예외 발생 등 여러 이유로 unlock이 호출되지 않을 수도 있다
		try {
			// lock 설정하기
			lock.lock();
			System.out.println(Thread.currentThread().getName() + "lock 설정(획득) 완료...");
			
			if(balance >= money) {
				for(int i = 0; i <= 1000000000; i++) {}
				balance -= money;
				System.out.println(Thread.currentThread().getName() + "메서드 안에서 balance = " + getBalance());
				
				result = true;
			}
			
		}catch(Exception ex){
			ex.printStackTrace();
		}finally {
			lock.unlock(); // lock 해제
			System.out.println(Thread.currentThread().getName() + "lock 해제(반납) 완료...");
		}
		
		return result;
		
	}
	
}

// 은행 업무를 처리하는 Thread
class BankThread2 extends Thread {
	
	private LockAccount lAcc;
	
	public BankThread2(LockAccount lAcc) {
		this.lAcc = lAcc;
	}
	
	@Override
	public void run() {
		boolean result = false;
		
		result = lAcc.withdraw(6000);
		
		System.out.println(Thread.currentThread().getName() + " 스레드 안에서 result = " + result
				+ ", balance = " + lAcc.getBalance());

	}
	
}