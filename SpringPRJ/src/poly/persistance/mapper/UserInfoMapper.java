package poly.persistance.mapper;

import config.Mapper;
import poly.dto.UserInfoDTO;

@Mapper("UserInfoMapper")
public interface UserInfoMapper {

	// 회원 가입하기(회원정보 등록하기)
	int insertUserInfo(UserInfoDTO pDTO) throws Exception;
	
	// 회원 가입 전 중복체크하기(DB조회하기)
	UserInfoDTO getUserExists(UserInfoDTO pDTO) throws Exception;
	
	// 로그인을 위해 아이디와 비밀번호가 일치하는지 확인하기
	UserInfoDTO getUserLoginCheck(UserInfoDTO pDTO) throws Exception;

}
