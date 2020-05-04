package poly.persistance.mapper;

import java.util.List;

import config.Mapper;
import poly.dto.MovieDTO;

@Mapper("MovieMapper")
public interface IMovieMapper {

	// 수집된 내용 DB에 등록
	int InsertMovieInfo(MovieDTO pDTO) throws Exception;

	// 수집된 데이터 조회
	List<MovieDTO> getMovieInfo(MovieDTO pDTO) throws Exception;

}

