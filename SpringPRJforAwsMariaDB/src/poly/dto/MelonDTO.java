package poly.dto;

public class MelonDTO {

	private String collect_time;
	private String seq;
	private String song;
	private String singer;

	private int singerCnt;

	public String getCollect_time() {
		return collect_time;
	}

	public void setCollect_time(String collect_time) {
		this.collect_time = collect_time;
	}

	public String getSeq() {
		return seq;
	}

	public void setSeq(String seq) {
		this.seq = seq;
	}

	public String getSong() {
		return song;
	}

	public void setSong(String song) {
		this.song = song;
	}

	public String getSinger() {
		return singer;
	}

	public void setSinger(String singer) {
		this.singer = singer;
	}

	public int getSingerCnt() {
		return singerCnt;
	}

	public void setSingerCnt(int singerCnt) {
		this.singerCnt = singerCnt;
	}

}
