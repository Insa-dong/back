package com.insadong.application.notice.service;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.insadong.application.common.entity.Employee;
import com.insadong.application.common.entity.Notice;
import com.insadong.application.employee.repository.EmployeeRepository;
import com.insadong.application.notice.dto.FileDTO;
import com.insadong.application.notice.dto.NoticeDTO;
import com.insadong.application.notice.repository.NoticeRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class NoticeService {

	private final NoticeRepository noticeRepository;
	private final EmployeeRepository employeeRepository;
	private final ModelMapper modelMapper;
	
	@Value("${image.image-url}")
	private String IMAGE_URL;
	@Value("${image.image-dir}")
	private String IMAGE_DIR;

	public NoticeService(NoticeRepository noticeRepository, EmployeeRepository employeeRepository,
			ModelMapper modelMapper) {
		this.noticeRepository = noticeRepository;
		this.employeeRepository = employeeRepository;
		this.modelMapper = modelMapper;
	}

	/* 공지사항 전체목록 조회 */
	public Page<NoticeDTO> selectNoticeList(int page) {

		log.info("[NoticeService] selectNoticeList start ============================== ");

		Pageable pageable = PageRequest.of(page - 1, 4, Sort.by("noticeCode").descending());

		Page<Notice> noticeList = noticeRepository.findAll(pageable);
		Page<NoticeDTO> noticeDTOList = noticeList.map(notice -> modelMapper.map(notice, NoticeDTO.class));

		log.info("[NoticeService] selectNoticeList.getContent() : {}", noticeDTOList.getContent());

		log.info("[NoticeService] selectEmpList end ============================== ");

		return noticeDTOList;
	}

	/* 공지사항 검색조건 조회 */
	public Page<NoticeDTO> searchNoticeByOption(int page, String searchOption, String searchKeyword) {

		log.info("[NoticeService] searchNoticeByOption start ==============================");

		Pageable pageable = PageRequest.of(page - 1, 4, Sort.by("noticeCode").descending());

		if (searchOption.equals("title")) {
			Page<Notice> noticeList = noticeRepository.findByNoticeTitleContains(pageable, searchKeyword);
			Page<NoticeDTO> noticeDTOList = noticeList.map(notice -> modelMapper.map(notice, NoticeDTO.class));

			if (noticeDTOList.isEmpty()) {
				throw new IllegalArgumentException("검색조건과 일치하는 공지사항이 존재하지 않습니다.");
			}

			log.info("[NoticeService] searchNoticeByOption.getContent() : {}", noticeDTOList.getContent());

			return noticeDTOList;

		} else if (searchOption.equals("content")) {
			Page<Notice> noticeList = noticeRepository.findByNoticeContentContains(pageable, searchKeyword);
			Page<NoticeDTO> noticeDTOList = noticeList.map(notice -> modelMapper.map(notice, NoticeDTO.class));

			if (noticeDTOList.isEmpty()) {
				throw new IllegalArgumentException("검색조건과 일치하는 공지사항이 존재하지 않습니다.");
			}

			log.info("[NoticeService] searchNoticeByOption.getContent() : {}", noticeDTOList.getContent());

			return noticeDTOList;

		} else if (searchOption.equals("writer")) {

			List<Employee> employeeList = employeeRepository.findByEmpNameContains(searchKeyword);

			if (employeeList.isEmpty()) {
				throw new IllegalArgumentException("검색 조건과 일치하는 공지사항이 존재하지 않습니다.");
			} else if (employeeList.size() > 1) {
				throw new IllegalArgumentException("검색 조건과 일치하는 공지사항이 존재하지 않습니다.");
			}

			Employee findEmployee = employeeList.get(0);

			Page<Notice> noticeList = noticeRepository.findByNoticeWriter(pageable, findEmployee);
			Page<NoticeDTO> noticeDTOList = noticeList.map(notice -> modelMapper.map(notice, NoticeDTO.class));

			if (noticeDTOList.isEmpty()) {
				throw new IllegalArgumentException("검색조건과 일치하는 공지사항이 존재하지 않습니다.");
			}

			log.info("[NoticeService] searchNoticeByOption.getContent() : {}", noticeDTOList.getContent());

			return noticeDTOList;

		} else {
			throw new IllegalArgumentException("유효하지 않은 검색 옵션입니다.");
		}

	}

	/* 공지사항 등록 */
	@Transactional
	public void registNotice(NoticeDTO noticeDTO) throws IOException {

		for(MultipartFile file : noticeDTO.getNoticeFile()) {
			
			FileDTO fileDTO = new FileDTO();
			
			
			String originFileName = file.getOriginalFilename();
			String saveFileName = UUID.randomUUID().toString().replace("-", "");
			String fileFath = IMAGE_DIR;
			Long fileSize = file.getSize();
			
		
			
			Notice notice = noticeRepository.save(modelMapper.map(noticeDTO, Notice.class));
		}

	}

}
