package poly.dto;

public class NlpDTO {

	/* DB 컬럼값 */
	private String word; //단어
	private String word_root; //단어 어근
	private String polarity; //단어

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public String getWord_root() {
		return word_root;
	}

	public void setWord_root(String word_root) {
		this.word_root = word_root;
	}

	public String getPolarity() {
		return polarity;
	}

	public void setPolarity(String polarity) {
		this.polarity = polarity;
	}

}

