package poly.persistance.mapper;

import config.Mapper;
import poly.dto.OcrDTO;

@Mapper("OcrMapper")
public interface IOcrMapper {

	//이미지로부터 인식된 텍스트 내용 DB에 등록
	int InsertOcrInfo(OcrDTO pDTO) throws Exception;
	
	
}

