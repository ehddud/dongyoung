package kr.or.ddit.basic;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

/*
 * 프린터 기능 제공 보조 스트림
 */

public class T15PrintStreamTest {
	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
		
		FileOutputStream fos = new FileOutputStream("d:/D_Other/print.txt");
		
		/*
		 * PrintStream은 모든 자료형을 출력할 수 있는 기능을 제공하는 보조스트림이다.
		 */
		
		PrintStream out = new PrintStream(fos);
		out.print("안녕하세요. PrintStream입니다.\n");
		out.println("안녕하세요. PrintStream입니다2.");
		out.println("안녕하세요. PrintStream입니다3.");
		out.println(out); // 객체 출력
		out.println(3.14);

		out.close();
		
		System.out.println("출력 완료...");
		
		/*
		 * PrintStream은 데이터를 문자열로 출력하는 기능을 수행함
		 * 향상된 기능의 PrintWriter가 추가되었지만 계속 사용됨.
		 * 
		 * PrintWriter가 PrintStream보다 다양한 인코딩 처리된 문자로
		 * 표현하는데 적합하다
		 */
		
		FileOutputStream fos2 = new FileOutputStream("d:/D_Other/print2.txt");
		PrintWriter pw = new PrintWriter(new OutputStreamWriter(fos2, "CP949"));
		
		pw.print("안녕하세요. PrintWriter입니다. \n");
		pw.println("안녕하세요. PrintWriter입니다2.");
		pw.println("안녕하세요. PrintWriter입니다3.");
		pw.println(fos2);
		pw.println(pw);
		pw.println(3.14);
		
		pw.close();
		
		System.out.println("출력완료2...");
		
	}
}
