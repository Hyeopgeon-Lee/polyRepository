package poly.persistance.mongo;

import java.util.List;

import poly.dto.MelonDTO;

public interface IMelonMapperUsingDTO {

	/**
	 * 멜론 노래 리스트 저장하기
	 * 
	 * @param pList 저장될 정보
	 * @param colNm 저장할 컬렉션 이름
	 * @return 저장 결과
	 */
	public int insertSong(List<MelonDTO> pList, String colNm) throws Exception;

	/**
	 * 오늘 수집된 멜론 노래리스트 가져오기
	 * 
	 * @param colNm 조회할 컬렉션 이름
	 * @return 노래 리스트
	 */
	public List<MelonDTO> getSongList(String colNm) throws Exception;

	/**
	 * 가수별 수집된 노래의 수 가져오기
	 * 
	 * @param colNm 조회할 컬렉션 이름
	 * @return 노래 리스트
	 */
	public List<MelonDTO> getSingerSongCnt(String colNm) throws Exception;

}


