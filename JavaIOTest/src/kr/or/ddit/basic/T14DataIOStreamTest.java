package kr.or.ddit.basic;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/*
 * 기본타입 입출력 보조 스트림
 */
public class T14DataIOStreamTest {
	public static void main(String[] args) {
		
		FileOutputStream fos = null;
		DataOutputStream dos = null;
		
		
		try {
			
			fos = new FileOutputStream("d:/D_Other/test.dat");
			dos = new DataOutputStream(fos);
			
			dos.writeUTF("홍길동");	// 문자열 데이터 출력
			dos.writeInt(17);		// int형 데이터 출력
			dos.writeFloat(3.14f);	// float형 데이터 출력
			dos.writeDouble(3.14);	// Double형 데이터 출력
			dos.writeBoolean(true);	// boolean형 데이터 출력
			
			System.out.println("데이터 출력 완료...");
	
			
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				dos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		/////////////////////////////////////////
		
		FileInputStream fis = null;
		DataInputStream dis = null;
		
		try {
			File file = new File("d:/D_Other/test.dat");
			fis = new FileInputStream(file);
			dis = new DataInputStream(fis);
			
			System.out.println("문자열 자료 : " + dis.readUTF());
			System.out.println("정수형 자료 : " + dis.readInt());
			System.out.println("실수형(Float)자료 : " + dis.readFloat());
			System.out.println("실수형(Double)자료 : " + dis.readDouble());
			System.out.println("논리형 자료 : " + dis.readBoolean());
			
			System.out.println("읽기 작업 완료...");
			
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			
		}
		
	}
}
