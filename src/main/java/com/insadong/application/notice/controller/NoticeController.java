package com.insadong.application.notice.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.springframework.core.io.ResourceLoader;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.insadong.application.common.ResponseDTO;
import com.insadong.application.employee.dto.EmpDTOImplUS;
import com.insadong.application.notice.dto.NoticeDTO;
import com.insadong.application.notice.service.NoticeService;
import com.insadong.application.paging.Pagenation;
import com.insadong.application.paging.PagingButtonInfo;
import com.insadong.application.paging.ResponseDTOWithPaging;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/insa/v1")
public class NoticeController {

	private final NoticeService noticeService;
	private final ResourceLoader resourceLoader;

	public NoticeController(NoticeService noticeService, ResourceLoader resourceLoader) {
		this.noticeService = noticeService;
		this.resourceLoader = resourceLoader;
	}

	/* 공지사항 전체목록 조회 */
	@GetMapping("/noticelist")
	public ResponseEntity<ResponseDTO> selectNoticeList(@RequestParam(name = "page", defaultValue = "1") int page) {
		log.info("[NoticeControll	er] : selectNoticeList start ==================================== ");
		log.info("[NoticeController] : page : {}", page);

		Page<NoticeDTO> noticeDTOList = noticeService.selectNoticeList(page);

		PagingButtonInfo pageInfo = Pagenation.getPagingButtonInfo(noticeDTOList);

		log.info("[NoticeController] : pageInfo : {}", pageInfo);

		ResponseDTOWithPaging responseDTOWithPaging = new ResponseDTOWithPaging();
		responseDTOWithPaging.setPageInfo(pageInfo);
		responseDTOWithPaging.setData(noticeDTOList.getContent());

		log.info("[NoticeController] : selectNoticeList end ==================================== ");

		return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "조회 성공", responseDTOWithPaging));
	}

	/* 공지사항 검색조건 조회 */
	@GetMapping("/noticesearch")
	public ResponseEntity<ResponseDTO> searchNoticeByOption(@RequestParam(name = "page", defaultValue = "1") int page,
			@RequestParam(name = "searchOption") String searchOption,
			@RequestParam(name = "searchKeyword") String searchKeyword) {

		log.info("[NoticeController] : searchNoticeByOption start ==================================== ");
		log.info("[NoticeController] : page : {}", page);
		log.info("[NoticeController] : searchOption : {}", searchOption);
		log.info("[NoticeController] : searchKeyword : {}", searchKeyword);

		Page<NoticeDTO> noticeDTOList = noticeService.searchNoticeByOption(page, searchOption, searchKeyword);

		PagingButtonInfo pageInfo = Pagenation.getPagingButtonInfo(noticeDTOList);

		log.info("[NoticeController] : pageInfo : {}", pageInfo);

		ResponseDTOWithPaging responseDTOWithPaging = new ResponseDTOWithPaging();
		responseDTOWithPaging.setPageInfo(pageInfo);
		responseDTOWithPaging.setData(noticeDTOList.getContent());

		log.info("[NoticeController] : searchNoticeByOption end ==================================== ");

		return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "조회 성공", responseDTOWithPaging));
	}

	/* 공지사항 등록(파일 업로드) */
	@PostMapping("/noticeregist")
	public ResponseEntity<ResponseDTO> registNotice(@ModelAttribute NoticeDTO noticeDTO,
			@AuthenticationPrincipal EmpDTOImplUS employeeDTO) throws IOException {

		log.info("[NoticeController] noticeDTO: {}", noticeDTO);
		log.info("[NoticeController] empDTO: {}", employeeDTO);

//		noticeDTO.setNoticeWriter(employeeDTO);

		noticeService.registNotice(noticeDTO, employeeDTO.getEmpCode());

		return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "공지사항 등록 성공"));
	}

	/* 공지사항 상세 조회 */
	@GetMapping("/notice/{noticeCode}")
	public ResponseEntity<ResponseDTO> selectNoticeList(@PathVariable Long noticeCode) {

		return ResponseEntity.ok()
				.body(new ResponseDTO(HttpStatus.OK, "조회 성공", noticeService.selectNoticeList(noticeCode)));
	}

	/* 파일 다운로드 */
	@GetMapping("/download/{fileName}")
	public void download(@PathVariable String fileName, HttpServletResponse response) throws Exception {
		try {
			
			String path = "src/main/resources/static/productimgs/" + fileName;

			File file = new File(path);
			response.setHeader("Content-Disposition", "attachment;filename=" + file.getName());

			// 파일 읽어오기
			FileInputStream fileInputStream = new FileInputStream(path);
			OutputStream out = response.getOutputStream();

			int read = 0;
			byte[] buffer = new byte[1024];
			// 1024바이트씩 계속 읽으면서 outputStream에 저장, -1이 나오면 더이상 읽을 파일이 없음
			while ((read = fileInputStream.read(buffer)) != -1) {
				out.write(buffer, 0, read);
			}

		} catch (Exception e) {
			throw new Exception("download error");
		}
	}

//	@GetMapping("/{fileName}")
//	public ResponseEntity<ResponseDTO> download(@PathVariable("fileName") String fileName) {
//		byte[] downloadFile = noticeService.downloadFile(fileName);
//		return ResponseEntity
//				.ok()
//				.body(new ResponseDTO(HttpStatus.OK, "조회 성공", downloadFile));
//	}
	
	/* 공지사항 수정 */
	@PutMapping("/notice")
	public ResponseEntity<ResponseDTO> updateNotice(@ModelAttribute NoticeDTO noticeDTO, 
			@AuthenticationPrincipal EmpDTOImplUS employeeDTO) {
		
		noticeService.updateNotice(noticeDTO, employeeDTO.getEmpCode());
		
		return ResponseEntity
				.ok()
				.body(new ResponseDTO(HttpStatus.OK, "공지사항 수정 성공"));
		
	}

}
