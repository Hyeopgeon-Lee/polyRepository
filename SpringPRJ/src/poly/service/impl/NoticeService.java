package poly.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import poly.dto.NoticeDTO;
import poly.persistance.mapper.NoticeMapper;
import poly.service.INoticeService;

@Service("NoticeService")
public class NoticeService implements INoticeService{
	
	@Resource(name="NoticeMapper")
	private NoticeMapper noticeMapper;
	
	@Override
	public List<NoticeDTO> getNoticeList() throws Exception {
		return noticeMapper.getNoticeList();
		
	}
	
	@Override
	public void InsertNoticeInfo(NoticeDTO pDTO) throws Exception {
		noticeMapper.InsertNoticeInfo(pDTO);;
		
	}
	
	@Override
	public NoticeDTO getNoticeInfo(NoticeDTO pDTO) throws Exception {
		return noticeMapper.getNoticeInfo(pDTO);
		
	}
	
	@Override
	public void updateNoticeReadCnt(NoticeDTO pDTO) throws Exception {
		noticeMapper.updateNoticeReadCnt(pDTO);;
		
	}
	
	@Override
	public void updateNoticeInfo(NoticeDTO pDTO) throws Exception {
		noticeMapper.updateNoticeInfo(pDTO);
		
	}
	
	@Override
	public void deleteNoticeInfo(NoticeDTO pDTO) throws Exception {
		noticeMapper.deleteNoticeInfo(pDTO);
		
	}
}
