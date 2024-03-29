package poly.service;

import java.util.List;

import poly.dto.MelonDTO;

public interface IMelonServiceUsingDTO {

	/**
	 * 멜론 노래 리스트 저장하기
	 */
	public int collectMelonSong() throws Exception;

	/**
	 * 오늘 수집된 멜론 노래리스트 가져오기
	 */
	public List<MelonDTO> getSongList() throws Exception;

	/**
	 * 멜론 가수별 노래 수 가져오기
	 */
	public List<MelonDTO> getSingerSongCnt() throws Exception;

}


