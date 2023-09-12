package kr.or.ddit.basic;

import java.io.File;
import java.io.IOException;

// IO 인풋 아웃풋 = 입출력
// 파일을 핸들링할때 필요한 객체를 만든다 = 파일 클래스
public class T01FileTest {
   public static void main(String[] args) throws IOException {
   /** File 객체 만들기 연습**/
   
   /** 1. new File(String 파일 또는 경로명) 주로 많이 쓰이는 방법**/
   // => 디렉토리와 디렉토리 사이 또는 디렉토리와 파일명 사이의 구분 문자는
   //      '\'를 사용하거나 '/'를 사용할 수 있다.
   //     \\역슬래시 2개 = / 슬래시 1개 똑같은 의미임
      
   File file = new File("d:/D_Other/test.txt");
   System.out.println("파일명 : " + file.getName());
   System.out.println("파일여부 : " + file.isFile());
   System.out.println("디렉토리(폴더)여부 : " + file.isDirectory());
   System.out.println("--------------------------");
   
   File file2 = new File("d:/D_Other");
   System.out.print(file2.getName() + "은 ");
   if(file2.isFile()) {
      System.out.println("파일");
   }else if(file2.isDirectory()) {
      System.out.println("디렉토리(폴더)");
   }
   System.out.println("--------------------------");
   
   /** 2. new File(File parent, String child) **/
   // => 'parent' 디렉토리 안에 있는 'child'파일 또는 디렉토리를 갖는다.
   File file3 = new File(file2, "test.txt");
   System.out.println(file3.createNewFile()); 
   System.out.println(file3.getName() + "의 용량(크기) : " +
   file3.length());
   
   /** 3. new File(String parent, String child) **/
   File file4 = new File(".\\D_Other\\test\\..", "test.txt");
   System.out.println("절대경로 : " + file4.getAbsolutePath());
   System.out.println("경로 : " + file4.getPath());
   System.out.println("표준경로 : " + file4.getCanonicalPath());
   System.out.println("-----------------------------------------");
   
   file4 = new File("C:\\JavaIOTest\\src\\img\\hello.jpg");
   
   file4 = new File(".\\src\\img\\hello.jpg");
   
    /**디렉토리(폴더) 만들기**/
    /* 
    1. mkdir() => File객체의 경로 중 "1개, 마지막 위치의 디렉토리"를 만든다.
                   마지막 위치라서 미리 중간의 경로가 만들어져 있어야 한다.
    2. mkdirs() => 만약 중간의 경로가 없으면, 중간의 경로를(복수s) 새롭게 만든 후
                이후 마지막 위치의 디렉토리를 만들어 준다.
       => 위 두 메서드 모두 만들기를 성공하면 return값이 true, 
                          실패하면 false를 반환
    */
   File file5 = new File("d:/D_Other/연습용");
   if(file5.mkdir()) {
      System.out.println(file5.getName()+ " 만들기 성공!");
   }else {
      System.out.println(file5.getName()+ " 만들기 실패!!!");
   }
   
   File file6 = new File("d:/D_Other/test/java/src");
   if(file6.mkdirs()) {
      System.out.println(file6.getName()+ " 만들기 성공!");
   }else {
      System.out.println(file6.getName()+ " 만들기 실패!!!");
   }
}
}
// 절대 경로 = "/" 슬래시 D(Disk)드라이브(변하지 않는 경로, 405호 왼쪽 벽 3번째)
//   => getAbsolutePath

// 상대 경로 = "." 쩜 하나 내자리(그때 그때 현재 내 위치에 따라 달라지는 경로, 아무데나)
//    => getPath