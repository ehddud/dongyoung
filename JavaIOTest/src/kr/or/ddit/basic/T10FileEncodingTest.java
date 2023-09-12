package kr.or.ddit.basic;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class T10FileEncodingTest {

	/*
	 * 인코딩 방식에 대하여..
	 * 한글 인코딩 방식은 크게 UTF-8 과 EUC-KR 방식 두가지로 나누어 볼 수 있다.
	 * 원래 한글윈도우는 CP949방식을 사용하는데, 윈도우를 개발한 마이크로소프트에서
	 * EUC-KR 방식에서 확장하였기 때문에 MS949 라고도 부른다.
	 * 한글 윈도우의 메모장에서 말하는 ANSI 인코딩이란 CP949(Code Page 949)를 말한다.
	 * -MS949 => 한글 윈도우의 기본 한글 인코딩 방식(ANSI 계열)
	 * -UTF-8 => 유니코드 UTF-8 인코딩 방식(영문자 및 숫자 : 1byte, 한글: 3byte)
	 * -US-ASCII => 영문 전용 인코딩 방식
	 * 
	 * ANSI는 영어를 표기하기 위해 만든 코드로 규격 자체에 한글이 없었다가 나중에 여기에
	 * EUC-KR, CP949 라는 식으로 한글이 포함되어 만들어졌다.
	 * 
	 * 참고)
	 * ASCII => extended ASCII(ISO 8859-1) => 조합형, 완성형
	 * 		 => 윈도우계열 : CP949		=> 유니코드 (UTF-8)
	 * 		 => 유닉스계열 : EUC-KR	
	 */
	
	public static void main(String[] args) {
		
		FileInputStream fis = null;
		InputStreamReader isr = null;	// 보조스트림
		
		try {
			
//			fis = new FileInputStream("d:/D_Other/test_ansi.txt");
			fis = new FileInputStream("d:/D_Other/test_utf8.txt");
			
			// InputStreamReader는 파일의 인코딩 방식을 지정할 수 있다.
			// 형식) new InputStreamReader(바이트기반스트림, 인코딩 방식);
//			isr = new InputStreamReader(fis, "CP949");
			isr = new InputStreamReader(fis, "utf-8");
			
			int data = 0;
			
			while( (data = isr.read()) != -1 ) {
				System.out.print((char) data);
			}
			
			System.out.println();
			System.out.println("출력 끝!");
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				isr.close(); // 보조스트림만 닫아도 된다.
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
}
