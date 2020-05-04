package poly.service;

import poly.dto.NlpDTO;

public interface INlpService {

	//단어 정보 가져오기
	void getWord() throws Exception;
	
	int preProcessWordAnalysisForMind(NlpDTO pDTO) throws Exception;
	
	// 감정 분석
	int WordAnalysisForMind(String firstWord, String text) throws Exception; 
}
