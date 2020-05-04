package poly.persistance.mapper;

import java.util.List;

import config.Mapper;
import poly.dto.NlpDTO;

@Mapper("NlpMapper")
public interface INlpMapper {

	//단어 정보 가져오기
	List<NlpDTO> getWord(NlpDTO pDTO) throws Exception;
	
	
}

