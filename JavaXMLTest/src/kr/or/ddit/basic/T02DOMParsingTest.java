package kr.or.ddit.basic;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * XML문서 읽어오기
 * @author PC-06
 *
 */
public class T02DOMParsingTest {

	public void parse() throws ParserConfigurationException, SAXException, IOException {
		
		// XML 문서를 생성하기 위한 DocumentBuilder 객체 생성하기
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		
		DocumentBuilder builder  = dbf.newDocumentBuilder();
		
		// Document 객체 생성하기
		Document document = builder.parse(new File("./src/kr/or/ddit/basic/new_book.xml"));
		
		// DOM Document 객체로부터 root 엘리먼트 객체 가져오기
		Element root = document.getDocumentElement();
		System.out.println("루트 엘리먼트 태그명 : " + root.getTagName());
		
		// 하위 엘리먼트 접근하기
		NodeList bookNodeList = root.getElementsByTagName("book");
		Node firstBookNode = bookNodeList.item(0);
		Element firstBookElement = (Element) firstBookNode;
		
		//속성 접근하기
		System.out.println("isbn => " + firstBookElement.getAttribute("isbn"));
		System.out.println("kind => " + firstBookElement.getAttribute("kind"));
		
		// 전체 출력하기
		System.out.println("--------------------------------");
		System.out.printf("%8s %8s %12s %10s %8s\n", 
				"ISBN", "분류", "제목", "저자", "가격");
		System.out.println("--------------------------------");
		
		for(int i = 0; i < bookNodeList.getLength(); i++) {
			
			Element bookEl = (Element) bookNodeList.item(i);
			
			String isbn = bookEl.getAttribute("isbn");
			String kind = bookEl.getAttribute("kind");
			
			String title = bookEl.getElementsByTagName("title")
							.item(0).getTextContent();
			String author = bookEl.getElementsByTagName("author")
							.item(0).getTextContent();
			String price = bookEl.getElementsByTagName("price")
							.item(0).getTextContent();

			System.out.printf("%8s %8s %12s %10s %8s\n", 
					isbn, kind, title, author, price);
			
			
			
			
		}
		
		
	}
	
	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
		new T02DOMParsingTest().parse();
	}
	
}
