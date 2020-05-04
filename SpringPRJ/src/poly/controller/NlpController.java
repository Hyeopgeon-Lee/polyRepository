package poly.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import poly.dto.NlpDTO;
import poly.service.INlpService;
import poly.util.CmmUtil;

/*
 * Controller 선언해야만 Spring 프레임워크에서 Controller인지 인식 가능
 * 자바 서블릿 역할 수행
 * */
@Controller
public class NlpController {
	private Logger log = Logger.getLogger(this.getClass());

	/*
	 * 비즈니스 로직(중요 로직을 수행하기 위해 사용되는 서비스를 메모리에 적재(싱글톤패턴 적용됨)
	 */
	@Resource(name = "NlpService")
	private INlpService nlpService;

	/**
	 * 문장 입력창
	 */
	@RequestMapping(value = "nlp/inputForm")
	public String inputForm() {
		log.info(this.getClass().getName() + ".inputForm!");

		return "/nlp/inputForm";
	}

	/**
	 * 긍정, 부정 분석하기
	 */
	@RequestMapping(value = "nlp/wordAnalysis")
	public String wordAnalysis(HttpServletRequest request, HttpServletResponse response, ModelMap model)
			throws Exception {

		log.info(this.getClass().getName() + ".wordAnalysis start!");

		String res = "";
		
		//분석할 문장
		String text_message = CmmUtil.nvl(request.getParameter("text_message"));
		
		NlpDTO pDTO = new NlpDTO();
		
		pDTO.setWord(text_message);
		
		int point = nlpService.preProcessWordAnalysisForMind(pDTO);
		
		if (point < 0) {
			res = "\""+ text_message + "\" 문장의 분석결과는 "+ point + "로 부정적인 결과가 나왔습니다.";
		
		}else if (point == 0) {
			res = "\""+ text_message + "\" 문장의 분석결과는 데이터 사전에 존재하지 않아 분석이 불가능합니다.";
				
		}else {
			res = "\""+ text_message + "\" 문장의 분석결과는 "+ point + "로 긍정적인 결과가 나왔습니다.";
			
		}
		
		// 분석 결과 넣어주기
		model.addAttribute("res", res);

		log.info(this.getClass().getName() + ".wordAnalysis end!");

		return "/nlp/wordAnalysis";
	}

}
