package poly.dto;

public class MelonDTO {

	private String collect_time; // 수집 시간
	private String rank; // 순위
	private String song; // 노래제목
	private String singer; // 가수
	private String album; // 엘범

	public String getCollect_time() {
		return collect_time;
	}

	public void setCollect_time(String collect_time) {
		this.collect_time = collect_time;
	}

	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
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

	public String getAlbum() {
		return album;
	}

	public void setAlbum(String album) {
		this.album = album;
	}

}

