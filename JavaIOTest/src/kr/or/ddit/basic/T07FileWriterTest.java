package kr.or.ddit.basic;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class T07FileWriterTest {

	public static void main(String[] args) {
		// 사용자가 입력할 내용을 그대로 파일에 저장하기
		
		
		// 콘솔(표준 입력장치)과 연결된 입력용 문자 스트림 생성
		// InputStreamReader => 바이트기반 스트림을 문자기반 스트림으로 변환해주는 보조스트림
		// 보조스트림 => 기본스트림을 도와주는 역할을 하는 스트림, 단독으로 IO를 할 수 없음
		
		InputStreamReader isr = new InputStreamReader(System.in);
		
		FileWriter fw = null; // 파일 출력용 문자기반 스트림
		
		try {
			
			fw = new FileWriter("d:/D_Other/testChar.txt");
			
			int data = 0;
			
			System.out.println("입력 후 Ctrl + z를 눌러주세요. ");
			System.out.print("아무거나 입력하세요 : ");
			
			// 콘솔에서 입력할 때 입력을 끝내려면(isr.read값을 -1로 만들려면) 
			// Ctrl + z 키를 누르면 된다
			while( (data = isr.read()) != -1 ) {
				fw.write(data); // 콘솔에서 입력받은 값을 파일에 저장하기
				
			}
			
			System.out.println("작업 끝...");
			
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				fw.close();
				isr.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
}
