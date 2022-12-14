package egovframework.example.sample.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import egovframework.example.sample.service.EgovSampleService;
import egovframework.example.sample.service.SampleDefaultVO;
import egovframework.rte.fdl.property.EgovPropertyService;

@Controller
public class FileController {

	/** EgovSampleService */
	@Resource(name = "sampleService")
	private EgovSampleService sampleService;
	
	@Resource(name="propertiesService")
    protected EgovPropertyService propertiesService;

	// 파일 다운로드
	@GetMapping("/fileDownload.do")
	public void download(@RequestParam("fileId") Integer fileId, SampleDefaultVO searchVO, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		try {
			searchVO.setFileId(fileId);
			System.out.println(searchVO.getFileId());
			SampleDefaultVO downloadFile = sampleService.downloadFile(fileId);
			
			String filePath =  propertiesService.getString("filePath");
			System.out.println(filePath);
			String path = filePath + downloadFile.getSaveFileName();
			String orgFileName = downloadFile.getOrgFileName();
			
			System.out.println("********DOWNLOAD FILE**********");
			System.out.println("파일 원본: " + downloadFile.getOrgFileName());
			System.out.println("글 번호 : " + downloadFile.getBoardIdx());
			System.out.println("파일 이름: " + downloadFile.getSaveFileName());
			System.out.println("파일 경로: " + path);
			System.out.println("********DOWNLOAD FILE**********");

			File file = new File(path);
			orgFileName = new String(orgFileName.getBytes("UTF-8"), "ISO-8859-1"); // 한글 깨짐 인코딩
			response.setHeader("Content-Disposition", "attachment;filename=" + orgFileName); // 다운로드 되거나 로컬에 저장되는 용도로 쓰이는지를 알려주는 헤더

			FileInputStream fileInputStream = new FileInputStream(path); // 파일 읽어오기
			OutputStream out = response.getOutputStream();

			int read = 0;
			byte[] buffer = new byte[1024];
			while ((read = fileInputStream.read(buffer)) != -1) { // 1024바이트씩 계속 읽으면서 outputStream에 저장, -1이 나오면 더이상 읽을 파일이 없음
				out.write(buffer, 0, read);
			}

		} catch (Exception e) {
			throw new Exception("download error");
		}
	}

	// 파일 삭제
	@RequestMapping(value = "/deleteFile.do")
	public String deleteFile(@RequestParam("fileId") Integer fileId, @RequestParam("boardIdx") Integer boardIdx, HttpServletRequest request,
			@ModelAttribute("searchVO") SampleDefaultVO searchVO) throws Exception {
		System.out.println("==================== delete board =================");
		System.out.println("삭제 아이디 : " + fileId);
		System.out.println("==================== delete board =================");
		searchVO.setFileId(fileId);
		searchVO.setBoardIdx(boardIdx);

		// DB에서 삭제
		sampleService.deleteFile(fileId);

		// server(local)에서 삭제
		System.out.println("====================delete File=================");
		System.out.println("삭제 파일 이름 : " + searchVO.getSaveFileName());
		System.out.println("====================delete File=================");
		String filePath =  propertiesService.getString("filePath");
		String path = filePath + searchVO.getSaveFileName();
		File deleteFile = new File(path);
		if (deleteFile.exists()) {
			deleteFile.delete();
		}
		/*
		 * String referer = request.getHeader("Referer"); return "redirect:" + referer;
		 */
		return "redirect: testListInsert.do?code=" + searchVO.getBoardIdx();
	}
}
