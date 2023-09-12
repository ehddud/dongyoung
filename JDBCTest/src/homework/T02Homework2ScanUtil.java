package homework;

import java.util.Scanner;

public class T02Homework2ScanUtil {
	 static Scanner sc = new Scanner(System.in);
	   
	   public static String nextLine() {
	      return sc.nextLine();
	   }
	   
	   public static int nextInt() {
	      
	      while(true) {
	         try {
	            int result = Integer.parseInt(sc.nextLine());
	            return result;
	         } catch (NumberFormatException e) {
	            System.out.println("숫자를 입력해주세요.");
	        System.out.print("→ ");
	         } 
	      }
	      
	   }
	   
}
