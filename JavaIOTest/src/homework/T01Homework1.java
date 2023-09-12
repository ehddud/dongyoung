package homework;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class T01Homework1 {

	public static void main(String[] args) {

		long startTime = System.currentTimeMillis();
		
		
		try(
				// 파일을 읽어 들여옴(inputStream)
				FileInputStream fis = new FileInputStream("d:/D_Other/Tulips.jpg");
				// 파일을 새로 생성하거나 덮어쓰거나 파일의 내용에 이어붙임
				FileOutputStream fos = new FileOutputStream("d:/D_Other/복사본_Tulips.jpg");
				) {
			
			int data = 0;
			// 이렇게 하면 성능이 올라감
			byte[] buffer = new byte[512];
			while((data = fis.read(buffer)) != -1) {
				fos.write(buffer, 0, data);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		long endTime = System.currentTimeMillis();
		
		System.out.println(endTime - startTime+"ms");
		
//		File originFile = new File("d:/D_Other/Tulips.jpg");
//		File copyFile = new File("d:/D_Other/복사본_Tulips.jpg");
//		
//		try {
//			Files.copy(originFile.toPath(), copyFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}

	}

}
