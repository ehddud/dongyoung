package kr.or.ddit.basic;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/*
 * 성능향상을 위한 보조스트림 예제
 * (바이트 기반의 Buffered Stream 사용 예제)
 */

public class T12BufferedIOTest {

	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		// 파일에 쓸때
		FileOutputStream fos = null;
		BufferedOutputStream bos = null; // 보조스트림
		
		try {
			fos = new FileOutputStream("d:/D_Other/bufferTest.txt");
			
			// 버퍼의 크기를 지정하지 않으면 기본적으로 8192byte(8kb)로 설정된다.
			bos = new BufferedOutputStream(fos); // buffer크기 5byte로 명시적 지정
			
			for(int i = 0; i < 10000000; i++) {
				bos.write(i);
			}
			
			System.out.println("작업 종료");
			
		}catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				bos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		long endTime = System.currentTimeMillis();
		System.out.println(endTime - startTime);
	}
	
}
