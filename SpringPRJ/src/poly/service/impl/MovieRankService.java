package poly.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import poly.dto.MovieDTO;
import poly.persistance.mapper.IMovieMapper;
import poly.persistance.redis.IRedisMovieMapper;
import poly.service.IMovieRankService;
import poly.service.IMovieService;
import poly.util.DateUtil;

@Service("MovieRankService")
public class MovieRankService implements IMovieRankService {

	@Resource(name = "MovieService")
	private IMovieService movieService; // 크롤링을 위한 service

	@Resource(name = "MovieMapper")
	private IMovieMapper oracleMovieMapper; // 오라클 데이터 제어용 Mapper

	@Resource(name = "RedisMovieMapper")
	private IRedisMovieMapper redisMovieMapper; // redisDB 데이터 제어용 Mapper

	// 로그 파일 생성 및 로그 출력을 위한 log4j 프레임워크의 자바 객체
	private Logger log = Logger.getLogger(this.getClass());

	@Override
	public List<MovieDTO> getMovieRank(MovieDTO pDTO) throws Exception {

		log.info(this.getClass().getName() + ".getMovieRank Start!");

		List<MovieDTO> rList = null; // redis와 오라클로부터 조회된 데이터가 저장될 객체

		// redis DB에 저장될 키
		String key = "CGV_RANK_" + DateUtil.getDateTime("yyyyMMdd");

		// 캐시로 활용될 Redis에 CGV순위 정보가 존재하는지 체크함
		if (redisMovieMapper.getExists(key)) { // redisDB에 데이터가 있음

			// 데이터 가져오기
			rList = redisMovieMapper.getMovieRank(key);

			// Null 에러 방지용
			if (rList == null) {
				rList = new ArrayList<MovieDTO>();

			}

			// 최근 호출된 데이터이기 때문에 저장유효시간(TTL) 연장하기
			redisMovieMapper.setTimeOutHour(key, 1);

		} else { // redisDB에 데이터가 없음

			// 오라클DB에서 데이터 가져오기
			rList = oracleMovieMapper.getMovieInfo(pDTO);

			// Null 에러 방지용
			if (rList == null) {
				rList = new ArrayList<MovieDTO>();

			}

			// 오라클에도 데이터가 존재하지 않으면 크롤링 시작
			if (rList.size() == 0) {

				// CGV 웹사이트에서 크롤링 시작
				movieService.getMovieInfoFromWEB();

				// 크롤링을 통해 오라클 DB에 저장된 영화 순위정보를 다시 조회하기
				rList = oracleMovieMapper.getMovieInfo(pDTO);

				// Null 에러 방지용
				if (rList == null) {
					rList = new ArrayList<MovieDTO>();

				}

			}

			// 데이터가 존재한다면, Redis에 저장함
			if (rList.size() > 0) {
				redisMovieMapper.setMovieRank(key, rList);

				// 최근 호출된 데이터이기 때문에 저장유효시간(TTL) 연장하기
				redisMovieMapper.setTimeOutHour(key, 1);

			}

		}

		log.info(this.getClass().getName() + ".getMovieRank End!");

		return rList;
	}

}
