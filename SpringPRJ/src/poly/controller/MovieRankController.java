package poly.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import poly.dto.MovieDTO;
import poly.service.IMovieRankService;
import poly.util.CmmUtil;
import poly.util.DateUtil;

/*
 * Controller 선언해야만 Spring 프레임워크에서 Controller인지 인식 가능
 * 자바 서블릿 역할 수행
 * */
@Controller
public class MovieRankController {
	private Logger log = Logger.getLogger(this.getClass());

	/*
	 * 비즈니스 로직(중요 로직을 수행하기 위해 사용되는 서비스를 메모리에 적재(싱글톤패턴 적용됨)
	 */
	@Resource(name = "MovieRankService")
	private IMovieRankService movieRankService;

	/**
	 * 음성 명령 첫화면
	 */
	@RequestMapping(value = "rank/index")
	public String index(HttpServletRequest request, HttpServletResponse response) throws Exception {

		log.info(this.getClass().getName() + ".index Start!");

		log.info(this.getClass().getName() + ".index End!");

		return "/rank/index";
	}

	/**
	 * CGV 영화 순위 가져오기
	 */
	@RequestMapping(value = "rank/movie")
	@ResponseBody
	public List<MovieDTO> rank(HttpServletRequest request, HttpServletResponse response) throws Exception {

		log.info(this.getClass().getName() + ".rank start!");

		List<MovieDTO> rList = null;

		// 음성 명령
		String send_msg = CmmUtil.nvl(request.getParameter("send_msg"));

		// 영화, 순위와 비슷한 단어가 존재하면 CGV 영화 순위 가져오기 수행
		if (((send_msg.indexOf("영화") > -1) || (send_msg.indexOf("영하") > -1) || (send_msg.indexOf("연하") > -1)
				|| (send_msg.indexOf("연화") > -1)) && ((send_msg.indexOf("순위") > -1) || (send_msg.indexOf("순이") > -1))) {

			MovieDTO pDTO = new MovieDTO();

			pDTO.setRank_chkeck_time(DateUtil.getDateTime("yyyyMMdd"));

			rList = movieRankService.getMovieRank(pDTO);

			if (rList == null) {
				rList = new ArrayList<MovieDTO>();

			}
		}

		log.info(this.getClass().getName() + ".rank end!");

		return rList;
	}

}
