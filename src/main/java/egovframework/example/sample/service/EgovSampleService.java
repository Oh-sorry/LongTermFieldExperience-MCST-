package egovframework.example.sample.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartFile;

public interface EgovSampleService {

	/** 메인 페이지 **/
	List<SampleDefaultVO> testList(SampleDefaultVO searchVO) throws Exception;
	
	//paging
	int testListCnt(SampleDefaultVO searchVO) throws Exception;
	
	//detail
	List<SampleDefaultVO> testListDetail(SampleDefaultVO searchVO) throws Exception;
	
	//insert page
	String testListInsert(SampleDefaultVO searchVO) throws Exception;
	
	//insert
	void insertTest(SampleDefaultVO searchVO) throws Exception;
	
	//update page
	List<SampleDefaultVO> testListUpdate(SampleDefaultVO searchVO) throws Exception;
	
	//update
	void updateTest(SampleDefaultVO searchVO) throws Exception;
	
	//delete
	void deleteTest(SampleDefaultVO searchVO) throws Exception;

	//insert file
	void insertFile(SampleDefaultVO searchVO, MultipartFile[] file) throws Exception;

	//detail file
	List<SampleDefaultVO> fileList(SampleDefaultVO searchVO) throws Exception;

	//update file
	void updateFile(SampleDefaultVO searchVO, MultipartFile[] file) throws Exception;

	//delete file
	void deleteFile(Integer fileId) throws Exception;
	
	//delete all file
	void deleteFileAll(SampleDefaultVO searchVO) throws Exception;

	//excel
	List<SampleDefaultVO> excelDownload(SampleDefaultVO searchVO) throws Exception;
	
	//댓글 관리
	//댓글 조회
	List<SampleDefaultVO> replyList(SampleDefaultVO searchVO) throws Exception; 
	
	//댓글 작성 
	void replyInsert(SampleDefaultVO searchVO) throws Exception; 
	
	//댓글 수정 
	
	//댓글 삭제
	void replyDelete(Integer rno) throws Exception;
	
	//글 삭제 시 댓글 전체 삭제
	void replyDeleteAll(SampleDefaultVO searchVO);

	//회원 관리
	//회원 로그인 체크
	boolean loginCheck(SampleDefaultVO searchVO, HttpSession session) throws Exception;
	  
	//회원 로그인 정보 
	SampleDefaultVO viewMember(SampleDefaultVO searchVO) throws Exception;
	  
	//회원 로그 아웃
	void logout(HttpSession session);

	//회원 등록
	void memberInsert(SampleDefaultVO searchVO);

	//회원 수정
	void memberUpdate(SampleDefaultVO searchVO);
	//회원 정보 조회
	SampleDefaultVO viewUpdate(String userId);

	//회원 정보 삭제
	void memberDelete(String userId);
	
	//회원 정보 수정 / 삭제 시 비밀번호 체크
	boolean checkPw(String userId, String userPw);

	SampleDefaultVO downloadFile(Integer fileId);
	 

}
