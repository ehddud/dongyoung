package kr.or.ddit.basic;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
/*
 * 성능향상을 위한 보조스트림 예제
 * (바이트 기반의 Buffered Stream 사용 예제)
 */
public class T13BufferedIOTest {
	public static void main(String[] args) {
		
		FileReader fr = null;
		BufferedReader br = null;
		
		try {
			// 상대경로 (./ => 현재디렉토리) => project folder가 기본 위치
			fr = new FileReader("./src/kr/or/ddit/basic/T12BufferedIOTest.java");
			br = new BufferedReader(fr);

			String tmpStr = "";

			while((tmpStr = br.readLine()) != null ) {
				
				System.out.println(tmpStr);
				
			}
			
		}catch(IOException e) {
			e.printStackTrace();
		}finally {
			try {
				fr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
				
				
				
	}
}
