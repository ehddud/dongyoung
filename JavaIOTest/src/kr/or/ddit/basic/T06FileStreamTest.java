package kr.or.ddit.basic;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/*
 * 파일 출력 예제
 */

public class T06FileStreamTest {

	public static void main(String[] args) {
		
		FileOutputStream fos = null;
		
		try {
			
//			File file = new File("d:/D_Other/out.txt");
//			fos = new FileOutputStream(file); 아래와 동일
			// 경로를 주지 않으면 프로젝트 디렉토리에 생성
			fos = new FileOutputStream("d:/D_Other/out.txt");
			
			for(char ch = 'a'; ch <= 'z' ; ch++) {
				fos.write(ch);
			}
			
			System.out.println("파일에 쓰기 작업 완료!!!");
			
		}catch(IOException e) {
			e.printStackTrace();
		}finally {
			try {
				fos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		////////////////////////////////////////////////////////////
		
		FileInputStream fis = null;
		
		try {
			fis = new FileInputStream("d:/D_Other/out.txt");
			
			int data = 0;
			
			while( (data = fis.read()) != -1 ) {
				System.out.print((char)data);
			}
			
		}catch(IOException e) {
			e.printStackTrace();
		}finally {
			try {
				fis.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}
	
}
