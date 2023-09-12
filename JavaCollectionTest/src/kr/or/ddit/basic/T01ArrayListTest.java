package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.List;

public class T01ArrayListTest {

   public static void main(String[] args) {

      List list1 = new ArrayList();

      list1.add("aaa");
      list1.add("bbb");
      list1.add(111);
      list1.add('k');
      list1.add(true);
      list1.add(12.34);

      System.out.println("size => " + list1.size());
      System.out.println("list1 => " + list1);

      System.out.println("1번째 자료 : " + list1.get(0));

      list1.add(0, "zzz");
      System.out.println("list1 => " + list1);

      String temp = (String) list1.set(0, "YYY");
      System.out.println("temp :" + temp);
      System.out.println("list1 :" + list1);

      list1.remove(0);
      System.out.println("삭제 후 :" + list1);

      list1.remove("bbb");
      System.out.println("bbb 삭제 후 :" + list1);
      System.out.println("========================");

//      list1.remove(1);   아니면 인덱스 번호로 삭제
      list1.remove(new Integer(111));  //list안의 내용은 객체화 되어있기 떄문에 객체화시켜서 삭제!
      list1.remove("aaa");
      System.out.println(list1);
      
      List<String> list2 = new ArrayList<String>();
      list2.add("AAA");
      list2.add("BBB");
      list2.add("CCC");
      list2.add("DDD");
      list2.add("EEE");

      for (String str : list2) {
         System.out.print(str);
      }   
      System.out.println("-----------------------");

//      contatins(비교 객체) => 리스트에 비교객체 가 있으면 true
//                      그렇지 않으면 false
      System.out.println(list2.contains("DDD"));
      System.out.println(list2.contains("ZZZ"));      
//   
//      indexOf(비교객체) => 리스트에서 기교객체 를 찾아 비교객체가 있는 
//                  index값을 반환한다. 없으면 '-1' 반환
   System.out.println("DDD의 index값 : " + list2.indexOf("DDD"));
   System.out.println("ZZZ의 index값 : " + list2.indexOf("ZZZ"));
   System.out.println("---------------------------------------");
   

   for (int i = 0; i < list2.size()+4; i++) {
      list2.remove(0);
      System.out.println(list2);
   }
   
   System.out.println("삭제 후 리스트2의 크기: " + list2.size());
   System.out.println(list2);
   
   for (int i = 0; i < list2.size(); i++) {
      list2.remove(i);
   }
   
   System.out.println("삭제 후 리스트2의 크기: " + list2.size());
   System.out.println(list2);
   
   
   
   }
   
}
   