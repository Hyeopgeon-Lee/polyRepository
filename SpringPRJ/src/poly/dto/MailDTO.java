package poly.dto;

public class MailDTO {

	String toMail; // 받는 사람
	String title; // 보내는 메일 제목
	String contents; // 보내는 메일 내용

	public String getToMail() {
		return toMail;
	}

	public void setToMail(String toMail) {
		this.toMail = toMail;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

}


