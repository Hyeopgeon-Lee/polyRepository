package poly.service;

import java.util.List;

import poly.dto.MelonDTO;
import poly.dto.MelonRankDTO;
import poly.dto.MelonSingerDTO;
import poly.dto.MelonSongDTO;

public interface IMelonService {

	/**
	 * 멜론 Top100 순위 수집하기
	 */
	public int collectMelonRank() throws Exception;

	/**
	 * MongoDB 멜론 데이터 가져오기
	 */
	public List<MelonDTO> getRank() throws Exception;

	/**
	 * MongoDB 가수별 멜론 랭킹에 많이 등록된 순서대로 가져오기
	 */
	public List<MelonSingerDTO> getRankForSinger() throws Exception;

	/**
	 * MongoDB 노래별 순위 변동 정보
	 * 
	 */
	public List<MelonRankDTO> getCompareRank() throws Exception;
	
	/**
	 * MongoDB 가수의 노래 데이터 가져오기
	 * 
	 */
	public List<MelonSongDTO> getSongForSinger() throws Exception;
	
}

