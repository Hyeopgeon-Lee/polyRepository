package poly.util;

public class StringUtil {

	/**
	 * 데이터 사전에 정의된 키 값을 가져오기 위한 함 
	 * @param 문장의 첫글자
	 * @return 데이터 사전 키 값
	 */
	public static String getFirstWord(String word) {
		
		String res = "";
		
		if (word.matches("[가-깋]")) {
			res = "ㄱ";
			
		}else if (word.matches("[까-낗]")) {
			res = "ㄲ";
			
		}else if (word.matches("[나-닣]")) {
			res = "ㄴ";
			
		}else if (word.matches("[다-딯]")) {
			res = "ㄷ";
			
		}else if (word.matches("[따-띻]")) {
			res = "ㄸ";
			
		}else if (word.matches("[라-맇]")) {
			res = "ㄹ";
			
		}else if (word.matches("[마-밓]")) {
			res = "ㅁ";
			
		}else if (word.matches("[바-빟]")) {
			res = "ㅂ";
			
		}else if (word.matches("[빠-삫]")) {
			res = "ㅃ";
			
		}else if (word.matches("[사-싷]")) {
			res = "ㅅ";
			
		}else if (word.matches("[싸-앃]")) {
			res = "ㅅ";
			
		}else if (word.matches("[아-잏]")) {
			res = "ㅇ";
			
		}else if (word.matches("[자-짛]")) {
			res = "ㅈ";
			
		}else if (word.matches("[짜-찧]")) {
			res = "ㅉ";
			
		}else if (word.matches("[카-킿]")) {
			res = "ㅋ";
			
		}else if (word.matches("[타-팋]")) {
			res = "ㅌ";
			
		}else if (word.matches("[파-핗]")) {
			res = "ㅍ";
			
		}else if (word.matches("[하-힣]")) {
			res = "ㅎ";
			
		}
	
		return res; 
	}

}
