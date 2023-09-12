package Homework;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

public class WhichLoopIsFaster {

	public static ArrayList<Integer> test_data = InputTestData();

	public static void main(String[] args) {

		WhichLoopIsFaster evaluate = new WhichLoopIsFaster();

		For();
		While();
		Iterator();

	}

	public static void For() {
		System.out.println("\n--- For ---\n");
		long startTime = new Date().getTime();
		int i;
		for (i = 0; i < test_data.size(); i++) {
			int v = test_data.get(i);
		}
		long endTime = new Date().getTime();
		long elapsed = endTime - startTime;
		System.out.println("For :: 걸린 시간은 " + elapsed + " 밀리초");
	}

	public static void While() {
		System.out.println("\n--- While Loop ---\n");
		long startTime = new Date().getTime();

		int i = 0;
		while (i < test_data.size()) {
			int v = test_data.get(i);
			i++;
		}
		long endTime = new Date().getTime();
		long elapsed = endTime - startTime;
		System.out.println("While :: 걸린 시간은 " + elapsed + " 밀리초");
	}

	public static void Iterator() {
		System.out.println("\n--- Iterator Loop ---\n");
		long startTime = new Date().getTime();

		Iterator<Integer> itr;
		for (itr = test_data.iterator(); itr.hasNext();) {
			test_data.get(itr.next());
		}

		long endTime = new Date().getTime();
		long elapsed = endTime - startTime;
		System.out.println("Iterator :: 걸린 시간은 " + elapsed + " 밀리초");
	}

	public static ArrayList<Integer> InputTestData() {
		ArrayList<Integer> arr = new ArrayList<Integer>();
		for (int i = 0; i < 15000000; i++) {
			arr.add(i);
		}
		return arr;
	}

}
