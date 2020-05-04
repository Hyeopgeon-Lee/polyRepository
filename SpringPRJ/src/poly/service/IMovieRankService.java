package poly.service;

import java.util.List;

import poly.dto.MovieDTO;

public interface IMovieRankService {

	/**
	 * CGV 영화 순위 정보가져오기
	 * 
	 * @param pDTO 영화 정보
	 */
	List<MovieDTO> getMovieRank(MovieDTO pDTO) throws Exception;

}

