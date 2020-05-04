package poly.persistance.redis;

import java.util.List;

import poly.dto.MovieDTO;

public interface IRedisMovieMapper {

	/**
	 * 영화 순위 정보가 존재하는 체크
	 * 
	 * @param key RedisDB에 저장된 키
	 */
	public boolean getExists(String key) throws Exception;

	/**
	 * 영화 순위 정보 가져오기
	 * 
	 * @param key RedisDB에 저장된 키
	 */
	public List<MovieDTO> getMovieRank(String key) throws Exception;

	/**
	 * 영화 순위 정보 저장하기
	 * 
	 * @param key   RedisDB에 저장될 키
	 * @param pList RedisDB에 저장될 데이터들
	 */
	public int setMovieRank(String key, List<MovieDTO> pList) throws Exception;

	/**
	 * 영화 순위 정보 저장될 시간(TTL) 설정
	 * 
	 * @param key RedisDB에 저장된 키
	 */
	public boolean setTimeOutHour(String key, int hours) throws Exception;

}

