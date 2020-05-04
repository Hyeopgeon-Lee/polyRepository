package poly.persistance.mapper;

import java.util.List;

import config.Mapper;
import poly.dto.NoticeDTO;

@Mapper("NoticeMapper")
public interface NoticeMapper {

	//게시판 리스트
	List<NoticeDTO> getNoticeList() throws Exception;
	
	//게시판 글 등록
	void InsertNoticeInfo(NoticeDTO pDTO) throws Exception;
	
	//게시판 상세보기
	NoticeDTO getNoticeInfo(NoticeDTO pDTO) throws Exception;

	//게시판 조회수 업데이트
	void updateNoticeReadCnt(NoticeDTO pDTO) throws Exception;
	
	//게시판 글 수정
	void updateNoticeInfo(NoticeDTO pDTO) throws Exception;
	
	//게시판 글 삭제
	void deleteNoticeInfo(NoticeDTO pDTO) throws Exception;
	
}
