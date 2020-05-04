package poly.dto;

public class AccStatDTO {

	// json 결과를 받기 위한 호출하는 URL
	private String url;

	// json 결과에 정의된 항목들 시작
	private String reqYYYYMM;
	private String reqAcode;
	private String recordCnt;

	private String yyyymm;
	private String a_code;
	private String a_name;
	private String stat_a;
	private String stat_b;
	private String reg_id;
	private String ret_dt;
	private String chg_id;
	private String chg_dt;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getReqYYYYMM() {
		return reqYYYYMM;
	}

	public void setReqYYYYMM(String reqYYYYMM) {
		this.reqYYYYMM = reqYYYYMM;
	}

	public String getReqAcode() {
		return reqAcode;
	}

	public void setReqAcode(String reqAcode) {
		this.reqAcode = reqAcode;
	}

	public String getRecordCnt() {
		return recordCnt;
	}

	public void setRecordCnt(String recordCnt) {
		this.recordCnt = recordCnt;
	}

	public String getYyyymm() {
		return yyyymm;
	}

	public void setYyyymm(String yyyymm) {
		this.yyyymm = yyyymm;
	}

	public String getA_code() {
		return a_code;
	}

	public void setA_code(String a_code) {
		this.a_code = a_code;
	}

	public String getA_name() {
		return a_name;
	}

	public void setA_name(String a_name) {
		this.a_name = a_name;
	}

	public String getStat_a() {
		return stat_a;
	}

	public void setStat_a(String stat_a) {
		this.stat_a = stat_a;
	}

	public String getStat_b() {
		return stat_b;
	}

	public void setStat_b(String stat_b) {
		this.stat_b = stat_b;
	}

	public String getReg_id() {
		return reg_id;
	}

	public void setReg_id(String reg_id) {
		this.reg_id = reg_id;
	}

	public String getRet_dt() {
		return ret_dt;
	}

	public void setRet_dt(String ret_dt) {
		this.ret_dt = ret_dt;
	}

	public String getChg_id() {
		return chg_id;
	}

	public void setChg_id(String chg_id) {
		this.chg_id = chg_id;
	}

	public String getChg_dt() {
		return chg_dt;
	}

	public void setChg_dt(String chg_dt) {
		this.chg_dt = chg_dt;
	}

}
