package poly.dto;

public class OcrDTO {

	private String filePath; // 저장된 이미지 파일의 파일 저장 경로
	private String fileName; // 저장된 이미지 파일 이름
	private String textFromImage; // 저장된 이미지로부터 읽은 글씨

	private String org_file_name; // 원래 파일 이름
	private String ext; // 확장자
	private String reg_id; // 등록자
	private String chg_id; // 수정자

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getTextFromImage() {
		return textFromImage;
	}

	public void setTextFromImage(String textFromImage) {
		this.textFromImage = textFromImage;
	}

	public String getOrg_file_name() {
		return org_file_name;
	}

	public String getReg_id() {
		return reg_id;
	}

	public void setReg_id(String reg_id) {
		this.reg_id = reg_id;
	}

	public String getChg_id() {
		return chg_id;
	}

	public void setChg_id(String chg_id) {
		this.chg_id = chg_id;
	}

	public void setOrg_file_name(String org_file_name) {
		this.org_file_name = org_file_name;
	}

	public String getExt() {
		return ext;
	}

	public void setExt(String ext) {
		this.ext = ext;
	}

}
