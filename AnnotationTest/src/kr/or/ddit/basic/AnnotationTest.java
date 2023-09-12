package kr.or.ddit.basic;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class AnnotationTest {

	public static void main(String[] args) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException, NoSuchMethodException, SecurityException {

		System.out.println("static 변수값 : " + PrintAnnotation.id);
		
		Method[] methodArr = Service.class.getDeclaredMethods();
		
		for(Method m : methodArr) {
			
			System.out.println(m.getName());
			
			Annotation[] annos = m.getAnnotations();
			
			for(Annotation anno : annos) {
				if(anno.annotationType().getSimpleName().equals("PrintAnnotation")) {
					PrintAnnotation printAnno = (PrintAnnotation) anno;
					
					// count값 만큼 value값 출력하기
					for(int i = 0; i < printAnno.count(); i++) {
						System.out.print(printAnno.value());
					}
				}
			}
			
			System.out.println();
			
			//m.invoke(new Service());
			Class<?> clazz = Service.class;
			Service service = (Service) clazz.newInstance();
			m.invoke(service);
			
		}
		
		Class<Service> clazz = Service.class;
		String b = (String) clazz.getMethod("method5", String.class).invoke(clazz, "개");
		System.out.println(b);
		
	}

}
