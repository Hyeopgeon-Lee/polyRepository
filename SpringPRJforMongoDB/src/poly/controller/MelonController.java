package poly.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import poly.dto.MelonDTO;
import poly.service.IMelonService;
import poly.service.IMelonServiceUsingDTO;

/*
 * Controller 선언해야만 Spring 프레임워크에서 Controller인지 인식 가능
 * 자바 서블릿 역할 수행
 * */
@Controller
public class MelonController {

	private Logger log = Logger.getLogger(this.getClass());

	/*
	 * 비즈니스 로직(중요 로직을 수행하기 위해 사용되는 서비스를 메모리에 적재(싱글톤패턴 적용됨)
	 */

	// Map 객체를 사용한 데이터 처리
	@Resource(name = "MelonService")
	private IMelonService melonService;

	// DTO를 사용한 데이터 처리
	@Resource(name = "MelonServiceUsingDTO")
	private IMelonServiceUsingDTO melonServiceUsingDTO;

	/**
	 * 멜론 노래 리스트 저장하기
	 */
	@RequestMapping(value = "melon/collectMelonSong")
	@ResponseBody
	public String collectMelonRank(HttpServletRequest request, HttpServletResponse response) throws Exception {

		log.info(this.getClass().getName() + ".collectMelonSong Start!");

		melonService.collectMelonSong();

		log.info(this.getClass().getName() + ".collectMelonSong End!");

		return "success";
	}

	/**
	 * 오늘 수집된 멜론 노래리스트 가져오기
	 */
	@RequestMapping(value = "melon/getSongList")
	@ResponseBody
	public List<Map<String, String>> getSongList(HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		log.info(this.getClass().getName() + ".getSongList Start!");

		List<Map<String, String>> rList = melonService.getSongList();

		log.info(this.getClass().getName() + ".getSongList End!");

		return rList;
	}

	/**
	 * 가수별 수집된 노래의 수 가져오기
	 */
	@RequestMapping(value = "melon/getSingerSongCnt")
	@ResponseBody
	public List<Map<String, Object>> getSingerSongCnt(HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		log.info(this.getClass().getName() + ".getSingerSongCnt Start!");

		List<Map<String, Object>> rList = melonService.getSingerSongCnt();

		log.info(this.getClass().getName() + ".getSingerSongCnt End!");

		return rList;
	}

	/**
	 * 멜론 노래 리스트 저장하기
	 */
	@RequestMapping(value = "melon/collectMelonSongUsingDTO")
	@ResponseBody
	public String collectMelonRankUsingDTO(HttpServletRequest request, HttpServletResponse response) throws Exception {

		log.info(this.getClass().getName() + ".collectMelonRankUsingDTO Start!");

		melonService.collectMelonSong();

		log.info(this.getClass().getName() + ".collectMelonRankUsingDTO End!");

		return "success";
	}

	/**
	 * 오늘 수집된 멜론 노래리스트 가져오기
	 */
	@RequestMapping(value = "melon/getSongListUsingDTO")
	@ResponseBody
	public List<MelonDTO> getSongListUsingDTO(HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		log.info(this.getClass().getName() + ".getSongListUsingDTO Start!");

		List<MelonDTO> rList = melonServiceUsingDTO.getSongList();

		log.info(this.getClass().getName() + ".getSongListUsingDTO End!");

		return rList;
	}

	/**
	 * 가수별 수집된 노래의 수 가져오기
	 */
	@RequestMapping(value = "melon/getSingerSongCntUsingDTO")
	@ResponseBody
	public List<MelonDTO> getSingerSongCntUsingDTO(HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		log.info(this.getClass().getName() + ".getSingerSongCntUsingDTO Start!");

		List<MelonDTO> rList = melonServiceUsingDTO.getSingerSongCnt();

		log.info(this.getClass().getName() + ".getSingerSongCntUsingDTO End!");

		return rList;
	}

}
