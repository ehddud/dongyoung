package homework.board.vo;

import java.text.SimpleDateFormat;
import java.util.Date;

public class T01Homework1BoardVO implements Comparable<T01Homework1BoardVO>{

	private String no;
	private String title;
	private String writer;
	private String cont;
	private Date date;
	
	public T01Homework1BoardVO() {};
	public T01Homework1BoardVO( String title, String writer, String cont) {
		super();
		this.title = title;
		this.writer = writer;
		this.cont = cont;
	}


	public String getNo() {
		return no;
	}


	public void setNo(String no) {
		this.no = no;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getWriter() {
		return writer;
	}


	public void setWriter(String writer) {
		this.writer = writer;
	}


	public String getCont() {
		return cont;
	}


	public void setCont(String cont) {
		this.cont = cont;
	}


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}

	public String getRegDtDisplay() {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		return sdf.format(this.date);
		
	}


	@Override
	public int compareTo(T01Homework1BoardVO o) {

		return this.getNo().compareTo(o.getNo());
		
	}
	
	
	
}
