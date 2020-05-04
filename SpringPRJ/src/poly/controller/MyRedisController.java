package poly.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import poly.service.IMyRedisService;

/*
 * Controller 선언해야만 Spring 프레임워크에서 Controller인지 인식 가능
 * 자바 서블릿 역할 수행
 * */
@Controller
public class MyRedisController {
	private Logger log = Logger.getLogger(this.getClass());

	/*
	 * 비즈니스 로직(중요 로직을 수행하기 위해 사용되는 서비스를 메모리에 적재(싱글톤패턴 적용됨)
	 */
	@Resource(name = "MyRedisService")
	private IMyRedisService myRedisService;

	/**
	 * 빅데이터 처리를 위한
	 */
	@RequestMapping(value = "myRedis/test")
	@ResponseBody
	public String myRedis(HttpServletRequest request, HttpServletResponse response) throws Exception {

		log.info(this.getClass().getName() + ".myRedis start!");

		myRedisService.doSaveData();

		log.info(this.getClass().getName() + ".myRedis end!");

		return "success";
	}

	@RequestMapping(value = "myRedis/test02")
	@ResponseBody
	public String test02(HttpServletRequest request, HttpServletResponse response) throws Exception {

		log.info(this.getClass().getName() + ".test02 start!");

		myRedisService.doSaveDataforList();

		log.info(this.getClass().getName() + ".test02 end!");

		return "success";
	}

	@RequestMapping(value = "myRedis/test03")
	@ResponseBody
	public String test03(HttpServletRequest request, HttpServletResponse response) throws Exception {

		log.info(this.getClass().getName() + ".test03 start!");

		myRedisService.doSaveDataforListJSON();

		log.info(this.getClass().getName() + ".test03 end!");

		return "success";
	}

	@RequestMapping(value = "myRedis/test04")
	@ResponseBody
	public String test04(HttpServletRequest request, HttpServletResponse response) throws Exception {

		log.info(this.getClass().getName() + ".test04 start!");

		myRedisService.doSaveDataforHashTable();

		log.info(this.getClass().getName() + ".test04 end!");

		return "success";
	}

	@RequestMapping(value = "myRedis/test05")
	@ResponseBody
	public String test05(HttpServletRequest request, HttpServletResponse response) throws Exception {

		log.info(this.getClass().getName() + ".test05 start!");

		myRedisService.doSaveDataforSet();

		log.info(this.getClass().getName() + ".test05 end!");

		return "success";
	}
	
	@RequestMapping(value = "myRedis/test06")
	@ResponseBody
	public String test06(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		log.info(this.getClass().getName() + ".test06 start!");
		
		myRedisService.doSaveDataforZSet();
		
		log.info(this.getClass().getName() + ".test06 end!");
		
		return "success";
	}

}
