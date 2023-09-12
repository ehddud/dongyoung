package homework;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Zexample {
	public static void main(String[] args) {

		BufferedReader br = null;
		BufferedWriter pw = null;
		try {
			br = new BufferedReader(new FileReader("d:/D_Other/out.txt"));
			pw = new BufferedWriter(new FileWriter("d:/D_Other/test.txt"));
			
			String data = null;
			
			while((data = br.readLine()) != null) {
				pw.write(data);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				br.close();
				pw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}
}
